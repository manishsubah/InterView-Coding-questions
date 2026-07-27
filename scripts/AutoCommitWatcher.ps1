#Requires -Version 5.1
<#
.SYNOPSIS
    Background watcher for logon, sleep/wake, unlock, and hourly fallback auto-sync.
#>

$ErrorActionPreference = "Stop"

$RepoPath = "C:\SUBAH\PROGRAMMING\InterViewCoding"
$SyncScript = Join-Path $RepoPath "scripts\Invoke-AutoCommitSync.ps1"
$LogDir = Join-Path $RepoPath "logs"
$WatcherLogFile = Join-Path $LogDir "watcher.log"
$WatcherLockFile = Join-Path $env:TEMP "InterViewCoding-watcher.lock"
$InitialDelayMs = 30000
$HourlyIntervalMs = 3600000

function Write-WatcherLog {
    param([string]$Message, [string]$Level = "INFO")
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    $line = "[$timestamp] [$Level] $Message"
    if (-not (Test-Path $LogDir)) {
        New-Item -ItemType Directory -Path $LogDir -Force | Out-Null
    }
    Add-Content -Path $WatcherLogFile -Value $line -Encoding UTF8
}

function Start-SyncProcess {
    param([string]$TriggerReason)
    Write-WatcherLog "Starting sync process for trigger: $TriggerReason"
    Start-Process -FilePath "powershell.exe" `
        -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$SyncScript`"", "-TriggerReason", $TriggerReason `
        -WindowStyle Hidden `
        -ErrorAction SilentlyContinue | Out-Null
}

function Test-HasPendingWork {
    $prevErrorAction = $ErrorActionPreference
    $ErrorActionPreference = "Continue"

    $status = (& git -C $RepoPath status --porcelain 2>&1 | Out-String).Trim()
    $ahead = 0
    if ($LASTEXITCODE -eq 0) {
        $tracking = (& git -C $RepoPath rev-parse --abbrev-ref --symbolic-full-name "@{u}" 2>&1 | Out-String).Trim()
        if ($LASTEXITCODE -eq 0 -and $tracking) {
            $count = (& git -C $RepoPath rev-list --count "$tracking..HEAD" 2>&1 | Out-String).Trim()
            if ($LASTEXITCODE -eq 0 -and $count) {
                $ahead = [int]$count
            }
        }
    }

    $ErrorActionPreference = $prevErrorAction
    return (-not [string]::IsNullOrWhiteSpace($status)) -or ($ahead -gt 0)
}

$watcherLock = $null
try {
    $watcherLock = [System.IO.File]::Open(
        $WatcherLockFile,
        [System.IO.FileMode]::OpenOrCreate,
        [System.IO.FileAccess]::ReadWrite,
        [System.IO.FileShare]::None
    )
}
catch {
    exit 0
}

Write-WatcherLog "AutoCommitWatcher started (PID $PID)."

if (-not (Test-Path $SyncScript)) {
    Write-WatcherLog "Sync script not found: $SyncScript" "ERROR"
    exit 1
}

$null = Register-WmiEvent -Query "SELECT * FROM Win32_PowerManagementEvent WHERE EventType = 4" `
    -SourceIdentifier "InterViewCoding.PowerResume" `
    -Action {
        $script = $using:SyncScript
        $logFile = $using:WatcherLogFile
        $ts = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
        Add-Content -Path $logFile -Value "[$ts] [INFO] Power resume detected." -Encoding UTF8
        Start-Process -FilePath "powershell.exe" `
            -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$script`"", "-TriggerReason", "Wake" `
            -WindowStyle Hidden
    }

Add-Type -AssemblyName System.Windows.Forms
Add-Type -AssemblyName System.Drawing

Register-ObjectEvent -InputObject ([Microsoft.Win32.SystemEvents]) -EventName SessionSwitch -Action {
    if ($Event.SourceEventArgs.Reason.ToString() -eq "SessionUnlock") {
        $script = $using:SyncScript
        $logFile = $using:WatcherLogFile
        $ts = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
        Add-Content -Path $logFile -Value "[$ts] [INFO] Session unlock detected." -Encoding UTF8
        Start-Process -FilePath "powershell.exe" `
            -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$script`"", "-TriggerReason", "Unlock" `
            -WindowStyle Hidden
    }
} | Out-Null

Write-WatcherLog "Subscribed to power resume and session unlock events."

$form = New-Object System.Windows.Forms.Form
$form.Text = "InterViewCoding AutoCommit Watcher"
$form.ShowInTaskbar = $false
$form.WindowState = [System.Windows.Forms.FormWindowState]::Minimized
$form.Size = New-Object System.Drawing.Size(0, 0)
$form.Opacity = 0
$form.Load.Add({ $this.Hide() })

$logonTimer = New-Object System.Windows.Forms.Timer
$logonTimer.Interval = $InitialDelayMs
$logonTimer.Add_Tick({
    $this.Stop()
    $ts = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    Add-Content -Path $using:WatcherLogFile -Value "[$ts] [INFO] Logon timer fired, starting sync." -Encoding UTF8
    Start-Process -FilePath "powershell.exe" `
        -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$using:SyncScript`"", "-TriggerReason", "Logon" `
        -WindowStyle Hidden
})
$logonTimer.Start()
Write-WatcherLog "Initial logon sync scheduled in $($InitialDelayMs / 1000) seconds (timer)."

$hourlyTimer = New-Object System.Windows.Forms.Timer
$hourlyTimer.Interval = $HourlyIntervalMs
$hourlyTimer.Add_Tick({
    $repo = $using:RepoPath
    $sync = $using:SyncScript
    $logFile = $using:WatcherLogFile
    $ts = Get-Date -Format "yyyy-MM-dd HH:mm:ss"

    $hasWork = $false
    $prev = $ErrorActionPreference
    $ErrorActionPreference = "Continue"
    $status = (& git -C $repo status --porcelain 2>&1 | Out-String).Trim()
    if ($LASTEXITCODE -eq 0 -and -not [string]::IsNullOrWhiteSpace($status)) { $hasWork = $true }
    if (-not $hasWork) {
        $tracking = (& git -C $repo rev-parse --abbrev-ref --symbolic-full-name "@{u}" 2>&1 | Out-String).Trim()
        if ($LASTEXITCODE -eq 0 -and $tracking) {
            $count = (& git -C $repo rev-list --count "$tracking..HEAD" 2>&1 | Out-String).Trim()
            if ($LASTEXITCODE -eq 0 -and [int]$count -gt 0) { $hasWork = $true }
        }
    }
    $ErrorActionPreference = $prev

    if ($hasWork) {
        Add-Content -Path $logFile -Value "[$ts] [INFO] Hourly fallback: pending work detected, starting sync." -Encoding UTF8
        Start-Process -FilePath "powershell.exe" `
            -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$sync`"", "-TriggerReason", "Wake" `
            -WindowStyle Hidden
    }
})
$hourlyTimer.Start()
Write-WatcherLog "Hourly fallback timer started (checks every 60 minutes)."

[System.Windows.Forms.Application]::Run($form)
