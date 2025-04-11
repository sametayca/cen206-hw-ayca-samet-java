/**
 * @file ProductDAO.java
 * @brief Data Access Object implementation for Product entities.
 * @details This file contains the implementation of the ProductDAO class,
 *          which provides database operations for Product entities.
 */
/**
 * Package for Data Access Objects (DAOs) providing database operations for
 * different application entities.
 */
package com.samet.erdem.tracker.dao;

import com.samet.erdem.tracker.database.DatabaseConnection;
import com.samet.erdem.tracker.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @class ProductDAO
 * @brief Data Access Object for Product entities.
 * @details This class implements the Data Access Object (DAO) pattern for Product entities.
 *          It provides methods for creating, retrieving, updating, and deleting product data
 *          in the database. The class encapsulates all database operations related to products,
 *          separating the business logic from the database access code.
 *          
 *          The class provides functionality to:
 *          - Add new products to the database
 *          - Retrieve products by ID or user ID
 *          - Update existing product information
 *          - Delete products from the database
 *          - Search for products by keyword
 */
public class ProductDAO {
    /**
     * @brief Database connection for product-related database operations.
     * @details This field stores the connection to the database used for product operations.
     *          It is initialized in the constructor by obtaining a connection from the
     *          DatabaseConnection singleton.
     */
    protected Connection connection;
    
    /**
     * @brief Default constructor for the ProductDAO class.
     * @details Initializes the database connection for product-related database operations.
     *          The constructor obtains a connection from the DatabaseConnection singleton
     *          and handles any errors that may occur during the connection process.
     */
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
    
    /**
     * @brief Adds a new product to the database.
     * @details Creates a new product record in the database with the provided product information.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL INSERT statement with the product data
     *          3. Retrieves the generated product ID and sets it in the Product object
     *          4. Returns the updated Product object with the assigned ID
     * 
     * @param product The Product object containing the product information to add.
     * @return The Product object with the assigned ID from the database.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Retrieves all products associated with a specific user.
     * @details Retrieves all products from the database that belong to the specified user.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL SELECT statement with the user ID
     *          3. Creates Product objects from the result set and adds them to a list
     *          4. Returns the list of Product objects
     * 
     * @param userId The ID of the user whose products should be retrieved.
     * @return A List of Product objects belonging to the specified user.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Retrieves a product by its ID.
     * @details Searches the database for a product with the specified ID and
     *          returns a Product object if found. This method is useful for
     *          retrieving product details when the ID is known.
     * 
     * @param id The product ID to search for.
     * @return A Product object if found, or null if no product with the specified ID exists.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Updates product information in the database.
     * @details Updates the information of an existing product in the database.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL UPDATE statement with the new values
     *          3. Returns a boolean indicating whether the update was successful
     *          
     *          The method includes a security check to ensure that users can only
     *          update their own products by including the user ID in the WHERE clause.
     * 
     * @param product The Product object containing the updated information and ID.
     * @return true if the update was successful, false otherwise.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Deletes a product from the database.
     * @details Removes a product record from the database based on the specified ID and user ID.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL DELETE statement with the product ID and user ID
     *          3. Returns a boolean indicating whether the deletion was successful
     *          
     *          The method includes a security check to ensure that users can only
     *          delete their own products by including the user ID in the WHERE clause.
     * 
     * @param id The ID of the product to delete.
     * @param userId The ID of the user who owns the product.
     * @return true if the deletion was successful, false otherwise.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Searches for products by keyword.
     * @details Searches the database for products with names containing the specified keyword
     *          and belonging to the specified user. The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL SELECT statement with the keyword and user ID
     *          3. Creates Product objects from the result set and adds them to a list
     *          4. Returns the list of matching Product objects
     * 
     * @param keyword The keyword to search for in product names.
     * @param userId The ID of the user whose products should be searched.
     * @return A List of Product objects matching the search criteria.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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