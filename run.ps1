param (
    [string]$action
)

function Show-Help {
    Write-Host "Usage:"
    Write-Host "  .\run.ps1 <action>"
    Write-Host ""
    Write-Host "Actions:"
    Write-Host "  clean     - Remove only MAPS-specific Docker containers and images"
    Write-Host "  prune     - Prune all Docker system data including volumes"
    Write-Host "  db        - Navigate to the ./backend directory and run 'docker compose up postgres"
    Write-Host "  backend   - Navigate to the ./backend directory and run 'docker compose up'"
    Write-Host "  frontend  - Navigate to the ./frontend directory and run 'npm run dev'"
    Write-Host "  restart   - Clean, navigate to the ./backend directory, and run 'docker compose up'"
    Write-Host "  help      - Show this help message"
}

switch ($action) {
    "clean" {
        Write-Host "Removing specific Docker containers and images..."
        docker rm -f spring-boot-app postgresql
        docker rmi -f backend-spring-boot-app postgres
    }
    "prune" {
        Write-Host "Pruning all Docker system data including volumes..."
        docker system prune -a --volumes -f
    }
    "db" {
        Write-Host "Navigating to the ./backend directory and running 'docker compose up postgres'..."
        Set-Location -Path "./backend"
        docker compose up postgres
    }
    "backend" {
        Write-Host "Navigating to the ./backend directory and running 'docker compose up'..."
        Set-Location -Path "./backend"
        docker compose up
    }
    "frontend" {
        Write-Host "Navigating to the ./frontend directory and running 'npm run dev'..."
        Set-Location -Path "./frontend"
        npm run dev
    }
    "restart" {
        Write-Host "Cleaning, navigating to the ./backend directory, and running 'docker compose up'..."
        docker rm -f spring-boot-app postgresql
        docker rmi -f backend-spring-boot-app postgres
        Set-Location -Path "./backend"
        docker compose up
    }
    "help" {
        Show-Help
    }
    default {
        Write-Host "Invalid action specified."
        Show-Help
    }
}