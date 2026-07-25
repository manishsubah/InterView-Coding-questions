#Requires -Version 5.1
<#
.SYNOPSIS
    Registers the InterViewCoding daily auto-commit scheduled task.
.DESCRIPTION
    Creates a Windows Scheduled Task that runs auto-commit-push.ps1 daily at
    8:00 PM local time. Uses schtasks for broad compatibility on Windows.
#>

$ErrorActionPreference = "Stop"

$TaskName = "InterViewCoding-DailyAutoCommit"
$ScriptPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\auto-commit-push.ps1"
$DailyTime = "20:00"

if (-not (Test-Path $ScriptPath)) {
    throw "Script not found: $ScriptPath"
}

$taskCommand = "powershell.exe -NoProfile -ExecutionPolicy Bypass -File `"$ScriptPath`""

$existing = schtasks /Query /TN $TaskName 2>$null
if ($LASTEXITCODE -eq 0) {
    schtasks /Delete /TN $TaskName /F | Out-Null
    Write-Host "Removed existing task '$TaskName'."
}

$result = cmd /c "schtasks /Create /TN `"$TaskName`" /TR `"$taskCommand`" /SC DAILY /ST $DailyTime /RL LIMITED /F" 2>&1
if ($LASTEXITCODE -ne 0) {
    throw "Failed to register scheduled task '$TaskName': $result"
}

Write-Host "Scheduled task '$TaskName' registered successfully."
Write-Host "  Daily trigger:  $DailyTime (local time)"
Write-Host "  Script:         $ScriptPath"
Write-Host ""
Write-Host "Optional: In Task Scheduler, open this task and enable:"
Write-Host "  - 'Run task as soon as possible after a scheduled start is missed'"
Write-Host "  - Add a second trigger: At log on, with 2 minute delay"
Write-Host ""
Write-Host "Test now:  schtasks /Run /TN `"$TaskName`""
Write-Host "Remove:    schtasks /Delete /TN `"$TaskName`" /F"
