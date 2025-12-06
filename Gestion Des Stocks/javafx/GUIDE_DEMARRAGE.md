# 🎯 GUIDE DE DÉMARRAGE RAPIDE - Gestion de Stock

## ✅ Checklist de Préparation

### Avant de commencer

- [ ] Java JDK 17+ installé
- [ ] MySQL 8.0+ installé et démarré
- [ ] Variables d'environnement configurées (JAVA_HOME, MySQL dans PATH)

## 🚀 Installation en 3 Étapes

### Étape 1 : Configuration MySQL

1. **Démarrer MySQL**

   ```bash
   # Windows - Services
   net start MySQL80

   # Ou via MySQL Workbench
   ```

2. **Importer la base de données**

   ```bash
   # Option 1 : Via ligne de commande
   mysql -u root -p < database.sql

   # Option 2 : Via MySQL Workbench
   # File → Run SQL Script → Sélectionner database.sql
   ```

3. **Vérifier la configuration**
   - Ouvrir `src/dao/dbconnection.java`
   - Modifier si nécessaire :
     ```java
     private static final String PASSWORD = "votre_mot_de_passe";
     ```

### Étape 2 : Compilation

```bash
cd javafx
mvnw.cmd clean package
```

### Étape 3 : Exécution

```bash
# Méthode 1 : Script batch (recommandé)
execute.bat

# Méthode 2 : Maven
mvnw.cmd javafx:run

# Méthode 3 : Via IDE
# Exécuter la classe main.main
```

## 🔐 Connexion

**Compte par défaut :**

- Utilisateur : `admin`
- Mot de passe : `admin`

## 📋 Fonctionnalités Disponibles

### 1️⃣ Gestion des Produits

- ➕ Ajouter un produit
- ✏️ Modifier un produit
- 🗑️ Supprimer un produit
- 👁️ Consulter tous les produits

### 2️⃣ Recherche et Filtrage

- 🔍 Rechercher par nom
- 🏷️ Filtrer par catégorie
- 📊 Voir les statistiques

### 3️⃣ Alertes

- 🚨 Détection automatique de rupture de stock (quantité < 5)
- 🎨 Code couleur : vert (disponible) / rouge (rupture)

## 🎨 Interface

### Écran de Connexion

![Login Screen]

- Design moderne avec gradient
- Validation en temps réel
- Messages d'erreur clairs

### Écran Principal

![Main Screen]

- Tableau interactif
- Barre de recherche et filtres
- Statistiques en temps réel
- Boutons d'action colorés

## 📊 Données de Test

### Catégories Pré-chargées (8)

- Électronique
- Alimentaire
- Vêtements
- Maison & Jardin
- Sport
- Livres
- Jouets
- Bureautique

### Produits Exemples (18)

- 15 produits disponibles
- 3 produits en rupture de stock
- Prix variés de 300 DZD à 85,000 DZD

## 🐛 Résolution de Problèmes

### Problème : "Cannot connect to database"

**Solution :**

1. Vérifier que MySQL est démarré
2. Vérifier le mot de passe dans `dbconnection.java`
3. Vérifier que la base `gestion_stock` existe

### Problème : "JavaFX runtime components missing"

**Solution :**

- Ne pas utiliser `java -jar`
- Utiliser `mvnw.cmd javafx:run`

### Problème : "Class not found"

**Solution :**

1. Nettoyer et recompiler : `mvnw.cmd clean compile`
2. Vérifier la structure des packages

### Problème : Caractères spéciaux mal affichés

**Solution :**

- Vérifier l'encodage UTF-8 dans l'IDE
- La base de données utilise utf8mb4

## 📁 Structure du Projet

```
javafx/
├── src/
│   ├── controller/     # Contrôleurs MVC
│   ├── dao/           # Accès aux données
│   ├── model/         # Entités métier
│   ├── view/          # Interfaces FXML
│   └── main/          # Point d'entrée
├── database.sql       # Script SQL
├── install.bat        # Installation
├── execute.bat        # Exécution
├── README.md          # Documentation complète
├── DOCUMENTATION.md   # Doc technique
└── DIAGRAMMES_UML.txt # Diagrammes UML
```

## 📝 Concepts POO Appliqués

✅ **Encapsulation** : Attributs privés, getters/setters  
✅ **Modularité** : Packages séparés (model, view, controller, dao)  
✅ **Extensibilité** : Architecture MVC  
✅ **Réutilisabilité** : Classes génériques  
✅ **Documentation** : Javadoc complet

## 🎓 Points Pédagogiques

1. **Architecture MVC** : Séparation claire des responsabilités
2. **Pattern DAO** : Abstraction de l'accès aux données
3. **Pattern Singleton** : Connexion unique à la BDD
4. **JDBC** : Communication avec MySQL
5. **JavaFX** : Interface graphique moderne
6. **SQL** : Requêtes optimisées avec JOIN
7. **Exception Handling** : Gestion robuste des erreurs

## 📦 Livraison du Projet

### Fichiers à Rendre

1. ✅ Code source complet (dossier `javafx/`)
2. ✅ Fichier `database.sql`
3. ✅ Fichiers batch (`install.bat`, `execute.bat`)
4. ✅ Documentation (`README.md`, `DOCUMENTATION.md`)
5. ✅ Diagrammes UML (`DIAGRAMMES_UML.txt`)
6. ✅ Fichier JAR compilé (dans `target/`)

### Format de Soumission

```
GestionStock_[VosNoms].zip
├── javafx/               # Code source
├── database.sql          # Base de données
├── install.bat           # Installation
├── execute.bat           # Exécution
├── README.md             # Documentation
├── DOCUMENTATION.md      # Doc technique
├── DIAGRAMMES_UML.txt    # Diagrammes
└── rapport.pdf          # (Optionnel) Rapport détaillé
```

## 🌟 Fonctionnalités Bonus

- ✅ Interface moderne avec CSS
- ✅ Validation des données
- ✅ Confirmations avant suppression
- ✅ Messages d'erreur explicites
- ✅ Statistiques en temps réel
- ✅ Code couleur pour les statuts
- ✅ Recherche avancée
- ✅ Filtrage multi-critères

## 📞 Support

**En cas de problème :**

1. Consulter la section "Résolution de Problèmes" ci-dessus
2. Vérifier les logs dans la console
3. Consulter `DOCUMENTATION.md` pour les détails techniques
4. Contacter l'enseignant responsable

## 🎉 Félicitations !

Vous avez maintenant une application complète de gestion de stock avec :

- ✅ Architecture professionnelle (MVC)
- ✅ Interface graphique moderne
- ✅ Base de données relationnelle
- ✅ Code documenté et maintenable
- ✅ Respect des principes POO

**Bonne chance pour votre présentation ! 🚀**

---

© 2025 Institut Supérieur d'Informatique - Mini-Projet POO - 2CS
