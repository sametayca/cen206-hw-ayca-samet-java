/**
 * @file DatabaseConnection.java
 * @brief Singleton class for managing SQLite database connections.
 * @details This file contains the implementation of the DatabaseConnection class,
 *          which manages connections to the SQLite database using the Singleton pattern.
 */
/**
 * Package for database connection management.
 */
package com.samet.erdem.tracker.database;

import java.sql.*;

/**
 * @class DatabaseConnection
 * @brief Singleton class for managing SQLite database connection.
 * @details This class implements the Singleton design pattern to ensure that only one
 *          database connection is created and used throughout the application. It handles
 *          the creation, initialization, and management of the SQLite database connection.
 *          
 *          The class provides functionality to:
 *          - Create and initialize the database connection
 *          - Create necessary database tables if they don't exist
 *          - Provide access to the connection object
 *          - Handle connection errors and reconnection
 *          - Close the connection when it's no longer needed
 */
public class DatabaseConnection {
    /**
     * @brief Singleton instance of the DatabaseConnection class.
     * @details This field stores the singleton instance of the DatabaseConnection class.
     *          It is used to ensure that only one instance of the class exists.
     */
    private static DatabaseConnection instance;
    /**
     * @brief Database connection object.
     * @details This field stores the connection to the database used for database operations.
     *          It is initialized in the constructor by obtaining a connection from the
     *          DatabaseConnection singleton.
     */
    private Connection connection;
    /**
     * @brief Database name.
     * @details This field stores the name of the database file.
     */ 
    private static final String DB_NAME = "tracker.db";
    
    /**
     * @brief Private constructor to prevent instantiation from outside the class.
     * @details Creates a new database connection and initializes the database tables.
     *          This constructor is private to enforce the Singleton pattern.
     *          It performs the following steps:
     *          1. Loads the SQLite JDBC driver
     *          2. Establishes a connection to the SQLite database
     *          3. Initializes the database tables if successful
     *          4. Handles any exceptions that may occur during these steps
     */
    private DatabaseConnection() {
        try {
            // SQLite JDBC sürücüsünü yükle
            Class.forName("org.sqlite.JDBC");
            
            // Veritabanı bağlantısını oluştur
            String dbUrl = "jdbc:sqlite:" + DB_NAME;
            connection = DriverManager.getConnection(dbUrl);
            
            if (connection != null) {
                initializeTables();
            } else {
               
            }
        } catch (ClassNotFoundException e) {
            
        } catch (SQLException e) {}
            
    }
    
    /**
     * @brief Gets the singleton instance of the DatabaseConnection class.
     * @details Returns the existing instance of DatabaseConnection if it exists,
     *          or creates a new instance if one doesn't exist yet. This method is
     *          synchronized to prevent race conditions in a multi-threaded environment.
     * 
     * @return The singleton instance of DatabaseConnection.
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    /**
     * @brief Gets the database connection object.
     * @details Returns the current database connection, or creates a new one if the
     *          current connection is null or closed. This method ensures that a valid
     *          connection is always available for database operations.
     * 
     * @return A valid Connection object for database operations.
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Connection is closed or null, reconnecting...");
                // Tekrar bağlantı oluştur
                connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            }
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * @brief Initializes the database tables.
     * @details Creates the necessary database tables if they don't already exist.
     *          This method is called during the initialization of the DatabaseConnection.
     *          It creates the following tables:
     *          - users: Stores user information including credentials and physical measurements
     *          - products: Stores product information including nutritional values
     *          
     *          Each table is created with appropriate columns, data types, constraints,
     *          and foreign key relationships.
     */
    private void initializeTables() {
        try {
            Statement stmt = connection.createStatement();
            
            // Users tablosunu oluştur
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS users (" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    username TEXT UNIQUE NOT NULL," +
                "    password TEXT NOT NULL," +
                "    height REAL NOT NULL," +
                "    weight REAL NOT NULL," +
                "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")"
            );
            
            // Products tablosunu oluştur
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS products (" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    name TEXT NOT NULL," +
                "    calories REAL NOT NULL," +
                "    protein REAL NOT NULL," +
                "    carbs REAL NOT NULL," +
                "    fat REAL NOT NULL," +
                "    user_id INTEGER," +
                "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "    FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")"
            );
            
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @brief Closes the database connection.
     * @details Safely closes the database connection if it is open.
     *          This method should be called when the connection is no longer needed,
     *          typically during application shutdown or when switching users.
     *          Any SQLException that occurs during the closing process is caught and printed.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 