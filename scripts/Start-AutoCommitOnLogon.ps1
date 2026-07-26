#Requires -Version 5.1
<#
.SYNOPSIS
    Starts the background auto-commit watcher at user logon.
#>

$WatcherPath = "C:\SUBAH\PROGRAMMING\InterViewCoding\scripts\AutoCommitWatcher.ps1"

Start-Process -FilePath "powershell.exe" `
    -ArgumentList "-NoProfile", "-ExecutionPolicy", "Bypass", "-WindowStyle", "Hidden", "-File", "`"$WatcherPath`"" `
    -WindowStyle Hidden
