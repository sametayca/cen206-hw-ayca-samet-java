package com.samet.erdem.tracker.dao;

import com.samet.erdem.tracker.database.DatabaseConnection;
import com.samet.erdem.tracker.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @class ProductDAO
 * @brief Data Access Object for Product entities.
 */
public class ProductDAO {
    private Connection connection;
    
    public ProductDAO() {
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            if (dbConnection != null) {
                this.connection = dbConnection.getConnection();
                if (this.connection == null) {
                    System.err.println("Database connection could not be established!");
                }
            } else {
                System.err.println("Failed to create DatabaseConnection instance!");
            }
        } catch (Exception e) {
            System.err.println("Error initializing ProductDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Product addProduct(Product product) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "INSERT INTO products (name, calories, protein, carbs, fat, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getCalories());
            pstmt.setDouble(3, product.getProtein());
            pstmt.setDouble(4, product.getCarbs());
            pstmt.setDouble(5, product.getFat());
            pstmt.setInt(6, product.getUserId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Product creation failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Product creation failed, ID could not be retrieved.");
                }
            }
            
            return product;
        }
    }
    
    public List<Product> getAllProducts(int userId) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE user_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("calories"),
                        rs.getDouble("protein"),
                        rs.getDouble("carbs"),
                        rs.getDouble("fat"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("created_at")
                    ));
                }
            }
        }
        
        return products;
    }
    
    public Product getProductById(int id) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "SELECT * FROM products WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("calories"),
                        rs.getDouble("protein"),
                        rs.getDouble("carbs"),
                        rs.getDouble("fat"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
        }
        
        return null;
    }
    
    public boolean updateProduct(Product product) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "UPDATE products SET name = ?, calories = ?, protein = ?, carbs = ?, fat = ? WHERE id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getCalories());
            pstmt.setDouble(3, product.getProtein());
            pstmt.setDouble(4, product.getCarbs());
            pstmt.setDouble(5, product.getFat());
            pstmt.setInt(6, product.getId());
            pstmt.setInt(7, product.getUserId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public boolean deleteProduct(int id, int userId) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "DELETE FROM products WHERE id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, userId);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public List<Product> searchProducts(String keyword, int userId) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? AND user_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setInt(2, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("calories"),
                        rs.getDouble("protein"),
                        rs.getDouble("carbs"),
                        rs.getDouble("fat"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("created_at")
                    ));
                }
            }
        }
        
        return products;
    }
} 