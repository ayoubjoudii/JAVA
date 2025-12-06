@echo off
echo ================================================
echo    GESTION DE STOCK - SCRIPT D'EXECUTION
echo ================================================
echo.

echo [1/4] Verification de Java...
java -version
if %errorlevel% neq 0 (
    echo ERREUR: Java n'est pas installe ou n'est pas dans le PATH
    pause
    exit /b 1
)
echo.

echo [2/4] Verification de MySQL...
echo ATTENTION: Assurez-vous que MySQL est en cours d'execution
echo           et que la base de donnees 'gestion_stock' est creee.
echo.
echo Pour importer la base de donnees:
echo 1. Ouvrez MySQL Workbench
echo 2. Executez le fichier database.sql
echo.
pause

echo [3/4] Compilation avec Maven Wrapper...
call mvnw.cmd clean compile
if %errorlevel% neq 0 (
    echo ERREUR: La compilation a echoue
    pause
    exit /b 1
)
echo.

echo [4/4] Lancement de l'application...
echo.
echo Identifiants de connexion:
echo - Utilisateur: admin
echo - Mot de passe: admin
echo.
call mvnw.cmd javafx:run

pause
