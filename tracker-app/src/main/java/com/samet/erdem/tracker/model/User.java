/**
 * @file User.java
 * @brief Model class representing a user in the nutrition tracking system.
 * @details This file contains the implementation of the User class,
 *          which represents a user entity with personal information and measurements.
 */
package com.samet.erdem.tracker.model;

import java.sql.Timestamp;

/**
 * @class User
 * @brief Model class representing a user in the system.
 * @details This class represents a user entity in the nutrition tracking system.
 *          It stores user information such as credentials (username and password),
 *          physical measurements (height and weight), and metadata (ID and creation timestamp).
 *          
 *          The class provides functionality to:
 *          - Store and retrieve user information
 *          - Calculate Body Mass Index (BMI) based on height and weight
 *          - Format user data for display or logging purposes
 */
public class User {
    /**
 * @brief Unique identifier for the user.
 */
private int id;

/**
 * @brief Username used for login and identification.
 */
private String username;

/**
 * @brief User's account password.
 * @details Stored in plain text or hashed form depending on implementation.
 */
private String password;

/**
 * @brief User's height in centimeters or meters.
 */
private double height;

/**
 * @brief User's weight in kilograms.
 */
private double weight;

/**
 * @brief Timestamp indicating when the user account was created.
 */
private Timestamp createdAt;
    
    /**
     * @brief Constructor for creating a new user without an ID.
     * @details Creates a new User object with the specified username, password, height, and weight.
     *          This constructor is typically used when registering a new user before they are
     *          assigned an ID from the database.
     * 
     * @param username The user's chosen username.
     * @param password The user's password.
     * @param height The user's height in centimeters.
     * @param weight The user's weight in kilograms.
     */
    public User(String username, String password, double height, double weight) {
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
    }
    
    /**
     * @brief Constructor for creating a user with an existing ID.
     * @details Creates a User object with all attributes including ID and creation timestamp.
     *          This constructor is typically used when retrieving an existing user from the database.
     * 
     * @param id The user's unique identifier.
     * @param username The user's username.
     * @param password The user's password.
     * @param height The user's height in centimeters.
     * @param weight The user's weight in kilograms.
     * @param createdAt The timestamp when the user account was created.
     */
    public User(int id, String username, String password, double height, double weight, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    /**
     * @brief Gets the user's ID.
     * @details Returns the unique identifier assigned to this user.
     * 
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }
    
    /**
     * @brief Default constructor for the User class.
     * @details This constructor is used when creating a new User object without any specific parameters.
     *          It initializes the User object with default values for all fields.
     */
    public User() {
        // empty
    }
    
    /**
     * @brief Sets the user's ID.
     * @details Updates the user's unique identifier.
     *          This method is typically called after a new user is registered
     *          and assigned an ID by the database.
     * 
     * @param id The new ID to assign to the user.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @brief Gets the user's username.
     * @details Returns the username associated with this user account.
     * 
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @brief Sets the user's username.
     * @details Updates the username associated with this user account.
     * 
     * @param username The new username to assign to the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @brief Gets the user's password.
     * @details Returns the password associated with this user account.
     *          Note: In a production environment, passwords should be stored securely
     *          using appropriate hashing techniques.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @brief Sets the user's password.
     * @details Updates the password associated with this user account.
     *          Note: In a production environment, passwords should be stored securely
     *          using appropriate hashing techniques.
     * 
     * @param password The new password to assign to the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @brief Gets the user's height.
     * @details Returns the height of the user in centimeters.
     * 
     * @return The user's height in centimeters.
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * @brief Sets the user's height.
     * @details Updates the height of the user.
     * 
     * @param height The new height in centimeters.
     */
    public void setHeight(double height) {
        this.height = height;
    }
    
    /**
     * @brief Gets the user's weight.
     * @details Returns the weight of the user in kilograms.
     * 
     * @return The user's weight in kilograms.
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * @brief Sets the user's weight.
     * @details Updates the weight of the user.
     * 
     * @param weight The new weight in kilograms.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    /**
     * @brief Gets the user's account creation timestamp.
     * @details Returns the timestamp when the user account was created.
     * 
     * @return The timestamp of account creation.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    /**
     * @brief Sets the user's account creation timestamp.
     * @details Updates the timestamp when the user account was created.
     * 
     * @param createdAt The new creation timestamp.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * @brief Calculates the user's Body Mass Index (BMI).
     * @details Computes the BMI using the formula: weight (kg) / (height (m))².
     *          The height is converted from centimeters to meters before calculation.
     *          BMI is a measure of body fat based on height and weight that applies
     *          to adult men and women.
     *          
     *          BMI Categories:
     *          - Below 18.5: Underweight
     *          - 18.5 - 24.9: Normal weight
     *          - 25.0 - 29.9: Overweight
     *          - 30.0 and above: Obesity
     * 
     * @return The calculated BMI value.
     */
    public double calculateBMI() {
        // Boy metre cinsinden olmalı
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    
    /**
     * @brief Provides a string representation of the user.
     * @details Formats the user's information into a string for display or logging purposes.
     *          The password is included in the string representation, which should be avoided
     *          in production environments for security reasons.
     * 
     * @return A string representation of the user object.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", createdAt=" + createdAt +
                '}';
    }
} 