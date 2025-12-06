# Système de Gestion de Stock

**Mini-Projet Programmation Orientée Objet - 2CS**  
**Institut Supérieur d'Informatique**

---

## 📋 Description du Projet

Application desktop de gestion de stock développée en Java avec JavaFX, suivant l'architecture MVC (Model-View-Controller). Cette application permet de gérer les produits d'un petit magasin avec une interface graphique moderne et intuitive.

---

## ✨ Fonctionnalités Principales

1. **Authentification** : Système de connexion avec login et mot de passe
2. **Gestion des Produits** :
   - Consulter la liste complète des produits
   - Ajouter de nouveaux produits
   - Modifier les informations des produits
   - Supprimer des produits
3. **Recherche et Filtrage** :
   - Rechercher un produit par nom
   - Filtrer les produits par catégorie
4. **Gestion des Statuts** :
   - Affichage automatique du statut "Rupture de stock" si quantité < 5
   - Mise en évidence visuelle des produits en rupture
5. **Statistiques** :
   - Nombre total de produits
   - Valeur totale du stock
6. **Interface Moderne** : Design coloré et ergonomique avec JavaFX

---

## 🏗️ Architecture

Le projet suit l'architecture **MVC** :

### Model (Modèle)

- `Product.java` : Représente un produit
- `Category.java` : Représente une catégorie
- `User.java` : Représente un utilisateur

### View (Vue)

- `login.fxml` : Interface de connexion
- `products.fxml` : Interface de gestion des produits

### Controller (Contrôleur)

- `logincontroller.java` : Gère la logique de connexion
- `productcontroller.java` : Gère la logique des produits

### DAO (Data Access Object)

- `dbconnection.java` : Gestion de la connexion à MySQL
- `userdao.java` : Opérations sur les utilisateurs
- `productdao.java` : Opérations CRUD sur les produits

---

## 🛠️ Technologies Utilisées

- **Langage** : Java 17+
- **Interface Graphique** : JavaFX 21
- **Base de Données** : MySQL 8.0
- **Build Tool** : Maven
- **JDBC** : MySQL Connector/J 8.0.33
- **IDE Recommandé** : IntelliJ IDEA / Eclipse / NetBeans

---

## 📦 Structure du Projet

```
javafx/
├── src/
│   ├── controller/          # Contrôleurs MVC
│   │   ├── logincontroller.java
│   │   └── productcontroller.java
│   ├── dao/                 # Couche d'accès aux données
│   │   ├── dbconnection.java
│   │   ├── userdao.java
│   │   └── productdao.java
│   ├── model/               # Modèles de données
│   │   ├── Product.java
│   │   ├── Category.java
│   │   └── User.java
│   ├── view/                # Vues FXML
│   │   ├── login.fxml
│   │   └── products.fxml
│   └── main/                # Point d'entrée
│       └── main.java
├── database.sql             # Script de création de la BDD
├── install.bat              # Script d'installation
├── execute.bat              # Script d'exécution
└── pom.xml                  # Configuration Maven
```

---

## 🚀 Installation et Exécution

### Prérequis

1. **Java JDK 17 ou supérieur**

   - Télécharger : https://www.oracle.com/java/technologies/downloads/
   - Vérifier : `java -version`

2. **MySQL 8.0 ou supérieur**

   - Télécharger : https://dev.mysql.com/downloads/mysql/
   - Démarrer le service MySQL

3. **Maven** (inclus avec le projet via mvnw)

### Installation

1. **Cloner ou extraire le projet**

2. **Exécuter le script d'installation** :

   ```bash
   install.bat
   ```

   Ce script va :

   - Vérifier les prérequis (Java, MySQL)
   - Créer la base de données `gestion_stock`
   - Insérer les données de test
   - Compiler l'application

3. **Configuration de la base de données** (si nécessaire) :
   - Ouvrir `src/dao/dbconnection.java`
   - Modifier les paramètres de connexion :
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/gestion_stock";
     private static final String USER = "root";
     private static final String PASSWORD = ""; // Votre mot de passe MySQL
     ```

### Exécution

**Méthode 1 : Via le script batch**

```bash
execute.bat
```

**Méthode 2 : Via Maven**

```bash
cd javafx
mvnw javafx:run
```

**Méthode 3 : Via un IDE**

- Exécuter la classe `main.main`

---

## 👤 Connexion par Défaut

- **Nom d'utilisateur** : `admin`
- **Mot de passe** : `admin`

---

## 📊 Base de Données

### Tables

1. **users** : Stocke les utilisateurs
2. **categories** : Stocke les catégories de produits
3. **products** : Stocke les produits

### Catégories par Défaut

- Électronique
- Alimentaire
- Vêtements
- Maison & Jardin
- Sport
- Livres
- Jouets
- Bureautique

### Produits Exemples

Le script SQL insère 18 produits d'exemple dans différentes catégories.

---

## 🎨 Captures d'Écran

### Écran de Connexion

- Interface moderne avec gradient coloré
- Validation des champs
- Messages d'erreur clairs

### Écran de Gestion des Produits

- Tableau avec toutes les informations
- Barre de recherche et filtres
- Boutons d'action (Ajouter, Modifier, Supprimer)
- Statistiques en temps réel
- Code couleur pour les statuts (vert = disponible, rouge = rupture)

---

## 📝 Concepts POO Utilisés

### 1. Encapsulation

- Tous les attributs sont privés
- Accès via getters/setters
- Validation dans les setters

### 2. Modularité

- Séparation en packages (model, view, controller, dao)
- Classes avec responsabilités uniques

### 3. Extensibilité

- Architecture MVC facilite l'ajout de fonctionnalités
- DAO pattern pour changer facilement de BDD

### 4. Réutilisabilité

- Classes génériques réutilisables
- Méthodes utilitaires

### 5. Documentation

- Javadoc sur toutes les classes et méthodes publiques
- Commentaires explicatifs

---

## 📚 Diagrammes UML

### Diagramme de Cas d'Utilisation

```
Acteur : Utilisateur

Cas d'utilisation :
1. Se connecter
2. Consulter la liste des produits
3. Ajouter un produit
4. Modifier un produit
5. Supprimer un produit
6. Rechercher un produit
7. Filtrer par catégorie
8. Consulter les statistiques
```

### Diagramme de Classes (Simplifié)

```
User
- id: int
- username: String
- password: String
- fullName: String
- role: String

Category
- id: int
- name: String
- description: String

Product
- id: int
- name: String
- description: String
- price: double
- quantity: int
- categoryId: int
- status: String
+ getTotalValue(): double
+ updateStatus(): void

userdao
+ authenticate(username, password): User
+ addUser(user): boolean
+ getAllUsers(): List<User>

productdao
+ getAllProducts(): List<Product>
+ getProductsByCategory(id): List<Product>
+ searchProducts(term): List<Product>
+ addProduct(product): boolean
+ updateProduct(product): boolean
+ deleteProduct(id): boolean
+ getTotalProductCount(): int
+ getTotalStockValue(): double
```

---

## 🔧 Dépendances Maven

```xml
<!-- JavaFX -->
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21.0.6</version>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

---

## 🐛 Résolution des Problèmes

### Erreur de connexion à MySQL

- Vérifier que MySQL est démarré
- Vérifier les identifiants dans `dbconnection.java`
- Vérifier que la base `gestion_stock` existe

### Erreur JavaFX

- Vérifier la version de Java (minimum 17)
- Nettoyer et recompiler : `mvnw clean compile`

### Problème d'affichage FXML

- Vérifier que les fichiers FXML sont dans `src/view/`
- Vérifier les noms de contrôleurs dans les fichiers FXML

---

## 👥 Auteurs

**Projet développé par** : [Votre Nom/Binôme]  
**Filière** : 2CS  
**Institut** : Institut Supérieur d'Informatique  
**Année** : 2025

---

## 📄 Licence

Projet académique - Mini-Projet POO  
© 2025 Institut Supérieur d'Informatique

---

## 📞 Support

Pour toute question ou problème :

- Consulter la documentation du code (Javadoc)
- Vérifier les logs dans la console
- Contacter l'enseignant responsable

---

**Bonne utilisation ! 🎉**
