#Requires -Version 5.1
<#
.SYNOPSIS
    Registers the InterViewCoding auto-commit watcher and daily backup task.
.PARAMETER Unregister
    Remove scheduled tasks, Startup shortcut, and watcher lock file.
.PARAMETER DailyTime
    Daily backup time in HH:mm format (default 21:00 / 9 PM).
#>

param(
    [switch]$Unregister,
    [string]$DailyTime = "21:00"
)

$ErrorActionPreference = "Stop"

$RepoPath = "C:\SUBAH\PROGRAMMING\InterViewCoding"
$TaskName = "InterViewCoding-LogonAutoCommit"
$DailyTaskName = "InterViewCoding-DailyAutoCommit"
$OldTaskName = "InterViewCoding-DailyAutoCommit"
$WatcherPath = Join-Path $RepoPath "scripts\AutoCommitWatcher.ps1"
$LauncherPath = Join-Path $RepoPath "scripts\Start-AutoCommitOnLogon.ps1"
$SyncScript = Join-Path $RepoPath "scripts\Invoke-AutoCommitSync.ps1"
$StartupShortcutName = "InterViewCoding-AutoCommit.lnk"
$TaskXmlPath = Join-Path $env:TEMP "InterViewCoding-LogonAutoCommit.xml"
$WatcherLockFile = Join-Path $env:TEMP "InterViewCoding-watcher.lock"
$UserName = "$env:USERDOMAIN\$env:USERNAME"
$StartupFolder = [Environment]::GetFolderPath("Startup")
$StartupShortcutPath = Join-Path $StartupFolder $StartupShortcutName
$SshKeyPath = "C:/Users/manis/.ssh/id_ed25519_interviewcoding"
$SshCommand = "ssh -i $SshKeyPath -p 443 -o IdentitiesOnly=yes -o HostName=ssh.github.com"

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

function Remove-WatcherLock {
    if (Test-Path $WatcherLockFile) {
        Remove-Item $WatcherLockFile -Force -ErrorAction SilentlyContinue
        Write-Host "Removed watcher lock file."
    }
}

function Install-StartupShortcut {
    $shell = New-Object -ComObject WScript.Shell
    $shortcut = $shell.CreateShortcut($StartupShortcutPath)
    $shortcut.TargetPath = "powershell.exe"
    $shortcut.Arguments = "-WindowStyle Hidden -NoProfile -ExecutionPolicy Bypass -File `"$LauncherPath`""
    $shortcut.WorkingDirectory = $RepoPath
    $shortcut.Description = "InterViewCoding auto-commit watcher (logon, wake, unlock)"
    $shortcut.Save()
    Write-Host "Installed Startup shortcut: $StartupShortcutPath"
}

function Install-DailyBackupTask {
    $taskCommand = "powershell.exe -NoProfile -ExecutionPolicy Bypass -File `"$SyncScript`" -TriggerReason Manual -Force"
    $result = cmd /c "schtasks /Create /TN `"$DailyTaskName`" /TR `"$taskCommand`" /SC DAILY /ST $DailyTime /RL LIMITED /F" 2>&1
    if ($LASTEXITCODE -ne 0) {
        throw "Daily backup task registration failed: $result"
    }
    Write-Host "Installed daily backup task '$DailyTaskName' at $DailyTime."
}

function Install-MultiTriggerTask {
    if (-not (Test-Path $WatcherPath)) {
        throw "Watcher not found: $WatcherPath"
    }

    $xml = @"
<?xml version="1.0" encoding="UTF-16"?>
<Task version="1.4" xmlns="http://schemas.microsoft.com/windows/2004/02/mit/task">
  <RegistrationInfo>
    <Description>Auto-commit watcher for InterViewCoding (logon, wake, unlock).</Description>
  </RegistrationInfo>
  <Triggers>
    <LogonTrigger>
      <Enabled>true</Enabled>
      <Delay>PT1M</Delay>
    </LogonTrigger>
    <SessionStateChangeTrigger>
      <Enabled>true</Enabled>
      <StateChange>SessionUnlock</StateChange>
    </SessionStateChangeTrigger>
    <EventTrigger>
      <Enabled>true</Enabled>
      <Subscription>&lt;QueryList&gt;&lt;Query Id="0" Path="System"&gt;&lt;Select Path="System"&gt;*[System[Provider[@Name='Microsoft-Windows-Kernel-Power'] and EventID=507]]&lt;/Select&gt;&lt;/Query&gt;&lt;/QueryList&gt;</Subscription>
      <Delay>PT1M</Delay>
    </EventTrigger>
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
      <Arguments>-NoProfile -ExecutionPolicy Bypass -WindowStyle Hidden -File "$LauncherPath"</Arguments>
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

function Ensure-GitSshConfig {
    & git -C $RepoPath config core.sshCommand $SshCommand
    Write-Host "Configured git SSH over port 443 for this repo."
}

if ($Unregister) {
    Remove-TaskIfExists -Name $TaskName
    Remove-TaskIfExists -Name $DailyTaskName
    Remove-StartupShortcut
    Remove-WatcherLock
    Write-Host "Auto-commit watcher unregistered."
    exit 0
}

if (-not (Test-Path $WatcherPath)) {
    throw "Watcher not found: $WatcherPath"
}

Remove-TaskIfExists -Name $TaskName
Remove-TaskIfExists -Name $DailyTaskName
Remove-StartupShortcut
Remove-WatcherLock

Ensure-GitSshConfig

$registeredVia = @()

try {
    Install-MultiTriggerTask
    $registeredVia += "scheduled task '$TaskName'"
}
catch {
    Write-Host "Multi-trigger task not available ($($_.Exception.Message))."
}

try {
    Install-DailyBackupTask
    $registeredVia += "daily backup task '$DailyTaskName' at $DailyTime"
}
catch {
    Write-Host "Daily backup task not available ($($_.Exception.Message))."
}

Install-StartupShortcut
$registeredVia += "Startup shortcut '$StartupShortcutName'"

Write-Host ""
Write-Host "Auto-commit registered via: $($registeredVia -join ' + ')"
Write-Host "  Logon:     delayed sync (60s) + watcher timer (30s)"
Write-Host "  Wake:      power resume event"
Write-Host "  Unlock:    session unlock event"
Write-Host "  Fallback:  hourly check + daily task at $DailyTime"
Write-Host "  SSH:       port 443 (ssh.github.com)"
Write-Host ""
Write-Host "Test manual sync:  powershell -File `"$SyncScript`" -TriggerReason Manual -Force"
Write-Host "Remove:            powershell -File `"$PSCommandPath`" -Unregister"
