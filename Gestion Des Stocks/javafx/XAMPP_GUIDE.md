# 🚀 XAMPP - GUIDE DE DEMARRAGE RAPIDE

## ✅ VOUS AVEZ DEJA TOUT CE QU'IL FAUT!

- ✅ Java JDK installé
- ✅ Maven Wrapper présent (pas besoin d'installer Maven)
- ✅ **XAMPP installé (MySQL inclus)**

---

## 🎯 LANCEMENT EN 3 ETAPES SIMPLES

### 📍 ETAPE 1: Démarrer MySQL dans XAMPP

1. **Ouvrez XAMPP Control Panel**:
   - Cherchez "XAMPP" dans le menu Démarrer
   - OU Double-cliquez sur: `C:\xampp\xampp-control.exe`

2. **Démarrez MySQL**:
   - Cliquez sur le bouton **"Start"** à côté de **MySQL**
   - Attendez que le statut devienne vert
   - Vous devriez voir: "MySQL is running on port 3306"

---

### 📍 ETAPE 2: Importer la Base de Données

**Option A: Via phpMyAdmin (Plus Facile)**
1. Dans XAMPP Control Panel, cliquez sur **"Admin"** à côté de MySQL
2. Cela ouvre **phpMyAdmin** dans votre navigateur
3. Cliquez sur **"Importer"** (ou "Import") dans le menu du haut
4. Cliquez sur **"Choisir un fichier"** (Choose file)
5. Sélectionnez: `database.sql` (dans le dossier de votre projet)
6. Cliquez sur **"Exécuter"** (Go) en bas de la page
7. ✅ La base `gestion_stock` est créée!

**Option B: Via Ligne de Commande**
```powershell
C:\xampp\mysql\bin\mysql.exe -u root < database.sql
```

**Vérification**:
- Dans phpMyAdmin, vous devriez voir la base `gestion_stock`
- Elle contient 3 tables: `categories`, `products`, `users`

---

### 📍 ETAPE 3: Lancer l'Application

```powershell
.\run.ps1
```

**OU simplement double-cliquez sur**: `run.ps1`

---

## 🔐 IDENTIFIANTS

### MySQL (XAMPP par défaut):
- **Utilisateur**: `root`
- **Mot de passe**: *(vide - aucun mot de passe)*

### Application:
- **Utilisateur**: `admin`
- **Mot de passe**: `admin`

---

## ⚙️ CONFIGURATION ACTUELLE

Votre fichier `src\dao\dbconnection.java` est déjà configuré pour XAMPP:

```java
private static final String URL = "jdbc:mysql://localhost:3306/gestion_stock";
private static final String USER = "root";
private static final String PASSWORD = "";  // ✅ Vide = configuration XAMPP par défaut
```

**✅ Pas besoin de modifier quoi que ce soit!**

---

## 🎯 COMMANDE COMPLETE (Tout-en-un)

Ouvrez PowerShell dans le dossier du projet et exécutez:

```powershell
# Importer la base de données (une seule fois)
C:\xampp\mysql\bin\mysql.exe -u root < database.sql

# Lancer l'application
.\run.ps1
```

---

## 📊 VERIFIER L'IMPORTATION

Pour vérifier que la base de données est bien importée:

```powershell
# Se connecter à MySQL
C:\xampp\mysql\bin\mysql.exe -u root

# Dans MySQL, tapez:
USE gestion_stock;
SHOW TABLES;
SELECT COUNT(*) FROM products;  # Doit afficher: 18
SELECT COUNT(*) FROM categories;  # Doit afficher: 8
SELECT * FROM users;  # Doit afficher: admin
EXIT;
```

---

## ❌ RESOLUTION DES PROBLEMES

### Problème: "Can't connect to MySQL server"
**Solution**: 
1. Ouvrez XAMPP Control Panel
2. Cliquez sur "Start" à côté de MySQL
3. Attendez que le statut devienne vert

### Problème: MySQL ne démarre pas dans XAMPP
**Solutions**:
1. Vérifiez que le port 3306 n'est pas utilisé par un autre programme
2. Vérifiez les logs dans XAMPP (bouton "Logs")
3. Redémarrez XAMPP en mode administrateur

### Problème: "Access denied for user 'root'"
**Solution**: Dans XAMPP par défaut, il n'y a pas de mot de passe
- Si vous avez défini un mot de passe, modifiez `dbconnection.java` ligne 8

---

## 🎬 SCENARIO COMPLET

### Première Utilisation:
```powershell
# 1. Démarrer XAMPP Control Panel → Start MySQL

# 2. Importer la base (une seule fois)
C:\xampp\mysql\bin\mysql.exe -u root < database.sql

# 3. Lancer l'application
.\run.ps1

# 4. Connectez-vous: admin / admin
```

### Utilisations Suivantes:
```powershell
# 1. Démarrer MySQL dans XAMPP (si pas déjà démarré)

# 2. Lancer l'application
.\run.ps1
```

---

## 📦 STRUCTURE XAMPP

Votre installation XAMPP:
```
C:\xampp\
├── mysql\              # Serveur MySQL
│   ├── bin\
│   │   └── mysql.exe   # Client MySQL
│   └── data\           # Données des bases
├── phpMyAdmin\         # Interface web MySQL
└── xampp-control.exe   # Panneau de contrôle
```

---

## 🎓 AVANTAGES XAMPP POUR VOTRE PROJET

✅ Facile à démarrer/arrêter (via Control Panel)  
✅ phpMyAdmin inclus (interface graphique)  
✅ Pas de mot de passe par défaut (simplifie le développement)  
✅ Configuration automatique du port 3306  
✅ Parfait pour le développement local  

---

## 🚀 PRET A LANCER!

Vous avez TOUT ce qu'il faut:
- ✅ Java
- ✅ Maven (via Maven Wrapper)
- ✅ MySQL (via XAMPP)
- ✅ Code source complet
- ✅ Base de données prête

**Prochaine étape**:
1. Ouvrez XAMPP Control Panel
2. Démarrez MySQL (bouton Start)
3. Importez `database.sql` via phpMyAdmin
4. Lancez: `.\run.ps1`
5. Connectez-vous: `admin` / `admin`

**C'est tout! 🎉**

---

**Note**: Laissez XAMPP Control Panel ouvert pendant que vous utilisez l'application, car votre application a besoin de MySQL en cours d'exécution.
