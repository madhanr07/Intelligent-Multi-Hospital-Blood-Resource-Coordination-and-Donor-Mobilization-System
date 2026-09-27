# Load .env file and start the backend
$envPath = "..\.env"

if (Test-Path $envPath) {
    Get-Content $envPath | ForEach-Object {
        if ($_ -match '^\s*([^#=\s]+)\s*=\s*(.*)\s*$') {
            [Environment]::SetEnvironmentVariable($matches[1], $matches[2].Trim(), 'Process')
        }
    }
    Write-Host "Environment variables loaded from .env" -ForegroundColor Green
} else {
    Write-Host "Warning: .env file not found at $envPath" -ForegroundColor Yellow
    Write-Host "Using system environment variables or defaults" -ForegroundColor Yellow
}

# Start the backend
Write-Host "Starting HemoNexus Backend..." -ForegroundColor Cyan
mvn org.springframework.boot:spring-boot-maven-plugin:3.2.0:run
