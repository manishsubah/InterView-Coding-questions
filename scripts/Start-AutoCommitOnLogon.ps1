#Requires -Version 5.1
<#
.SYNOPSIS
    Starts the auto-commit watcher and delayed logon sync at user sign-in.
#>

$WatcherPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\AutoCommitWatcher.ps1"
$DelayedPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\Start-AutoCommitDelayed.ps1"

Start-Process -FilePath "powershell.exe" `
    -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-WindowStyle", "Hidden", "-File", "`"$WatcherPath`"" `
    -WindowStyle Hidden

Start-Process -FilePath "powershell.exe" `
    -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-WindowStyle", "Hidden", "-File", "`"$DelayedPath`"" `
    -WindowStyle Hidden
