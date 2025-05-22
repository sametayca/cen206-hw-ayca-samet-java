package com.samet.erdem.tracker.model;

import java.sql.Timestamp;

/**
 * @brief Model class representing a product or recipe in the Recipe & Nutrition Tracker application.
 * 
 * This class encapsulates all the nutritional information and metadata for a product or recipe.
 * It includes functionality for:
 * - Storing basic product information (name, portion)
 * - Managing nutritional values (calories, protein, carbs, fat)
 * - Tracking product ownership and creation time
 * - String representation of product details
 * 
 * The class provides constructors for different initialization scenarios and
 * comprehensive getter/setter methods for all properties.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class Product {
    /** Unique identifier for the product */
    private int id;
    
    /** Name of the product or recipe */
    private String name;
    
    /** Total calories in the product */
    private double calories;
    
    /** Protein content in grams */
    private double protein;
    
    /** Carbohydrate content in grams */
    private double carbs;
    
    /** Fat content in grams */
    private double fat;
    
    /** ID of the user who owns this product */
    private int userId;
    
    /** Timestamp when the product was created */
    private Timestamp createdAt;
    
    /** Portion description of the product (e.g., "100g", "1 glass") */
    private String portion;

    /**
     * @brief Default constructor.
     * 
     * Creates a new Product instance with default values.
     */
    public Product() {
        // Default constructor
    }

    /**
     * @brief Constructs a new Product with basic nutritional information.
     * 
     * @param name The name of the product
     * @param calories Total calories in the product
     * @param protein Protein content in grams
     * @param carbs Carbohydrate content in grams
     * @param fat Fat content in grams
     * @param userId ID of the user who owns this product
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
     * @brief Constructs a new Product with complete information.
     * 
     * @param id Unique identifier for the product
     * @param name The name of the product
     * @param calories Total calories in the product
     * @param protein Protein content in grams
     * @param carbs Carbohydrate content in grams
     * @param fat Fat content in grams
     * @param userId ID of the user who owns this product
     * @param createdAt Timestamp when the product was created
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

    /**
     * @brief Gets the product's unique identifier.
     * @return The product ID
     */
    public int getId() {
        return id;
    }

    /**
     * @brief Sets the product's unique identifier.
     * @param id The new ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief Gets the product's name.
     * @return The product name
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Sets the product's name.
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Gets the product's calorie content.
     * @return The number of calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * @brief Sets the product's calorie content.
     * @param calories The new calorie value to set
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * @brief Gets the product's protein content.
     * @return The protein content in grams
     */
    public double getProtein() {
        return protein;
    }

    /**
     * @brief Sets the product's protein content.
     * @param protein The new protein value to set
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * @brief Gets the product's carbohydrate content.
     * @return The carbohydrate content in grams
     */
    public double getCarbs() {
        return carbs;
    }

    /**
     * @brief Sets the product's carbohydrate content.
     * @param carbs The new carbohydrate value to set
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    /**
     * @brief Gets the product's fat content.
     * @return The fat content in grams
     */
    public double getFat() {
        return fat;
    }

    /**
     * @brief Sets the product's fat content.
     * @param fat The new fat value to set
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * @brief Gets the ID of the user who owns this product.
     * @return The user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @brief Sets the ID of the user who owns this product.
     * @param userId The new user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @brief Gets the timestamp when the product was created.
     * @return The creation timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @brief Sets the timestamp when the product was created.
     * @param createdAt The new creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @brief Gets the portion description.
     * @return Portion string (e.g., "100g", "1 piece")
     */
    public String getPortion() {
        return portion;
    }

    /**
     * @brief Sets the portion description.
     * @param portion The new portion to set
     */
    public void setPortion(String portion) {
        this.portion = portion;
    }

    /**
     * @brief Returns a string representation of the product.
     * 
     * Formats the product information in a readable format including:
     * - Product name
     * - Nutritional values (calories, protein, carbs, fat)
     * - Portion information
     * 
     * @return Formatted string containing product details
     */
    @Override
    public String toString() {
        return String.format(
            "Product: %s\n" +
            "Calories: %.2f\n" +
            "Protein: %.2f g\n" +
            "Carbs: %.2f g\n" +
            "Fat: %.2f g\n" +
            "Portion: %s", 
            name, calories, protein, carbs, fat, portion);
    }
}
