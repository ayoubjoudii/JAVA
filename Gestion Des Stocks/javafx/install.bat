@echo off
echo ====================================
echo Installation de Gestion de Stock
echo ====================================
echo.
echo [1/4] Verification de MySQL...
mysql --version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: MySQL n'est pas installe ou n'est pas dans le PATH
    echo Veuillez installer MySQL et reessayer
    pause
    exit /b 1
)
echo MySQL detecte!
echo.
echo [2/4] Verification de Java...
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: Java n'est pas installe ou n'est pas dans le PATH
    echo Veuillez installer Java JDK 17 ou superieur et reessayer
    pause
    exit /b 1
)
echo Java detecte!
echo.
echo [3/4] Creation de la base de donnees...
echo Veuillez entrer votre mot de passe MySQL root (appuyez sur Entree si pas de mot de passe):
mysql -u root -p < database.sql
if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: Echec de la creation de la base de donnees
    echo Verifiez vos identifiants MySQL
    pause
    exit /b 1
)
echo Base de donnees creee avec succes!
echo.
echo [4/4] Compilation de l'application...
cd javafx
call mvnw.cmd clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: Echec de la compilation
    pause
    exit /b 1
)
cd ..
echo Application compilee avec succes!
echo.
echo ====================================
echo Installation terminee avec succes!
echo ====================================
echo.
echo Pour lancer l'application, executez: execute.bat
echo.
pause


