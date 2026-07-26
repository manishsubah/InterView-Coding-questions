#Requires -Version 5.1
<#
.SYNOPSIS
    Background watcher for logon, sleep/wake, and unlock auto-sync triggers.
#>

$ErrorActionPreference = "Stop"

$RepoPath = "C:\SUBAH\PROGRAMMING\InterViewCoding"
$SyncScript = Join-Path $RepoPath "scripts\Invoke-AutoCommitSync.ps1"
$LogDir = Join-Path $RepoPath "logs"
$WatcherLogFile = Join-Path $LogDir "watcher.log"
$WatcherLockFile = Join-Path $env:TEMP "InterViewCoding-watcher.lock"
$InitialDelaySeconds = 30

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
        -ArgumentList @("-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$SyncScript`"", "-TriggerReason", $TriggerReason) `
        -WindowStyle Hidden `
        -ErrorAction SilentlyContinue | Out-Null
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
        $ts = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
        Add-Content -Path $using:WatcherLogFile -Value "[$ts] [INFO] Power resume detected." -Encoding UTF8
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

Start-Job -ScriptBlock {
    param($Delay, $Script)
    Start-Sleep -Seconds $Delay
    Start-Process -FilePath "powershell.exe" `
        -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", "`"$Script`"", "-TriggerReason", "Logon" `
        -WindowStyle Hidden
} -ArgumentList $InitialDelaySeconds, $SyncScript | Out-Null

Write-WatcherLog "Initial logon sync scheduled in $InitialDelaySeconds seconds."

$form = New-Object System.Windows.Forms.Form
$form.Text = "InterViewCoding AutoCommit Watcher"
$form.ShowInTaskbar = $false
$form.WindowState = [System.Windows.Forms.FormWindowState]::Minimized
$form.Size = New-Object System.Drawing.Size(0, 0)
$form.Opacity = 0
$form.Load.Add({ $this.Hide() })

[System.Windows.Forms.Application]::Run($form)
