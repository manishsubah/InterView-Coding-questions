#Requires -Version 5.1
<#
.SYNOPSIS
    Delayed logon sync launcher (separate process, reliable).
#>

$SyncScript = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\Invoke-AutoCommitSync.ps1"
$LogDir = "C:\SUBAH\PROGRAMMING\InterViewCoding\logs"
$WatcherLogFile = Join-Path $LogDir "watcher.log"
$DelaySeconds = 60

function Write-WatcherLog {
    param([string]$Message)
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    if (-not (Test-Path $LogDir)) {
        New-Item -ItemType Directory -Path $LogDir -Force | Out-Null
    }
    Add-Content -Path $WatcherLogFile -Value "[$timestamp] [INFO] $Message" -Encoding UTF8
}

Write-WatcherLog "Delayed logon sync started (waiting $DelaySeconds seconds)."
Start-Sleep -Seconds $DelaySeconds
Write-WatcherLog "Delayed logon sync firing now."
& powershell.exe -NoProfile -ExecutionPolicy Bypass -File $SyncScript -TriggerReason Logon
