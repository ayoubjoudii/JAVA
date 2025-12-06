CREATE DATABASE IF NOT EXISTS gestion_stock;
USE gestion_stock;
CREATE TABLE IF NOT EXISTS categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    quantity INT NOT NULL DEFAULT 0,
    category_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT,
    INDEX idx_category (category_id),
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO categories (name, description) VALUES
('Électronique', 'Appareils électroniques et accessoires'),
('Alimentaire', 'Produits alimentaires et boissons'),
('Vêtements', 'Articles vestimentaires'),
('Maison & Jardin', 'Produits pour la maison et le jardin'),
('Sport', 'Équipements et articles de sport'),
('Livres', 'Livres et magazines'),
('Jouets', 'Jouets et jeux pour enfants'),
('Bureautique', 'Fournitures de bureau');
INSERT INTO users (username, password, full_name, role) VALUES
('admin', 'admin', 'Administrateur', 'admin');
INSERT INTO products (name, description, price, quantity, category_id) VALUES
('Ordinateur Portable Dell', 'Dell Inspiron 15, 8GB RAM, 256GB SSD', 85000.00, 15, 1),
('Smartphone Samsung', 'Samsung Galaxy A54, 128GB', 45000.00, 25, 1),
('Souris sans fil', 'Souris optique sans fil Logitech', 2500.00, 50, 1),
('Clavier mécanique', 'Clavier RGB pour gaming', 8500.00, 20, 1),
('Huile d''olive', 'Huile d''olive extra vierge 1L', 850.00, 100, 2),
('Café moulu', 'Café arabica 250g', 450.00, 80, 2),
('Thé vert', 'Thé vert naturel 100g', 320.00, 4, 2),
('T-shirt coton', 'T-shirt 100% coton, plusieurs tailles', 1500.00, 60, 3),
('Jean homme', 'Jean slim fit', 3500.00, 30, 3),
('Chaise de bureau', 'Chaise ergonomique avec accoudoirs', 12000.00, 3, 4),
('Lampe de bureau LED', 'Lampe LED réglable', 3200.00, 25, 4),
('Ballon de football', 'Ballon officiel taille 5', 2800.00, 40, 5),
('Raquette de tennis', 'Raquette professionnelle Wilson', 15000.00, 8, 5),
('Livre - Le Petit Prince', 'Édition illustrée', 1200.00, 2, 6),
('Roman - Les Misérables', 'Victor Hugo, édition complète', 2500.00, 15, 6),
('Puzzle 1000 pièces', 'Puzzle paysage', 1800.00, 20, 7),
('Ramette papier A4', 'Papier blanc 500 feuilles', 650.00, 150, 8),
('Stylo à bille', 'Lot de 10 stylos bleus', 300.00, 200, 8);
CREATE OR REPLACE VIEW stock_statistics AS
SELECT 
    COUNT(*) as total_products,
    SUM(quantity) as total_quantity,
    SUM(price * quantity) as total_value,
    COUNT(CASE WHEN quantity < 5 THEN 1 END) as low_stock_count
FROM products;
CREATE OR REPLACE VIEW low_stock_products AS
SELECT 
    p.id,
    p.name,
    p.quantity,
    c.name as category_name
FROM products p
JOIN categories c ON p.category_id = c.id
WHERE p.quantity < 5
ORDER BY p.quantity ASC;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS update_product_quantity(
    IN prod_id INT,
    IN new_quantity INT
)
BEGIN
    UPDATE products 
    SET quantity = new_quantity 
    WHERE id = prod_id;
END //
DELIMITER ;
SELECT 'Base de données créée avec succès!' as Message;
SELECT * FROM stock_statistics;
SELECT COUNT(*) as 'Nombre de catégories' FROM categories;
SELECT COUNT(*) as 'Nombre de produits' FROM products;
SELECT COUNT(*) as 'Nombre d''utilisateurs' FROM users;


