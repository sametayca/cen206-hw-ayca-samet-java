/**
 * @file UserDAO.java
 * @brief Data Access Object implementation for User entities.
 * @details This file contains the implementation of the UserDAO class,
 *          which provides database operations for User entities.
 */
package com.samet.erdem.tracker.dao;

import com.samet.erdem.tracker.database.DatabaseConnection;
import com.samet.erdem.tracker.model.User;
import java.sql.*;

/**
 * @class UserDAO
 * @brief Data Access Object for User entities.
 * @details This class implements the Data Access Object (DAO) pattern for User entities.
 *          It provides methods for creating, retrieving, updating, and deleting user data
 *          in the database. The class encapsulates all database operations related to users,
 *          separating the business logic from the database access code.
 *          
 *          The class provides functionality to:
 *          - Register new users
 *          - Authenticate users during login
 *          - Update user information
 *          - Retrieve user data by ID or username
 *          - Delete user accounts
 *          - Check if a username already exists
 */
public class UserDAO {
    /**
     * @brief Database connection for user-related database operations.
     * @details This field stores the connection to the database used for user operations.
     *          It is initialized in the constructor by obtaining a connection from the
     *          DatabaseConnection singleton.
     */
    protected Connection connection;
    
    /**
     * @brief Retrieves a user by their username.
     * @details Searches the database for a user with the specified username and
     *          returns a User object if found. This method is useful for checking
     *          user existence and retrieving user details by username.
     * 
     * @param username The username to search for.
     * @return A User object if found, or null if no user with the specified username exists.
     * @throws SQLException If a database access error occurs.
     */
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
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
        return null;
    }

    
    /**
     * @brief Default constructor for the UserDAO class.
     * @details Initializes the database connection for user-related database operations.
     *          The constructor obtains a connection from the DatabaseConnection singleton
     *          and handles any errors that may occur during the connection process.
     */
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
    
    /**
     * @brief Registers a new user in the database.
     * @details Creates a new user record in the database with the provided user information.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL INSERT statement with the user data
     *          3. Retrieves the generated user ID and sets it in the User object
     *          4. Returns the updated User object with the assigned ID
     * 
     * @param user The User object containing the user information to register.
     * @return The User object with the assigned ID from the database.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Authenticates a user during login.
     * @details Validates the provided username and password against the database.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL SELECT statement with the username and password
     *          3. Creates and returns a User object if authentication is successful
     *          4. Returns null if no matching user is found
     * 
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @return A User object if authentication is successful, or null if authentication fails.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Updates user information in the database.
     * @details Updates the height and weight of a user in the database.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL UPDATE statement with the new values
     *          3. Returns a boolean indicating whether the update was successful
     * 
     * @param user The User object containing the updated information and ID.
     * @return true if the update was successful, false otherwise.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Retrieves a user by their ID.
     * @details Searches the database for a user with the specified ID and
     *          returns a User object if found. This method is useful for
     *          retrieving user details when the ID is known.
     * 
     * @param id The user ID to search for.
     * @return A User object if found, or null if no user with the specified ID exists.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Deletes a user from the database.
     * @details Removes a user record from the database based on the specified ID.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL DELETE statement with the user ID
     *          3. Returns a boolean indicating whether the deletion was successful
     * 
     * @param id The ID of the user to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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
    
    /**
     * @brief Checks if a username already exists in the database.
     * @details Queries the database to determine if a username is already taken.
     *          This method is useful during user registration to ensure username uniqueness.
     *          The method performs the following steps:
     *          1. Validates the database connection
     *          2. Prepares and executes an SQL SELECT COUNT statement with the username
     *          3. Returns a boolean indicating whether the username exists
     * 
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     * @throws SQLException If a database access error occurs or the connection is null.
     */
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