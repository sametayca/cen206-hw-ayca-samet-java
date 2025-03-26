package com.samet.erdem.tracker.database;

import java.sql.*;

/**
 * @class DatabaseConnection
 * @brief Singleton class for managing SQLite database connection.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private static final String DB_NAME = "tracker.db";
    
    private DatabaseConnection() {
        try {
            // SQLite JDBC sürücüsünü yükle
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC driver loaded successfully.");
            
            // Veritabanı bağlantısını oluştur
            String dbUrl = "jdbc:sqlite:" + DB_NAME;
            System.out.println("Connecting to database: " + dbUrl);
            connection = DriverManager.getConnection(dbUrl);
            
            if (connection != null) {
                System.out.println("Database connection successful.");
                initializeTables();
            } else {
                System.err.println("Database connection failed: Connection object is null.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
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