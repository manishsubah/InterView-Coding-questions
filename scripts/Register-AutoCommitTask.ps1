#Requires -Version 5.1
<#
.SYNOPSIS
    Registers the InterViewCoding logon auto-commit trigger.
.DESCRIPTION
    Tries a Windows Scheduled Task at logon first. If that is blocked,
    falls back to a Startup folder shortcut (runs when you sign in).
.PARAMETER Unregister
    Remove the scheduled task and Startup shortcut.
#>

param(
    [switch]$Unregister
)

$ErrorActionPreference = "Stop"

$TaskName = "InterViewCoding-LogonAutoCommit"
$OldTaskName = "InterViewCoding-DailyAutoCommit"
$LauncherPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\Start-AutoCommitOnLogon.ps1"
$ScriptPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\auto-commit-push.ps1"
$StartupShortcutName = "InterViewCoding-AutoCommit.lnk"
$TaskXmlPath = Join-Path $env:TEMP "InterViewCoding-LogonAutoCommit.xml"
$UserName = "$env:USERDOMAIN\$env:USERNAME"
$StartupFolder = [Environment]::GetFolderPath("Startup")
$StartupShortcutPath = Join-Path $StartupFolder $StartupShortcutName

function Remove-TaskIfExists {
    param([string]$Name)
    $null = cmd /c "schtasks /Query /TN `"$Name`" 2>nul"
    if ($LASTEXITCODE -eq 0) {
        schtasks /Delete /TN $Name /F | Out-Null
        Write-Host "Removed scheduled task '$Name'."
    }
}

function Remove-StartupShortcut {
    if (Test-Path $StartupShortcutPath) {
        Remove-Item $StartupShortcutPath -Force
        Write-Host "Removed Startup shortcut '$StartupShortcutName'."
    }
}

function Install-StartupShortcut {
    $shell = New-Object -ComObject WScript.Shell
    $shortcut = $shell.CreateShortcut($StartupShortcutPath)
    $shortcut.TargetPath = "powershell.exe"
    $shortcut.Arguments = "-WindowStyle Hidden -NoProfile -ExecutionPolicy Bypass -File `"$LauncherPath`""
    $shortcut.WorkingDirectory = "C:\SUBAH\PROGRAMMING\InterViewCoding"
    $shortcut.Description = "InterViewCoding logon auto-commit (2 min delay)"
    $shortcut.Save()
    Write-Host "Installed Startup shortcut: $StartupShortcutPath"
}

function Install-LogonScheduledTask {
    if (-not (Test-Path $LauncherPath)) {
        throw "Launcher not found: $LauncherPath"
    }

    $xml = @"
<?xml version="1.0" encoding="UTF-16"?>
<Task version="1.4" xmlns="http://schemas.microsoft.com/windows/2004/02/mit/task">
  <RegistrationInfo>
    <Description>Logon auto git commit/push for InterViewCoding repository only.</Description>
  </RegistrationInfo>
  <Triggers>
    <LogonTrigger>
      <Enabled>true</Enabled>
      <Delay>PT2M</Delay>
    </LogonTrigger>
  </Triggers>
  <Principals>
    <Principal id="Author">
      <UserId>$UserName</UserId>
      <LogonType>InteractiveToken</LogonType>
      <RunLevel>LeastPrivilege</RunLevel>
    </Principal>
  </Principals>
  <Settings>
    <MultipleInstancesPolicy>IgnoreNew</MultipleInstancesPolicy>
    <DisallowStartIfOnBatteries>false</DisallowStartIfOnBatteries>
    <StopIfGoingOnBatteries>false</StopIfGoingOnBatteries>
    <AllowHardTerminate>true</AllowHardTerminate>
    <Enabled>true</Enabled>
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
      <Arguments>-NoProfile -ExecutionPolicy Bypass -File "$LauncherPath"</Arguments>
    </Exec>
  </Actions>
</Task>
"@

    $xml | Out-File -FilePath $TaskXmlPath -Encoding Unicode -Force
    $result = schtasks /Create /TN $TaskName /XML $TaskXmlPath /F 2>&1
    Remove-Item $TaskXmlPath -Force -ErrorAction SilentlyContinue

    if ($LASTEXITCODE -ne 0) {
        throw "Scheduled task registration failed: $result"
    }
}

if ($Unregister) {
    Remove-TaskIfExists -Name $TaskName
    Remove-TaskIfExists -Name $OldTaskName
    Remove-StartupShortcut
    Write-Host "Logon auto-commit unregistered."
    exit 0
}

if (-not (Test-Path $LauncherPath)) {
    throw "Launcher not found: $LauncherPath"
}

Remove-TaskIfExists -Name $OldTaskName
Remove-TaskIfExists -Name $TaskName
Remove-StartupShortcut

$registeredVia = $null
try {
    Install-LogonScheduledTask
    $registeredVia = "scheduled task '$TaskName'"
}
catch {
    Write-Host "Scheduled task logon trigger not available ($($_.Exception.Message))."
    Write-Host "Using Startup folder fallback instead."
    Install-StartupShortcut
    $registeredVia = "Startup shortcut '$StartupShortcutName'"
}

Write-Host ""
Write-Host "Logon auto-commit registered via $registeredVia."
Write-Host "  Trigger:   At sign-in (2 minute delay for network)"
Write-Host "  Launcher:  $LauncherPath"
Write-Host "  Script:    $ScriptPath"
Write-Host ""
Write-Host "Test now:  powershell -File `"$ScriptPath`""
Write-Host "Remove:    powershell -File `"$PSCommandPath`" -Unregister"
