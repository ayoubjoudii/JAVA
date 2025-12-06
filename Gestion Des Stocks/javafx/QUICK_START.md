# 🚀 DEMARRAGE RAPIDE - 3 ETAPES

## ⚡ METHODE RAPIDE (Recommandée)

### Etape 1: Importer la Base de Données
1. Ouvrez **MySQL Workbench**
2. Connectez-vous (utilisateur: `root`)
3. Ouvrez le fichier `database.sql` (File → Open SQL Script)
4. Exécutez le script (cliquez sur l'éclair ⚡ ou `Ctrl+Shift+Enter`)

### Etape 2: Vérifier le Mot de Passe MySQL
Si votre mot de passe MySQL **n'est pas vide**:
1. Ouvrez `src\dao\dbconnection.java`
2. Ligne 9, modifiez:
   ```java
   private static final String PASSWORD = "votre_mot_de_passe";
   ```

### Etape 3: Lancer l'Application
**Double-cliquez sur:** `run.ps1` (PowerShell) OU `run.bat` (Windows)

**Ou en ligne de commande:**
```powershell
.\run.ps1
```

---

## 🔐 CONNEXION

Une fois l'application lancée:
- **Utilisateur**: `admin`
- **Mot de passe**: `admin`

---

## 🛠️ METHODES ALTERNATIVES

### Méthode 1: Maven Wrapper (Manuelle)
```cmd
.\mvnw.cmd clean compile
.\mvnw.cmd javafx:run
```

### Méthode 2: Compilation Manuelle
```cmd
# 1. Créer dossiers
mkdir bin
mkdir lib

# 2. Télécharger JavaFX SDK et MySQL Connector dans lib/

# 3. Compiler
javac -cp "lib/*" -d bin --module-path "lib" --add-modules javafx.controls,javafx.fxml src/model/*.java src/dao/*.java src/controller/*.java src/main/*.java

# 4. Copier FXML
mkdir bin\view
copy src\view\*.fxml bin\view\

# 5. Exécuter
java -cp "bin;lib/*" --module-path "lib" --add-modules javafx.controls,javafx.fxml main.main
```

---

## ❌ PROBLEMES FREQUENTS

### "Access denied for user 'root'"
→ Modifiez le mot de passe dans `src\dao\dbconnection.java`

### "Unknown database 'gestion_stock'"
→ Réimportez `database.sql` dans MySQL Workbench

### "JavaFX runtime components are missing"
→ Utilisez Maven Wrapper (méthode recommandée)

### L'application ne se lance pas
→ Vérifiez que MySQL est démarré
→ Vérifiez les logs dans la console

---

## 📚 DOCUMENTATION COMPLETE

Consultez les fichiers suivants pour plus de détails:
- `RUN_INSTRUCTIONS.md` - Instructions complètes d'exécution
- `README.md` - Guide utilisateur
- `DOCUMENTATION.md` - Documentation technique
- `COMMENT_EXECUTER.txt` - Guide pas à pas

---

## ✅ CHECKLIST AVANT DE LANCER

- [ ] MySQL installé et démarré
- [ ] Base `gestion_stock` créée (via `database.sql`)
- [ ] Mot de passe MySQL configuré dans `dbconnection.java`
- [ ] Java JDK installé (version 8+)
- [ ] Maven Wrapper présent (`mvnw.cmd`)

**Tout est prêt? Lancez:** `.\run.ps1` 🚀
