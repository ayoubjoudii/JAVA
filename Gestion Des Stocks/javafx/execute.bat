@echo off
echo ====================================
echo Lancement de Gestion de Stock
echo ====================================
echo.
if not exist "target\javafx-1.0-SNAPSHOT.jar" (
    echo ERREUR: L'application n'est pas compilee
    echo Veuillez executer install.bat d'abord
    pause
    exit /b 1
)
echo Verification de la connexion MySQL...
mysql -u root -e "USE gestion_stock;" >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ATTENTION: Impossible de se connecter a la base de donnees
    echo Assurez-vous que MySQL est demarre et que la base gestion_stock existe
    echo.
)
echo Demarrage de l'application...
echo.
cd javafx
call mvnw.cmd javafx:run
cd ..
pause


