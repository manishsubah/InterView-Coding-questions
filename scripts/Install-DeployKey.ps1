#Requires -Version 5.1
<#
.SYNOPSIS
    Adds the InterViewCoding SSH deploy key to GitHub (one-time setup).
.DESCRIPTION
    Requires GitHub CLI (gh) logged in with repo admin access:
      gh auth login
#>

$ErrorActionPreference = "Stop"

$Repo = "manishsubah/InterView-Coding-questions"
$KeyTitle = "auto-commit-InterViewCoding"
$PublicKeyPath = Join-Path $env:USERPROFILE ".ssh\id_ed25519_interviewcoding.pub"

if (-not (Test-Path $PublicKeyPath)) {
    throw "Public key not found: $PublicKeyPath. Run ssh-keygen first."
}

$gh = Get-Command gh -ErrorAction SilentlyContinue
if (-not $gh) {
    throw "GitHub CLI (gh) is not installed. Install from https://cli.github.com/"
}

$authStatus = & gh auth status 2>&1
if ($LASTEXITCODE -ne 0) {
    throw "GitHub CLI is not authenticated. Run: gh auth login"
}

$publicKey = (Get-Content $PublicKeyPath -Raw).Trim()
$existing = & gh api "repos/$Repo/keys" --jq ".[] | select(.title==`"$KeyTitle`") | .id" 2>$null
if ($existing) {
    Write-Host "Deploy key '$KeyTitle' already exists (id: $existing). Skipping."
}
else {
    & gh api "repos/$Repo/keys" `
        -f title="$KeyTitle" `
        -f key="$publicKey" `
        -F read_only=false
    if ($LASTEXITCODE -ne 0) {
        throw "Failed to add deploy key to $Repo"
    }
    Write-Host "Deploy key '$KeyTitle' added to $Repo with write access."
}

Write-Host "Testing SSH connection..."
ssh -o BatchMode=yes -T git@github-interviewcoding 2>&1 | ForEach-Object { Write-Host $_ }
if ($LASTEXITCODE -eq 1) {
    Write-Host "SSH authentication successful (GitHub returns exit code 1 for successful shell test)."
}
elseif ($LASTEXITCODE -ne 0) {
    throw "SSH test failed. Verify deploy key and ~/.ssh/config."
}
