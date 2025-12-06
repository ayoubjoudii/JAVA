@echo off
cls
echo ================================================
echo    GESTION DE STOCK - LANCEMENT RAPIDE
echo ================================================
echo.
echo [INFO] Assurez-vous que MySQL tourne dans XAMPP!
echo.
echo Demarrage de l'application...
echo.
echo Identifiants:
echo   - Utilisateur: admin
echo   - Mot de passe: admin
echo.
echo ================================================
echo.

call mvnw.cmd clean compile javafx:run

echo.
echo Application fermee.
pause
