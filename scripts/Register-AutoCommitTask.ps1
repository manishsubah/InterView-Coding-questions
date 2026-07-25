#Requires -Version 5.1
<#
.SYNOPSIS
    Registers the InterViewCoding daily auto-commit scheduled task.
.DESCRIPTION
    Creates a Windows Scheduled Task that runs auto-commit-push.ps1 daily at
    8:00 PM local time, with missed-start recovery and logon safety net.
#>

$ErrorActionPreference = "Stop"

$TaskName = "InterViewCoding-DailyAutoCommit"
$ScriptPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\auto-commit-push.ps1"
$DailyTime = "20:00"
$TaskXmlPath = Join-Path $env:TEMP "InterViewCoding-DailyAutoCommit.xml"

if (-not (Test-Path $ScriptPath)) {
    throw "Script not found: $ScriptPath"
}

$escapedScript = $ScriptPath -replace '\\', '\\'
$command = "powershell.exe -NoProfile -ExecutionPolicy Bypass -File `"$ScriptPath`""

$xml = @"
<?xml version="1.0" encoding="UTF-16"?>
<Task version="1.4" xmlns="http://schemas.microsoft.com/windows/2004/02/mit/task">
  <RegistrationInfo>
    <Description>Daily auto git commit/push for InterViewCoding repository only.</Description>
  </RegistrationInfo>
  <Triggers>
    <CalendarTrigger>
      <StartBoundary>$(Get-Date -Format 'yyyy-MM-dd')T20:00:00</StartBoundary>
      <Enabled>true</Enabled>
      <ScheduleByDay>
        <DaysInterval>1</DaysInterval>
      </ScheduleByDay>
    </CalendarTrigger>
    <LogonTrigger>
      <Enabled>true</Enabled>
      <Delay>PT2M</Delay>
    </LogonTrigger>
  </Triggers>
  <Principals>
    <Principal id="Author">
      <LogonType>InteractiveToken</LogonType>
      <RunLevel>LeastPrivilege</RunLevel>
    </Principal>
  </Principals>
  <Settings>
    <MultipleInstancesPolicy>IgnoreNew</MultipleInstancesPolicy>
    <DisallowStartIfOnBatteries>false</DisallowStartIfOnBatteries>
    <StopIfGoingOnBatteries>false</StopIfGoingOnBatteries>
    <AllowHardTerminate>true</AllowHardTerminate>
    <StartWhenAvailable>true</StartWhenAvailable>
    <RunOnlyIfNetworkAvailable>false</RunOnlyIfNetworkAvailable>
    <Enabled>true</Enabled>
    <Hidden>false</Hidden>
    <RunOnlyIfIdle>false</RunOnlyIfIdle>
    <RestartOnFailure>
      <Interval>PT5M</Interval>
      <Count>2</Count>
    </RestartOnFailure>
    <AllowStartOnDemand>true</AllowStartOnDemand>
    <ExecutionTimeLimit>PT1H</ExecutionTimeLimit>
  </Settings>
  <Actions Context="Author">
    <Exec>
      <Command>powershell.exe</Command>
      <Arguments>-NoProfile -ExecutionPolicy Bypass -File "$ScriptPath"</Arguments>
    </Exec>
  </Actions>
</Task>
"@

$xml | Out-File -FilePath $TaskXmlPath -Encoding Unicode -Force

$existing = schtasks /Query /TN $TaskName 2>$null
if ($LASTEXITCODE -eq 0) {
    schtasks /Delete /TN $TaskName /F | Out-Null
    Write-Host "Removed existing task '$TaskName'."
}

schtasks /Create /TN $TaskName /XML $TaskXmlPath /F | Out-Null
if ($LASTEXITCODE -ne 0) {
    throw "Failed to register scheduled task '$TaskName'."
}

Remove-Item $TaskXmlPath -Force -ErrorAction SilentlyContinue

Write-Host "Scheduled task '$TaskName' registered successfully."
Write-Host "  Daily trigger:  $DailyTime (local time, with missed-start recovery)"
Write-Host "  Logon trigger:  2 minutes after user logon"
Write-Host "  Script:         $ScriptPath"
Write-Host ""
Write-Host "Test now:  schtasks /Run /TN `"$TaskName`""
Write-Host "Remove:    schtasks /Delete /TN `"$TaskName`" /F"
