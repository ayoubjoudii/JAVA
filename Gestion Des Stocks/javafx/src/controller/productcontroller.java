package controller;

import dao.productdao;
import model.Category;
import model.Product;
import model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.Optional;

public class productcontroller {
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> statusColumn;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<Category> categoryFilter;
    @FXML
    private Label totalProductsLabel;
    @FXML
    private Label totalValueLabel;
    @FXML
    private Label welcomeLabel;
    private productdao productDAO;
    private ObservableList<Product> productsList;
    private User currentUser;

    @FXML
    public void initialize() {
        productDAO = new productdao();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(column -> new TableCell<Product, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if (item.equals("Rupture de stock")) {
                        setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-background-color: #ccffcc; -fx-text-fill: green;");
                    }
                }
            }
        });
        loadCategories();
        loadAllProducts();
        updateStatistics();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Bienvenue, " + user.getFullName());
    }

    private void loadCategories() {
        List<Category> categories = productDAO.getAllCategories();
        Category allCategories = new Category(0, "Toutes les catégories", "");
        categories.add(0, allCategories);
        categoryFilter.setItems(FXCollections.observableArrayList(categories));
        categoryFilter.getSelectionModel().selectFirst();
    }

    private void loadAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        productsList = FXCollections.observableArrayList(products);
        productsTable.setItems(productsList);
    }

    @FXML
    private void handleCategoryFilter() {
        Category selectedCategory = categoryFilter.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            if (selectedCategory.getId() == 0) {
                loadAllProducts();
            } else {
                List<Product> products = productDAO.getProductsByCategory(selectedCategory.getId());
                productsList = FXCollections.observableArrayList(products);
                productsTable.setItems(productsList);
            }
            updateStatistics();
        }
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText();
        if (searchTerm.isEmpty()) {
            loadAllProducts();
        } else {
            List<Product> products = productDAO.searchProducts(searchTerm);
            productsList = FXCollections.observableArrayList(products);
            productsTable.setItems(productsList);
        }
        updateStatistics();
    }

    @FXML
    private void handleAddProduct() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un produit");
        dialog.setHeaderText("Entrez les informations du nouveau produit");
        ButtonType addButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = createProductForm(null);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return getProductFromForm(grid, null);
            }
            return null;
        });
        Optional<Product> result = dialog.showAndWait();
        result.ifPresent(product -> {
            if (productDAO.addProduct(product)) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Produit ajouté avec succès");
                loadAllProducts();
                updateStatistics();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout du produit");
            }
        });
    }

    @FXML
    private void handleEditProduct() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner un produit à modifier");
            return;
        }
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Modifier un produit");
        dialog.setHeaderText("Modifiez les informations du produit");
        ButtonType updateButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
        GridPane grid = createProductForm(selectedProduct);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return getProductFromForm(grid, selectedProduct);
            }
            return null;
        });
        Optional<Product> result = dialog.showAndWait();
        result.ifPresent(product -> {
            if (productDAO.updateProduct(product)) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Produit modifié avec succès");
                loadAllProducts();
                updateStatistics();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification du produit");
            }
        });
    }

    @FXML
    private void handleDeleteProduct() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner un produit à supprimer");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation de suppression");
        confirmAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit ?");
        confirmAlert.setContentText("Produit: " + selectedProduct.getName());
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (productDAO.deleteProduct(selectedProduct.getId())) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Produit supprimé avec succès");
                loadAllProducts();
                updateStatistics();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression du produit");
            }
        }
    }

    @FXML
    private void handleRefresh() {
        searchField.clear();
        categoryFilter.getSelectionModel().selectFirst();
        loadAllProducts();
        updateStatistics();
    }

    private GridPane createProductForm(Product product) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        TextField nameField = new TextField();
        TextField descriptionField = new TextField();
        TextField priceField = new TextField();
        TextField quantityField = new TextField();
        ComboBox<Category> categoryCombo = new ComboBox<>();
        List<Category> categories = productDAO.getAllCategories();
        categoryCombo.setItems(FXCollections.observableArrayList(categories));
        if (product != null) {
            nameField.setText(product.getName());
            descriptionField.setText(product.getDescription());
            priceField.setText(String.valueOf(product.getPrice()));
            quantityField.setText(String.valueOf(product.getQuantity()));
            for (Category cat : categories) {
                if (cat.getId() == product.getCategoryId()) {
                    categoryCombo.getSelectionModel().select(cat);
                    break;
                }
            }
        } else {
            categoryCombo.getSelectionModel().selectFirst();
        }
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Prix:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Quantité:"), 0, 3);
        grid.add(quantityField, 1, 3);
        grid.add(new Label("Catégorie:"), 0, 4);
        grid.add(categoryCombo, 1, 4);
        return grid;
    }

    private Product getProductFromForm(GridPane grid, Product existingProduct) {
        TextField nameField = (TextField) grid.getChildren().get(1);
        TextField descriptionField = (TextField) grid.getChildren().get(3);
        TextField priceField = (TextField) grid.getChildren().get(5);
        TextField quantityField = (TextField) grid.getChildren().get(7);
        @SuppressWarnings("unchecked")
        ComboBox<Category> categoryCombo = (ComboBox<Category>) grid.getChildren().get(9);
        try {
            String name = nameField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Category category = categoryCombo.getSelectionModel().getSelectedItem();
            if (name.isEmpty() || category == null) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs obligatoires");
                return null;
            }
            if (existingProduct != null) {
                existingProduct.setName(name);
                existingProduct.setDescription(description);
                existingProduct.setPrice(price);
                existingProduct.setQuantity(quantity);
                existingProduct.setCategoryId(category.getId());
                return existingProduct;
            } else {
                return new Product(name, description, price, quantity, category.getId());
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Prix et quantité doivent être des nombres valides");
            return null;
        }
    }

    private void updateStatistics() {
        int totalProducts = productDAO.getTotalProductCount();
        double totalValue = productDAO.getTotalStockValue();
        totalProductsLabel.setText("Total de produits: " + totalProducts);
        totalValueLabel.setText(String.format("Valeur totale du stock: %.2f DZD", totalValue));
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
