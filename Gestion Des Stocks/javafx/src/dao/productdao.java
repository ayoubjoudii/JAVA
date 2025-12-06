package dao;

import model.Product;
import model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class productdao {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, c.name as category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "ORDER BY p.id";
        try (Connection conn = dbconnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des produits: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, c.name as category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.category_id = ? ORDER BY p.name";
        try (Connection conn = dbconnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des produits par catégorie: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
    public List<Product> searchProducts(String searchTerm) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, c.name as category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.name LIKE ? OR c.name LIKE ? ORDER BY p.name";
        try (Connection conn = dbconnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de produits: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
    public boolean addProduct(Product product) {
        String query = "INSERT INTO products (name, description, price, quantity, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbconnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getCategoryId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du produit: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, description = ?, price = ?, " +
                "quantity = ?, category_id = ? WHERE id = ?";
        try (Connection conn = dbconnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getCategoryId());
            stmt.setInt(6, product.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du produit: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection conn = dbconnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du produit: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public int getTotalProductCount() {
        String query = "SELECT COUNT(*) FROM products";
        try (Connection conn = dbconnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors du comptage des produits: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
    public double getTotalStockValue() {
        String query = "SELECT SUM(price * quantity) as total FROM products";
        try (Connection conn = dbconnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors du calcul de la valeur du stock: " + e.getMessage());
            e.printStackTrace();
        }
        return 0.0;
    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories ORDER BY name";
        try (Connection conn = dbconnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des catégories: " + e.getMessage());
            e.printStackTrace();
        }
        return categories;
    }
    public Product getProductById(int id) {
        String query = "SELECT p.*, c.name as category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (Connection conn = dbconnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("category_id"),
                        rs.getString("category_name"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du produit: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}


