# 🚀 Comment Exécuter le Système de Gestion de Stock

## ⚠️ PRÉREQUIS

### 1. **Java Development Kit (JDK)**
- ✅ **Installé**: Java 25 (JDK)
- ✅ **Installé**: Java Runtime 1.8.0_451

### 2. **MySQL Server**
- ⚠️ **Vérification nécessaire**: MySQL doit être installé et en cours d'exécution
- Version recommandée: MySQL 8.0+

### 3. **Maven** (Optionnel mais recommandé)
- ⚠️ **Non détecté**: Maven n'est pas dans le PATH système
- Alternative: Compilation manuelle avec javac

---

## 📊 ÉTAPE 1: Configuration de la Base de Données

### Option A: Via MySQL Workbench (Recommandé)
1. Ouvrez **MySQL Workbench**
2. Connectez-vous à votre serveur MySQL (généralement: `root` / votre mot de passe)
3. Cliquez sur **File** → **Open SQL Script**
4. Sélectionnez le fichier: `database.sql`
5. Cliquez sur l'icône ⚡ **Execute** (ou appuyez sur `Ctrl+Shift+Enter`)
6. Vérifiez que la base `gestion_stock` est créée avec les tables et données

### Option B: Via Ligne de Commande MySQL
Si MySQL est dans votre PATH système:
```cmd
mysql -u root -p < database.sql
```

Si MySQL n'est pas dans le PATH, utilisez le chemin complet:
```cmd
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p < database.sql
```

### Option C: Importation Manuelle
1. Ouvrez MySQL Command Line Client
2. Entrez votre mot de passe root
3. Copiez-collez le contenu de `database.sql` ligne par ligne
4. Vérifiez avec: `SHOW DATABASES;` puis `USE gestion_stock;` puis `SHOW TABLES;`

### 🔐 Vérification de la Base de Données
Après l'importation, vérifiez:
```sql
USE gestion_stock;
SELECT * FROM users;          -- Doit afficher 1 utilisateur (admin)
SELECT * FROM categories;     -- Doit afficher 8 catégories
SELECT * FROM products;       -- Doit afficher 18 produits
```

**Identifiants par défaut:**
- **Utilisateur**: `admin`
- **Mot de passe**: `admin`

---

## ⚙️ ÉTAPE 2: Configuration de la Connexion MySQL

Avant la compilation, vérifiez le fichier `src/dao/dbconnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/gestion_stock";
private static final String USER = "root";
private static final String PASSWORD = "";  // ⚠️ Modifiez avec votre mot de passe MySQL
```

**Si votre mot de passe MySQL n'est pas vide, modifiez la ligne:**
```java
private static final String PASSWORD = "votre_mot_de_passe";
```

---

## 🔧 ÉTAPE 3: Compilation du Projet

### Option A: Avec Maven (Recommandé)
Si Maven est installé:
```cmd
mvn clean install
mvn javafx:run
```

### Option B: Compilation Manuelle (Sans Maven)

#### 1. Télécharger les Dépendances Nécessaires
Téléchargez et placez ces fichiers JAR dans un dossier `lib/`:

**JavaFX SDK 21** (OpenJFX):
- Téléchargez depuis: https://openjfx.io/
- Extrayez et copiez les fichiers JAR du dossier `lib/` vers votre dossier `lib/`
- Fichiers nécessaires:
  - `javafx-controls-21.jar`
  - `javafx-fxml-21.jar`
  - `javafx-graphics-21.jar`
  - `javafx-base-21.jar`

**MySQL Connector/J 8.0.33**:
- Téléchargez depuis: https://dev.mysql.com/downloads/connector/j/
- Copiez `mysql-connector-java-8.0.33.jar` dans le dossier `lib/`

#### 2. Créer le Dossier `lib/`
```cmd
mkdir lib
```

#### 3. Compiler les Classes Java
```cmd
javac -cp "lib/*" -d bin --module-path "lib" --add-modules javafx.controls,javafx.fxml src/model/*.java src/dao/*.java src/controller/*.java src/main/*.java
```

**Explication des options:**
- `-cp "lib/*"`: Inclut toutes les bibliothèques du dossier `lib/`
- `-d bin`: Place les fichiers `.class` compilés dans le dossier `bin/`
- `--module-path "lib"`: Chemin vers les modules JavaFX
- `--add-modules javafx.controls,javafx.fxml`: Active les modules JavaFX nécessaires

#### 4. Copier les Fichiers FXML
```cmd
mkdir bin\view
copy src\view\*.fxml bin\view\
```

---

## ▶️ ÉTAPE 4: Exécution de l'Application

### Option A: Avec Maven
```cmd
mvn javafx:run
```

### Option B: Exécution Manuelle
```cmd
java -cp "bin;lib/*" --module-path "lib" --add-modules javafx.controls,javafx.fxml main.main
```

**Explication des options:**
- `-cp "bin;lib/*"`: Chemin des classes et bibliothèques
- `--module-path "lib"`: Chemin des modules JavaFX
- `--add-modules javafx.controls,javafx.fxml`: Modules JavaFX requis
- `main.main`: Classe principale à exécuter

### Option C: Via Script BAT (Simplifié)
Utilisez le script `execute.bat` fourni:
```cmd
execute.bat
```

---

## 🎯 ÉTAPE 5: Utilisation de l'Application

### 1. **Écran de Connexion**
- **Utilisateur**: `admin`
- **Mot de passe**: `admin`
- Cliquez sur **Connexion**

### 2. **Interface Principale - Gestion des Produits**

**📋 Tableau des Produits:**
- Affiche tous les produits avec leurs informations
- **Colonne Statut**:
  - 🟢 **Vert (En Stock)**: Quantité ≥ 5
  - 🔴 **Rouge (Rupture de Stock)**: Quantité < 5

**➕ Ajouter un Produit:**
1. Remplissez les champs: Nom, Description, Prix, Quantité
2. Sélectionnez une Catégorie
3. Cliquez sur **Ajouter**

**✏️ Modifier un Produit:**
1. Sélectionnez un produit dans le tableau (clic simple)
2. Les champs se remplissent automatiquement
3. Modifiez les informations souhaitées
4. Cliquez sur **Modifier**

**🗑️ Supprimer un Produit:**
1. Sélectionnez un produit dans le tableau
2. Cliquez sur **Supprimer**
3. Confirmez la suppression dans la boîte de dialogue

**🔍 Rechercher un Produit:**
- **Par Nom**: Tapez dans le champ "Rechercher par nom..."
- **Par Catégorie**: Sélectionnez une catégorie dans le menu déroulant
- **Réinitialiser**: Cliquez sur **Actualiser** pour afficher tous les produits

**📊 Statistiques en Temps Réel:**
- **Nombre Total de Produits**: Affiché en haut à gauche
- **Valeur Totale du Stock**: Calculée automatiquement (Prix × Quantité)

**🚪 Déconnexion:**
- Cliquez sur **Déconnexion** pour retourner à l'écran de connexion

---

## 🛠️ RÉSOLUTION DES PROBLÈMES

### ❌ Problème 1: "java.sql.SQLException: Access denied for user 'root'"
**Cause**: Mot de passe MySQL incorrect dans `dbconnection.java`

**Solution**:
1. Ouvrez `src/dao/dbconnection.java`
2. Modifiez la ligne:
   ```java
   private static final String PASSWORD = "votre_mot_de_passe_mysql";
   ```
3. Recompilez le projet

---

### ❌ Problème 2: "java.sql.SQLException: Unknown database 'gestion_stock'"
**Cause**: La base de données n'a pas été créée

**Solution**:
1. Vérifiez que MySQL est en cours d'exécution
2. Réimportez le fichier `database.sql` (voir ÉTAPE 1)
3. Vérifiez avec MySQL Workbench que la base `gestion_stock` existe

---

### ❌ Problème 3: "Error: JavaFX runtime components are missing"
**Cause**: JavaFX n'est pas inclus dans votre JDK

**Solution**:
1. Téléchargez JavaFX SDK depuis https://openjfx.io/
2. Ajoutez les modules avec `--module-path` et `--add-modules` (voir compilation manuelle)
3. Ou utilisez Maven qui gère automatiquement les dépendances

---

### ❌ Problème 4: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Cause**: Le driver MySQL n'est pas dans le classpath

**Solution**:
1. Vérifiez que `mysql-connector-java-8.0.33.jar` est dans le dossier `lib/`
2. Ajoutez `-cp "lib/*"` lors de la compilation et l'exécution
3. Ou utilisez Maven qui télécharge automatiquement le driver

---

### ❌ Problème 5: L'application se lance mais rien ne s'affiche
**Cause**: Fichiers FXML non trouvés ou mal placés

**Solution**:
1. Vérifiez que les fichiers `.fxml` sont dans `bin/view/` après compilation
2. Copiez manuellement:
   ```cmd
   copy src\view\login.fxml bin\view\
   copy src\view\products.fxml bin\view\
   ```
3. Vérifiez les chemins dans les contrôleurs

---

## 📦 ÉTAPE 6: Création d'un Package JAR (Optionnel)

Pour distribuer l'application sans recompilation:

### Avec Maven:
```cmd
mvn clean package
```
Le fichier JAR sera dans `target/javafx-1.0-SNAPSHOT.jar`

### Manuellement:
```cmd
jar cvfm GestionStock.jar MANIFEST.MF -C bin .
```

**Contenu de MANIFEST.MF:**
```
Manifest-Version: 1.0
Main-Class: main.main
Class-Path: lib/mysql-connector-java-8.0.33.jar lib/javafx-controls-21.jar lib/javafx-fxml-21.jar
```

**Exécution du JAR:**
```cmd
java --module-path "lib" --add-modules javafx.controls,javafx.fxml -jar GestionStock.jar
```

---

## 📚 COMMANDES RAPIDES - RÉSUMÉ

### 🗄️ Base de Données (MySQL Workbench):
1. Ouvrir `database.sql`
2. Exécuter le script (⚡)
3. Vérifier les tables

### 🔧 Compilation (Maven):
```cmd
mvn clean install
```

### ▶️ Exécution (Maven):
```cmd
mvn javafx:run
```

### 🔧 Compilation Manuelle:
```cmd
javac -cp "lib/*" -d bin --module-path "lib" --add-modules javafx.controls,javafx.fxml src/model/*.java src/dao/*.java src/controller/*.java src/main/*.java
copy src\view\*.fxml bin\view\
```

### ▶️ Exécution Manuelle:
```cmd
java -cp "bin;lib/*" --module-path "lib" --add-modules javafx.controls,javafx.fxml main.main
```

### 🚀 Exécution Simple (Script):
```cmd
execute.bat
```

---

## ✅ CHECKLIST DE DÉMARRAGE

- [ ] MySQL est installé et en cours d'exécution
- [ ] La base de données `gestion_stock` est créée (via `database.sql`)
- [ ] Les tables `users`, `products`, `categories` contiennent des données
- [ ] Le mot de passe MySQL est correct dans `dbconnection.java`
- [ ] Java JDK est installé (vérifié avec `javac -version`)
- [ ] Les dépendances sont disponibles (JavaFX + MySQL Connector)
- [ ] Le projet est compilé (dossier `bin/` contient les `.class`)
- [ ] Les fichiers FXML sont dans `bin/view/`
- [ ] L'application se lance correctement
- [ ] La connexion avec `admin`/`admin` fonctionne

---

## 🎓 INFORMATIONS PROJET

**Projet**: Système de Gestion de Stock  
**Niveau**: 2CS - Institut Supérieur d'Informatique  
**Technologies**: Java, JavaFX, MySQL  
**Architecture**: MVC (Model-View-Controller) + DAO  
**Identifiants**: admin / admin  

**Fonctionnalités**:
✅ Authentification  
✅ CRUD Complet (Ajouter, Modifier, Supprimer, Lister)  
✅ Recherche par nom et catégorie  
✅ Détection automatique des ruptures de stock (quantité < 5)  
✅ Statistiques en temps réel  
✅ Interface moderne avec codes couleurs  

---

## 📞 SUPPORT

En cas de problème persistant:
1. Vérifiez les logs de l'application dans la console
2. Consultez la section **RÉSOLUTION DES PROBLÈMES**
3. Vérifiez la documentation complète dans `DOCUMENTATION.md`
4. Consultez les diagrammes UML dans `DIAGRAMMES_UML.txt`

**Fichiers de documentation disponibles:**
- `README.md` - Guide utilisateur complet
- `DOCUMENTATION.md` - Documentation technique
- `DIAGRAMMES_UML.txt` - Diagrammes de conception
- `GUIDE_DEMARRAGE.md` - Guide de démarrage rapide
- `COMMENT_EXECUTER.txt` - Instructions d'exécution détaillées

---

**Bon développement! 🚀**
