#Requires -Version 5.1
<#
.SYNOPSIS
    Auto-commit and push changes for the InterViewCoding repository only.
.DESCRIPTION
    Appends a timestamp to auto-sync-log.md, commits all changes, pulls with
    rebase, and pushes. Supports retry on transient network failures.
.PARAMETER TriggerReason
    Logon, Wake, Unlock, or Manual — used in sync log and commit message.
#>

param(
    [ValidateSet("Logon", "Wake", "Unlock", "Manual")]
    [string]$TriggerReason = "Manual"
)

$ErrorActionPreference = "Stop"

$RepoPath = "C:\SUBAH\PROGRAMMING\InterViewCoding"
$ExpectedRemote = "git@github-interviewcoding:manishsubah/InterView-Coding-questions.git"
$Branch = "master"
$SyncLogFile = Join-Path $RepoPath "auto-sync-log.md"
$LogDir = Join-Path $RepoPath "logs"
$LogFile = Join-Path $LogDir "auto-commit.log"
$LockFile = Join-Path $env:TEMP "InterViewCoding-auto-commit.lock"
$MaxLogBytes = 1MB
$GitRetryAttempts = 3
$GitRetryDelaySeconds = 30

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

function Test-IsNetworkError {
    param([string]$Message)
    $patterns = @(
        "Could not resolve host",
        "Name or service not known",
        "Connection timed out",
        "Connection refused",
        "Network is unreachable",
        "Failed to connect",
        "ssh: connect to host"
    )
    foreach ($pattern in $patterns) {
        if ($Message -like "*$pattern*") {
            return $true
        }
    }
    return $false
}

function Invoke-GitWithRetry {
    param(
        [string[]]$GitArgs,
        [string]$OperationName
    )

    for ($attempt = 1; $attempt -le $GitRetryAttempts; $attempt++) {
        try {
            return Invoke-Git -GitArgs $GitArgs
        }
        catch {
            $isLast = ($attempt -eq $GitRetryAttempts)
            if ((Test-IsNetworkError -Message $_.Exception.Message) -and -not $isLast) {
                Write-Log "$OperationName failed (attempt $attempt/$GitRetryAttempts). Retrying in $GitRetryDelaySeconds s..." "WARN"
                Start-Sleep -Seconds $GitRetryDelaySeconds
            }
            else {
                throw
            }
        }
    }
}

function Add-SyncLogEntry {
    param([string]$Reason)

    $reasonLabel = $Reason.ToLowerInvariant()
    $entryTime = Get-Date -Format "yyyy-MM-dd HH:mm"
    $entryLine = "- $entryTime - synced on $reasonLabel"

    if (-not (Test-Path $SyncLogFile)) {
        @(
            "# Auto Sync Log",
            "",
            "Timestamped entries added automatically on Windows logon, wake, or unlock.",
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
        New-Item -ItemType Directory -Path $LockDir -Force | Out-Null
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
    Write-Log "Auto-commit started (trigger: $TriggerReason)."

    if (-not (Test-Path (Join-Path $RepoPath ".git"))) {
        throw "Not a git repository: $RepoPath"
    }

    $actualRemote = (Invoke-Git @("remote", "get-url", "origin") | Out-String).Trim()
    if ($actualRemote -ne $ExpectedRemote) {
        throw "Remote URL mismatch. Expected '$ExpectedRemote', got '$actualRemote'."
    }

    $syncTime = Add-SyncLogEntry -Reason $TriggerReason

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
            $commitMessage = "chore: auto sync on $($TriggerReason.ToLower()) $syncTime"
            Invoke-Git @("commit", "-m", $commitMessage) | Out-Null
            Write-Log "Committed: $commitMessage"
        }
    }
    elseif ($aheadCount -gt 0) {
        Write-Log "No working tree changes, but $aheadCount local commit(s) are ahead of remote. Retrying push."
    }

    Write-Log "Syncing with remote before push."
    try {
        Invoke-GitWithRetry -GitArgs @("pull", "--rebase", "origin", $Branch) -OperationName "Pull --rebase" | Out-Null
    }
    catch {
        Write-Log "Pull --rebase failed (possible merge conflict). Resolve manually and retry. $($_.Exception.Message)" "ERROR"
        exit 1
    }

    Invoke-GitWithRetry -GitArgs @("push", "origin", $Branch) -OperationName "Push" | Out-Null
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
