#Requires -Version 5.1
<#
.SYNOPSIS
    Auto-commit and push changes for the InterViewCoding repository only.
.DESCRIPTION
    Triggered on Windows logon via Task Scheduler. Appends a timestamp to
    auto-sync-log.md, commits all changes, pulls with rebase, and pushes.
#>

$ErrorActionPreference = "Stop"

$RepoPath = "C:\SUBAH\PROGRAMMING\InterViewCoding"
$ExpectedRemote = "git@github-interviewcoding:manishsubah/InterView-Coding-questions.git"
$Branch = "master"
$SyncLogFile = Join-Path $RepoPath "auto-sync-log.md"
$LogDir = Join-Path $RepoPath "logs"
$LogFile = Join-Path $LogDir "auto-commit.log"
$LockFile = Join-Path $env:TEMP "InterViewCoding-auto-commit.lock"
$MaxLogBytes = 1MB

$env:GIT_TERMINAL_PROMPT = "0"

function Write-Log {
    param([string]$Message, [string]$Level = "INFO")
    $timestamp = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
    $line = "[$timestamp] [$Level] $Message"
    if (-not (Test-Path $LogDir)) {
        New-Item -ItemType Directory -Path $LogDir -Force | Out-Null
    }
    if ((Test-Path $LogFile) -and ((Get-Item $LogFile).Length -gt $MaxLogBytes)) {
        $archive = Join-Path $LogDir ("auto-commit-{0}.log" -f (Get-Date -Format "yyyyMMdd-HHmmss"))
        Move-Item -Path $LogFile -Destination $archive -Force
    }
    Add-Content -Path $LogFile -Value $line -Encoding UTF8
}

function Invoke-Git {
    param([string[]]$GitArgs)
    $prevErrorAction = $ErrorActionPreference
    $ErrorActionPreference = "Continue"
    $output = & git -C $RepoPath @GitArgs 2>&1
    $exitCode = $LASTEXITCODE
    $ErrorActionPreference = $prevErrorAction
    if ($exitCode -ne 0) {
        $detail = ($output | Out-String).Trim()
        throw "git $($GitArgs -join ' ') failed (exit $exitCode): $detail"
    }
    return $output
}

function Add-SyncLogEntry {
    $entryTime = Get-Date -Format "yyyy-MM-dd HH:mm"
    $entryLine = "- $entryTime - synced on logon"

    if (-not (Test-Path $SyncLogFile)) {
        @(
            "# Auto Sync Log",
            "",
            "Timestamped entries added automatically on Windows logon.",
            "",
            $entryLine
        ) | Set-Content -Path $SyncLogFile -Encoding UTF8
    }
    else {
        Add-Content -Path $SyncLogFile -Value $entryLine -Encoding UTF8
    }

    Write-Log "Appended sync log entry: $entryLine"
    return $entryTime
}

$lockHandle = $null
try {
    $lockDir = Split-Path $LockFile -Parent
    if (-not (Test-Path $lockDir)) {
        New-Item -ItemType Directory -Path $lockDir -Force | Out-Null
    }
    $lockHandle = [System.IO.File]::Open(
        $LockFile,
        [System.IO.FileMode]::OpenOrCreate,
        [System.IO.FileAccess]::ReadWrite,
        [System.IO.FileShare]::None
    )
}
catch {
    Write-Log "Another auto-commit instance is already running. Exiting." "WARN"
    exit 0
}

try {
    Write-Log "Auto-commit started."

    if (-not (Test-Path (Join-Path $RepoPath ".git"))) {
        throw "Not a git repository: $RepoPath"
    }

    $actualRemote = (Invoke-Git @("remote", "get-url", "origin") | Out-String).Trim()
    if ($actualRemote -ne $ExpectedRemote) {
        throw "Remote URL mismatch. Expected '$ExpectedRemote', got '$actualRemote'."
    }

    $syncTime = Add-SyncLogEntry

    $status = (Invoke-Git @("status", "--porcelain") | Out-String).Trim()
    $aheadCount = 0
    try {
        $tracking = (Invoke-Git @("rev-parse", "--abbrev-ref", "--symbolic-full-name", "@{u}") | Out-String).Trim()
        if ($tracking) {
            $aheadCount = [int](Invoke-Git @("rev-list", "--count", "$tracking..HEAD") | Out-String).Trim()
        }
    }
    catch {
        Write-Log "Could not determine upstream ahead count; continuing with working tree check only." "WARN"
    }

    if ([string]::IsNullOrWhiteSpace($status) -and $aheadCount -eq 0) {
        Write-Log "No changes detected after sync log append. Nothing to commit."
        exit 0
    }

    if (-not [string]::IsNullOrWhiteSpace($status)) {
        Write-Log "Changes detected. Staging and committing."
        Invoke-Git @("add", "-A") | Out-Null

        & git -C $RepoPath diff --cached --quiet
        if ($LASTEXITCODE -eq 0) {
            Write-Log "No staged changes after add. Nothing to commit."
        }
        else {
            $commitMessage = "chore: auto sync on logon $syncTime"
            Invoke-Git @("commit", "-m", $commitMessage) | Out-Null
            Write-Log "Committed: $commitMessage"
        }
    }
    elseif ($aheadCount -gt 0) {
        Write-Log "No working tree changes, but $aheadCount local commit(s) are ahead of remote. Retrying push."
    }

    Write-Log "Syncing with remote before push."
    try {
        Invoke-Git @("pull", "--rebase", "origin", $Branch) | Out-Null
    }
    catch {
        Write-Log "Pull --rebase failed (possible merge conflict). Resolve manually and retry. $($_.Exception.Message)" "ERROR"
        exit 1
    }

    Invoke-Git @("push", "origin", $Branch) | Out-Null
    Write-Log "Pushed to origin/$Branch successfully."
    exit 0
}
catch {
    Write-Log $_.Exception.Message "ERROR"
    exit 1
}
finally {
    if ($null -ne $lockHandle) {
        $lockHandle.Close()
        $lockHandle.Dispose()
    }
    if (Test-Path $LockFile) {
        Remove-Item $LockFile -Force -ErrorAction SilentlyContinue
    }
}
