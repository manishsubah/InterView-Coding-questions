#Requires -Version 5.1
<#
.SYNOPSIS
    Orchestrates auto-commit with cooldown, boot detection, and network wait.
.PARAMETER TriggerReason
    Logon, Wake, Unlock, or Manual.
.PARAMETER Force
    Skip cooldown checks and always sync.
#>

param(
    [ValidateSet("Logon", "Wake", "Unlock", "Manual")]
    [string]$TriggerReason = "Manual",

    [switch]$Force
)

$ErrorActionPreference = "Stop"

$RepoPath = "C:\SUBAH\PROGRAMMING\InterViewCoding"
$CommitScript = Join-Path $RepoPath "scripts\auto-commit-push.ps1"
$LogDir = Join-Path $RepoPath "logs"
$WatcherLogFile = Join-Path $LogDir "watcher.log"
$StateFile = Join-Path $LogDir ".sync-state.json"
$CooldownMinutes = 30
$NetworkRetrySeconds = 15
$NetworkMaxWaitSeconds = 180

function Write-WatcherLog {
    param([string]$Message, [string]$Level = "INFO")
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    $line = "[$timestamp] [$Level] $Message"
    if (-not (Test-Path $LogDir)) {
        New-Item -ItemType Directory -Path $LogDir -Force | Out-Null
    }
    Add-Content -Path $WatcherLogFile -Value $line -Encoding UTF8
}

function Get-BootTimeUtc {
    $os = Get-CimInstance Win32_OperatingSystem
    return $os.LastBootUpTime.ToUniversalTime().ToString("o")
}

function Get-SyncState {
    if (-not (Test-Path $StateFile)) {
        return $null
    }
    try {
        return Get-Content $StateFile -Raw | ConvertFrom-Json
    }
    catch {
        return $null
    }
}

function Set-SyncState {
    param(
        [string]$LastSuccessUtc,
        [string]$LastBootUtc
    )
    $state = [ordered]@{
        lastSuccessUtc = $LastSuccessUtc
        lastBootUtc    = $LastBootUtc
    }
    if (-not (Test-Path $LogDir)) {
        New-Item -ItemType Directory -Path $LogDir -Force | Out-Null
    }
    $state | ConvertTo-Json | Set-Content -Path $StateFile -Encoding UTF8
}

function Test-HasUnpushedCommits {
    $prevErrorAction = $ErrorActionPreference
    $ErrorActionPreference = "Continue"
    $tracking = (& git -C $RepoPath rev-parse --abbrev-ref --symbolic-full-name "@{u}" 2>&1 | Out-String).Trim()
    if ($LASTEXITCODE -ne 0 -or [string]::IsNullOrWhiteSpace($tracking)) {
        $ErrorActionPreference = $prevErrorAction
        return $false
    }
    $count = [int](& git -C $RepoPath rev-list --count "$tracking..HEAD" 2>&1 | Out-String).Trim()
    $ErrorActionPreference = $prevErrorAction
    return ($LASTEXITCODE -eq 0) -and ($count -gt 0)
}

function Test-HasRealWorkChanges {
    $prevErrorAction = $ErrorActionPreference
    $ErrorActionPreference = "Continue"
    $lines = & git -C $RepoPath status --porcelain 2>&1
    $ErrorActionPreference = $prevErrorAction

    if ($LASTEXITCODE -ne 0) {
        return $false
    }

    foreach ($line in $lines) {
        if ([string]::IsNullOrWhiteSpace($line)) { continue }
        $path = $line.Substring(3).Trim('"')
        if ($path -ne "auto-sync-log.md") {
            return $true
        }
    }
    return $false
}

function Test-ShouldSync {
    param([string]$Reason)

    if ($Force) {
        Write-WatcherLog "Force sync requested."
        return $true
    }

    if ($Reason -eq "Logon") {
        Write-WatcherLog "Logon trigger: sync always allowed."
        return $true
    }

    if (Test-HasRealWorkChanges) {
        Write-WatcherLog "Real work changes detected outside sync log. Sync allowed."
        return $true
    }

    if (Test-HasUnpushedCommits) {
        Write-WatcherLog "Unpushed commits detected. Sync allowed."
        return $true
    }

    $state = Get-SyncState
    if ($null -ne $state -and $state.lastSuccessUtc) {
        $lastSuccess = [DateTime]::Parse($state.lastSuccessUtc).ToUniversalTime()
        $elapsed = ([DateTime]::UtcNow - $lastSuccess).TotalMinutes
        if ($elapsed -lt $CooldownMinutes) {
            Write-WatcherLog "Cooldown active ($([math]::Round($elapsed, 1)) min / $CooldownMinutes min). Skipping sync for $Reason."
            return $false
        }
    }

    Write-WatcherLog "Cooldown expired or no prior sync. Sync allowed for $Reason."
    return $true
}

function Wait-ForNetwork {
    $deadline = (Get-Date).AddSeconds($NetworkMaxWaitSeconds)
    $attempt = 0

    while ((Get-Date) -lt $deadline) {
        $attempt++
        $pingOk = $false
        $tcpOk = $false

        try {
            $pingOk = Test-Connection -ComputerName "github.com" -Count 1 -Quiet -ErrorAction SilentlyContinue
        }
        catch { }

        if (-not $pingOk) {
            try {
                $tcp = Test-NetConnection -ComputerName "ssh.github.com" -Port 443 -WarningAction SilentlyContinue -ErrorAction SilentlyContinue
                $tcpOk = $tcp.TcpTestSucceeded
            }
            catch { }
        }

        if ($pingOk -or $tcpOk) {
            Write-WatcherLog "Network ready (attempt $attempt)."
            return $true
        }

        Write-WatcherLog "Waiting for network (attempt $attempt)..." "WARN"
        Start-Sleep -Seconds $NetworkRetrySeconds
    }

    Write-WatcherLog "Network not available after $NetworkMaxWaitSeconds seconds." "WARN"
    return $false
}

if (-not (Test-Path $CommitScript)) {
    throw "Commit script not found: $CommitScript"
}

Write-WatcherLog "Sync requested. Trigger=$TriggerReason Force=$($Force.IsPresent)"

if (-not (Test-ShouldSync -Reason $TriggerReason)) {
    exit 0
}

if (-not (Wait-ForNetwork)) {
    Write-WatcherLog "Proceeding without confirmed network; commit script will retry." "WARN"
}

& powershell.exe -NoProfile -ExecutionPolicy Bypass -File $CommitScript -TriggerReason $TriggerReason
$exitCode = $LASTEXITCODE

if ($exitCode -eq 0) {
    Set-SyncState -LastSuccessUtc ([DateTime]::UtcNow.ToString("o")) -LastBootUtc (Get-BootTimeUtc)
    Write-WatcherLog "Sync completed successfully."
}
else {
    Write-WatcherLog "Sync failed with exit code $exitCode." "ERROR"
}

exit $exitCode
