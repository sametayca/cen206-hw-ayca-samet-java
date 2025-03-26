package com.samet.erdem.tracker.dao;

import com.samet.erdem.tracker.database.DatabaseConnection;
import com.samet.erdem.tracker.model.User;
import java.sql.*;

/**
 * @class UserDAO
 * @brief Data Access Object for User entities.
 */
public class UserDAO {
    private Connection connection;
    
    public UserDAO() {
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
            System.err.println("Error initializing UserDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public User register(User user) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "INSERT INTO users (username, password, height, weight) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setDouble(3, user.getHeight());
            pstmt.setDouble(4, user.getWeight());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("User creation failed, no affected rows.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("User creation failed, ID not retrieved.");
                }
            }
            
            return user;
        }
    }
    
    public User login(String username, String password) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("height"),
                        rs.getDouble("weight"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
        }
        
        return null;
    }
    
    public boolean updateUser(User user) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "UPDATE users SET height = ?, weight = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, user.getHeight());
            pstmt.setDouble(2, user.getWeight());
            pstmt.setInt(3, user.getId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public User getUserById(int id) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("height"),
                        rs.getDouble("weight"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
        }
        
        return null;
    }
    
    public boolean deleteUser(int id) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public boolean usernameExists(String username) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection could not be established!");
        }
        
        String sql = "SELECT COUNT(*) AS count FROM users WHERE username = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        }
        
        return false;
    }
} 