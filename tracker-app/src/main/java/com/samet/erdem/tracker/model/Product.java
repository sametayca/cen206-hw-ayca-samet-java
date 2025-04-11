/**
 * @file Product.java
 * @brief Model class representing a food product in the nutrition tracking system.
 * @details This file contains the implementation of the Product class,
 *          which represents a food product with nutritional information.
 */
package com.samet.erdem.tracker.model;

import java.sql.Timestamp;

/**
 * @class Product
 * @brief Model class representing a product or recipe in the system.
 * @details This class represents a food product or recipe entity in the nutrition tracking system.
 *          It stores product information such as name, nutritional values (calories, protein,
 *          carbohydrates, and fat), ownership information (user ID), and metadata (ID and creation timestamp).
 *          
 *          The class provides functionality to:
 *          - Store and retrieve product information
 *          - Track nutritional content of food items
 *          - Associate products with specific users
 *          - Format product data for display or logging purposes
 */
public class Product {
    /**
     * @brief Unique identifier of the product.
     */
    private int id;

    /**
     * @brief Name of the product.
     */
    private String name;

    /**
     * @brief Caloric content of the product in kilocalories (kcal).
     */
    private double calories;

    /**
     * @brief Protein content of the product in grams (g).
     */
    private double protein;

    /**
     * @brief Carbohydrate content of the product in grams (g).
     */
    private double carbs;

    /**
     * @brief Fat content of the product in grams (g).
     */
    private double fat;

    /**
     * @brief ID of the user who owns this product.
     */
    private int userId;

    /**
     * @brief Timestamp for when the product was created.
     */
    private Timestamp createdAt;
    
    /**
     * @brief Constructor for creating a new product without an ID.
     * @details Creates a new Product object with the specified name, nutritional values, and user ID.
     *          This constructor is typically used when adding a new product before it is
     *          assigned an ID from the database.
     * 
     * @param name The name of the product.
     * @param calories The caloric content in kilocalories (kcal).
     * @param protein The protein content in grams (g).
     * @param carbs The carbohydrate content in grams (g).
     * @param fat The fat content in grams (g).
     * @param userId The ID of the user who owns this product.
     */
    public Product(String name, double calories, double protein, double carbs, double fat, int userId) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.userId = userId;
    }
    
    /**
     * @brief Constructor for creating a product with an existing ID.
     * @details Creates a Product object with all attributes including ID and creation timestamp.
     *          This constructor is typically used when retrieving an existing product from the database.
     * 
     * @param id The product's unique identifier.
     * @param name The name of the product.
     * @param calories The caloric content in kilocalories (kcal).
     * @param protein The protein content in grams (g).
     * @param carbs The carbohydrate content in grams (g).
     * @param fat The fat content in grams (g).
     * @param userId The ID of the user who owns this product.
     * @param createdAt The timestamp when the product was created.
     */
    public Product(int id, String name, double calories, double protein, double carbs, double fat, 
                  int userId, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.userId = userId;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    /**
     * @brief Gets the product's ID.
     * @details Returns the unique identifier assigned to this product.
     * 
     * @return The product's ID.
     */
    public int getId() {
        return id;
    }
    
    /**
     * @brief Sets the product's ID.
     * @details Updates the product's unique identifier.
     *          This method is typically called after a new product is added
     *          and assigned an ID by the database.
     * 
     * @param id The new ID to assign to the product.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @brief Gets the product's name.
     * @details Returns the name or description of this product.
     * 
     * @return The product's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * @brief Sets the product's name.
     * @details Updates the name or description of this product.
     * 
     * @param name The new name to assign to the product.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @brief Gets the product's caloric content.
     * @details Returns the caloric content of this product in kilocalories (kcal).
     * 
     * @return The caloric content in kilocalories (kcal).
     */
    public double getCalories() {
        return calories;
    }
    
    /**
     * @brief Sets the product's caloric content.
     * @details Updates the caloric content of this product.
     * 
     * @param calories The new caloric content in kilocalories (kcal).
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }
    
    /**
     * @brief Gets the product's protein content.
     * @details Returns the protein content of this product in grams (g).
     *          Protein is an essential macronutrient for building and repairing tissues.
     * 
     * @return The protein content in grams (g).
     */
    public double getProtein() {
        return protein;
    }
    
    /**
     * @brief Sets the product's protein content.
     * @details Updates the protein content of this product.
     * 
     * @param protein The new protein content in grams (g).
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }
    
    /**
     * @brief Gets the product's carbohydrate content.
     * @details Returns the carbohydrate content of this product in grams (g).
     *          Carbohydrates are the body's primary source of energy.
     * 
     * @return The carbohydrate content in grams (g).
     */
    public double getCarbs() {
        return carbs;
    }
    
    /**
     * @brief Sets the product's carbohydrate content.
     * @details Updates the carbohydrate content of this product.
     * 
     * @param carbs The new carbohydrate content in grams (g).
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
    
    /**
     * @brief Gets the product's fat content.
     * @details Returns the fat content of this product in grams (g).
     *          Fat is an essential macronutrient that provides energy and supports cell growth.
     * 
     * @return The fat content in grams (g).
     */
    public double getFat() {
        return fat;
    }
    
    /**
     * @brief Sets the product's fat content.
     * @details Updates the fat content of this product.
     * 
     * @param fat The new fat content in grams (g).
     */
    public void setFat(double fat) {
        this.fat = fat;
    }
    
    /**
     * @brief Gets the ID of the user who owns this product.
     * @details Returns the user ID associated with this product.
     *          This association is used to ensure that users can only
     *          access and modify their own products.
     * 
     * @return The ID of the user who owns this product.
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * @brief Sets the ID of the user who owns this product.
     * @details Updates the user ID associated with this product.
     * 
     * @param userId The new user ID to associate with this product.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    /**
     * @brief Gets the product's creation timestamp.
     * @details Returns the timestamp when the product was created.
     * 
     * @return The timestamp of product creation.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    /**
     * @brief Default constructor for the Product class.
     * @details Creates an empty Product object with default values.
     *          This constructor can be used when a product needs to be
     *          created first and populated with data later.
     */
    public Product() {
        // Varsayılan değerlerle boş bir ürün nesnesi oluşturulabilir.
    }
    
    /**
     * @brief Sets the product's creation timestamp.
     * @details Updates the timestamp when the product was created.
     * 
     * @param createdAt The new creation timestamp.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * @brief Provides a string representation of the product.
     * @details Formats the product's information into a string for display or logging purposes.
     *          The string includes the product name and nutritional values with appropriate units.
     * 
     * @return A formatted string representation of the product object.
     */
    @Override
    public String toString() {
        return String.format(
            "Product: %s\n" +
            "Calories: %.2f\n" +
            "Protein: %.2f g\n" +
            "Carbs: %.2f g\n" +
            "Fat: %.2f g", 
            name, calories, protein, carbs, fat);
    }
} 