# Quick Launch Script - No Prompts
Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  GESTION DE STOCK - DEMARRAGE RAPIDE" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "Identifiants de connexion:" -ForegroundColor Yellow
Write-Host "  - Utilisateur: admin" -ForegroundColor White
Write-Host "  - Mot de passe: admin" -ForegroundColor White
Write-Host ""

Write-Host "Compilation et lancement..." -ForegroundColor Green
Write-Host "Premiere utilisation: cela peut prendre 1-2 minutes" -ForegroundColor Gray
Write-Host ""

# Compile first, then run
& .\mvnw.cmd clean compile javafx:run

Write-Host ""
Write-Host "Application fermee." -ForegroundColor Yellow
