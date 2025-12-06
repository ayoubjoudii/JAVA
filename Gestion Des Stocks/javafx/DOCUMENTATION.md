# Documentation Technique - Gestion de Stock

## Sources et Outils Utilisés

### Bibliothèques et Frameworks

1. **JavaFX 21.0.6**

   - Source : https://openjfx.io/
   - Utilisation : Interface graphique (GUI)
   - Documentation : https://openjfx.io/javadoc/21/

2. **MySQL Connector/J 8.0.33**

   - Source : https://dev.mysql.com/downloads/connector/j/
   - Utilisation : Connexion JDBC à MySQL
   - Documentation : https://dev.mysql.com/doc/connector-j/en/

3. **MySQL 8.0**
   - Source : https://dev.mysql.com/downloads/mysql/
   - Utilisation : Système de gestion de base de données
   - Documentation : https://dev.mysql.com/doc/

### Outils de Développement

1. **Apache Maven**

   - Source : https://maven.apache.org/
   - Utilisation : Gestion des dépendances et build
   - Version : Maven Wrapper inclus

2. **Java Development Kit (JDK) 17+**

   - Source : https://www.oracle.com/java/technologies/downloads/
   - Utilisation : Compilation et exécution
   - Documentation : https://docs.oracle.com/en/java/

3. **IDE Recommandés**
   - IntelliJ IDEA : https://www.jetbrains.com/idea/
   - Eclipse : https://www.eclipse.org/
   - Visual Studio Code : https://code.visualstudio.com/

### Ressources de Documentation

1. **Architecture MVC**

   - Article : https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
   - Utilisation : Structure du projet

2. **Design Patterns**

   - Singleton Pattern : Pour dbconnection
   - DAO Pattern : Pour l'accès aux données
   - Source : https://refactoring.guru/design-patterns

3. **JavaFX Tutorials**
   - Documentation officielle Oracle
   - Tutoriels : https://docs.oracle.com/javafx/2/

### Standards et Conventions

1. **Java Code Conventions**

   - Source : https://www.oracle.com/java/technologies/javase/codeconventions-contents.html
   - Utilisation : Style de code

2. **Javadoc**
   - Source : https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
   - Utilisation : Documentation du code

## Répartition du Travail (Binôme)

### Personne 1 : [Nom]

- Conception de la base de données
- Développement des classes Model (Product, Category, User)
- Développement des classes DAO (dbconnection, userdao, productdao)
- Tests des fonctionnalités CRUD
- Documentation Javadoc

### Personne 2 : [Nom]

- Conception de l'interface utilisateur
- Développement des vues FXML (login.fxml, products.fxml)
- Développement des contrôleurs (logincontroller, productcontroller)
- Intégration de l'interface avec la logique métier
- Création des scripts batch et README

### Travail Commun

- Conception des diagrammes UML
- Tests d'intégration
- Debugging et optimisation
- Préparation de la présentation finale

## Architecture Détaillée

### Couche Model (Modèle de données)

```
model/
├── Product.java      - Entité produit avec logique métier
├── Category.java     - Entité catégorie
└── User.java         - Entité utilisateur
```

**Principes appliqués :**

- Encapsulation totale (attributs privés)
- Getters/Setters pour l'accès contrôlé
- Méthodes métier (calculateStatus, getTotalValue)
- Override de toString, equals, hashCode

### Couche DAO (Accès aux données)

```
dao/
├── dbconnection.java  - Singleton pour la connexion MySQL
├── userdao.java       - Opérations sur les utilisateurs
└── productdao.java    - Opérations CRUD sur les produits
```

**Principes appliqués :**

- Pattern Singleton pour la connexion
- Séparation des responsabilités
- Gestion des exceptions SQL
- Utilisation de PreparedStatement (sécurité)
- Fermeture automatique des ressources (try-with-resources)

### Couche Controller (Contrôleurs)

```
controller/
├── logincontroller.java    - Gestion de l'authentification
└── productcontroller.java  - Gestion des produits
```

**Principes appliqués :**

- Liaison avec les vues FXML via @FXML
- Validation des entrées utilisateur
- Gestion des événements
- Communication avec la couche DAO

### Couche View (Vues)

```
view/
├── login.fxml       - Interface de connexion
└── products.fxml    - Interface de gestion des produits
```

**Principes appliqués :**

- Séparation présentation/logique
- Design responsive
- Utilisation de styles CSS inline
- Binding avec les contrôleurs

## Fonctionnalités Implémentées

### 1. Authentification

- [x] Login avec username/password
- [x] Validation des champs
- [x] Messages d'erreur
- [x] Création d'utilisateur par défaut
- [x] Redirection après connexion

### 2. Gestion des Produits

- [x] Affichage dans un TableView
- [x] Ajout de produit (dialog)
- [x] Modification de produit (dialog)
- [x] Suppression de produit (confirmation)
- [x] Validation des données

### 3. Recherche et Filtrage

- [x] Recherche par nom
- [x] Recherche par catégorie (via filtre)
- [x] ComboBox de filtrage par catégorie
- [x] Bouton de rafraîchissement

### 4. Statuts et Alertes

- [x] Calcul automatique du statut
- [x] Affichage coloré (vert/rouge)
- [x] Alerte "Rupture de stock" si quantité < 5

### 5. Statistiques

- [x] Nombre total de produits
- [x] Valeur totale du stock
- [x] Mise à jour en temps réel

### 6. Base de Données

- [x] Tables normalisées
- [x] Relations (Foreign Keys)
- [x] Index pour les performances
- [x] Données de test
- [x] Vues SQL pour statistiques

## Concepts POO Avancés

### Encapsulation

- Tous les attributs sont privés
- Accès contrôlé via getters/setters
- Validation dans les setters (exemple : setQuantity met à jour le statut)

### Héritage

- Application extends javafx.application.Application
- Utilisation de classes JavaFX (TableView, Button, etc.)

### Polymorphisme

- Override de méthodes (toString, equals, hashCode)
- Interfaces JavaFX (EventHandler implicite)

### Abstraction

- Interface entre DAO et Controller
- Séparation des couches MVC

### Modularité

- Organisation en packages
- Classes avec responsabilité unique
- Couplage faible, cohésion forte

## Sécurité

### Implémentées

- PreparedStatement pour éviter les injections SQL
- Validation des entrées utilisateur
- Gestion des exceptions

### À Améliorer (Production)

- Hachage des mots de passe (BCrypt, SHA-256)
- Validation côté serveur plus stricte
- Gestion des sessions
- Logs d'audit

## Performance

### Optimisations

- Index sur les colonnes fréquemment recherchées
- Connexion unique (Singleton)
- Fermeture automatique des ressources
- Requêtes SQL optimisées (JOIN au lieu de multiples SELECT)

## Tests

### Tests Manuels Effectués

- ✅ Connexion avec identifiants valides
- ✅ Connexion avec identifiants invalides
- ✅ Ajout de produit
- ✅ Modification de produit
- ✅ Suppression de produit
- ✅ Recherche de produits
- ✅ Filtrage par catégorie
- ✅ Affichage des statistiques
- ✅ Détection de rupture de stock

### Tests à Ajouter (Amélioration)

- Tests unitaires JUnit pour les DAO
- Tests d'intégration
- Tests de l'interface (TestFX)

## Améliorations Futures

### Fonctionnalités

- [ ] Gestion des fournisseurs
- [ ] Historique des mouvements de stock
- [ ] Génération de rapports PDF
- [ ] Export Excel
- [ ] Graphiques de statistiques
- [ ] Alertes par email
- [ ] Multi-utilisateurs avec droits d'accès
- [ ] Backup automatique de la BDD

### Technique

- [ ] Migration vers Spring Boot
- [ ] API REST
- [ ] Application web (React/Angular)
- [ ] Application mobile
- [ ] Docker pour déploiement
- [ ] CI/CD pipeline

## Problèmes Connus et Solutions

### Problème 1 : Connexion MySQL

**Symptôme** : Erreur "Connection refused"  
**Solution** : Vérifier que MySQL est démarré, port 3306 ouvert

### Problème 2 : FXML not found

**Symptôme** : FXMLLoader ne trouve pas les fichiers  
**Solution** : Vérifier le chemin "/view/login.fxml" (avec /)

### Problème 3 : JavaFX runtime components missing

**Symptôme** : Erreur au lancement  
**Solution** : Utiliser mvnw javafx:run au lieu de java -jar

## Références Bibliographiques

1. Eckel, B. (2006). _Thinking in Java_. Prentice Hall.
2. Bloch, J. (2018). _Effective Java_ (3rd ed.). Addison-Wesley.
3. Freeman, E., & Freeman, E. (2020). _Head First Design Patterns_. O'Reilly.
4. Oracle. (2023). _JavaFX Documentation_. https://openjfx.io/
5. Goncalves, A. (2017). _Beginning Java EE 7_. Apress.

## Contact et Support

**Enseignant responsable** : [Nom de l'enseignant]  
**Email** : [email@institution.dz]  
**Date de rendu** : Décembre 2025

---

_Documentation générée pour le mini-projet POO - 2CS 2025_
