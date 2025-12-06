# ================================================
# GESTION DE STOCK - SCRIPT D'EXECUTION PowerShell
# ================================================

Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "   GESTION DE STOCK - LANCEMENT APPLICATION" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Etape 1: Verification de Java
Write-Host "[1/5] Verification de Java..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1
    Write-Host "OK Java detecte: $($javaVersion[0])" -ForegroundColor Green
} catch {
    Write-Host "ERREUR: Java n'est pas installe ou n'est pas dans le PATH" -ForegroundColor Red
    Write-Host "Telechargez Java depuis: https://www.oracle.com/java/technologies/downloads/" -ForegroundColor Yellow
    pause
    exit 1
}

# Etape 2: Verification de la base de donnees
Write-Host ""
Write-Host "[2/5] Verification de la base de donnees..." -ForegroundColor Yellow
Write-Host "IMPORTANT: Assurez-vous que:" -ForegroundColor Magenta
Write-Host "  1. MySQL est en cours d'execution" -ForegroundColor White
Write-Host "  2. La base 'gestion_stock' est creee (via database.sql)" -ForegroundColor White
Write-Host "  3. Le mot de passe MySQL est correct dans src/dao/dbconnection.java" -ForegroundColor White
Write-Host ""
Write-Host "Pour importer la base de donnees:" -ForegroundColor Cyan
Write-Host "  - Option 1: Ouvrez MySQL Workbench et executez database.sql" -ForegroundColor White
Write-Host "  - Option 2: Utilisez: mysql -u root -p < database.sql" -ForegroundColor White
Write-Host ""
$response = Read-Host "Appuyez sur ENTREE pour continuer ou 'Q' pour quitter"
if ($response -eq 'Q' -or $response -eq 'q') { exit 0 }

# Etape 3: Verification du mot de passe MySQL
Write-Host ""
Write-Host "[3/5] Verification de la configuration..." -ForegroundColor Yellow
$dbConnectionFile = "src\dao\dbconnection.java"
if (Test-Path $dbConnectionFile) {
    $content = Get-Content $dbConnectionFile | Select-String "PASSWORD"
    Write-Host "Configuration MySQL actuelle:" -ForegroundColor Cyan
    Write-Host "$content" -ForegroundColor White
    Write-Host ""
    Write-Host "Si votre mot de passe MySQL n'est pas vide, modifiez dbconnection.java" -ForegroundColor Magenta
    $response = Read-Host "Continuer? (O/N)"
    if ($response -eq 'N' -or $response -eq 'n') { exit 0 }
} else {
    Write-Host "ERREUR: Fichier dbconnection.java introuvable!" -ForegroundColor Red
    pause
    exit 1
}

# Etape 4: Compilation
Write-Host ""
Write-Host "[4/5] Compilation du projet avec Maven..." -ForegroundColor Yellow
Write-Host "Cette etape peut prendre quelques minutes..." -ForegroundColor Gray

$compileProcess = Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "clean", "compile" -NoNewWindow -Wait -PassThru

if ($compileProcess.ExitCode -ne 0) {
    Write-Host "ERREUR: La compilation a echoue" -ForegroundColor Red
    Write-Host "Verifiez les erreurs ci-dessus et reessayez" -ForegroundColor Yellow
    pause
    exit 1
}

Write-Host "OK Compilation reussie!" -ForegroundColor Green

# Etape 5: Lancement
Write-Host ""
Write-Host "[5/5] Lancement de l'application..." -ForegroundColor Yellow
Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "    IDENTIFIANTS DE CONNEXION          " -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host "  Utilisateur: admin                   " -ForegroundColor White
Write-Host "  Mot de passe: admin                  " -ForegroundColor White
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

Write-Host "Demarrage en cours..." -ForegroundColor Cyan

# Lancer l'application
& .\mvnw.cmd javafx:run

Write-Host ""
Write-Host "Application fermee." -ForegroundColor Yellow
pause
