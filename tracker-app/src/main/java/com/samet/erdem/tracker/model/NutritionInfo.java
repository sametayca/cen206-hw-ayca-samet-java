/**
 * @file NutritionInfo.java
 * @brief Model class for storing and managing nutritional information.
 * @details This file contains the implementation of the NutritionInfo class,
 *          which encapsulates nutritional data for food items and recipes.
 */
package com.samet.erdem.tracker.model;

import java.util.Locale;

/**
 * @class NutritionInfo
 * @brief Class to store and manipulate nutritional information for food items.
 * @details This class represents a comprehensive nutritional profile for food items or recipes.
 *          It stores essential nutritional values including calories, protein, carbohydrates, fat, and fiber.
 *          The class provides methods for creating, accessing, modifying, and combining nutritional information,
 *          making it a fundamental component of the nutrition tracking system.
 *          
 *          NutritionInfo objects can be used to:
 *          - Store nutritional data for individual food items
 *          - Represent daily nutritional goals
 *          - Track accumulated nutritional intake
 *          - Combine multiple food items' nutritional profiles
 */
public class NutritionInfo {
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fat;
    private double fiber;
    
    /**
     * @brief Default constructor that initializes all nutritional values to zero.
     * @details Creates a new NutritionInfo object with all nutritional values (calories,
     *          protein, carbohydrates, fat, and fiber) set to 0.0. This constructor is
     *          useful for creating empty nutritional profiles that can be populated later
     *          or used as a starting point for accumulating nutritional information.
     */
    public NutritionInfo() {
        this.calories = 0.0;
        this.protein = 0.0;
        this.carbohydrates = 0.0;
        this.fat = 0.0;
        this.fiber = 0.0;
    }
    
    /**
     * @brief Parameterized constructor that initializes nutritional values with specified amounts.
     * @details Creates a new NutritionInfo object with the specified nutritional values.
     *          This constructor allows for the creation of nutritional profiles with
     *          predefined values, which is useful for representing specific food items
     *          or setting nutritional goals.
     * 
     * @param calories The caloric content in kilocalories (kcal).
     * @param protein The protein content in grams (g).
     * @param carbohydrates The carbohydrate content in grams (g).
     * @param fat The fat content in grams (g).
     * @param fiber The dietary fiber content in grams (g).
     */
    public NutritionInfo(double calories, double protein, double carbohydrates, double fat, double fiber) {
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.fiber = fiber;
    }
    
    // Getters and Setters
    /**
     * @brief Gets the caloric content.
     * @details Returns the current caloric value stored in this nutritional profile.
     *          Calories are typically measured in kilocalories (kcal).
     * 
     * @return The caloric content in kilocalories (kcal).
     */
    public double getCalories() {
        return calories;
    }
    
    /**
     * @brief Sets the caloric content.
     * @details Updates the caloric value in this nutritional profile to the specified amount.
     * 
     * @param calories The new caloric content in kilocalories (kcal).
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }
    
    /**
     * @brief Gets the protein content.
     * @details Returns the current protein value stored in this nutritional profile.
     *          Protein is an essential macronutrient for building and repairing tissues.
     * 
     * @return The protein content in grams (g).
     */
    public double getProtein() {
        return protein;
    }
    
    /**
     * @brief Sets the protein content.
     * @details Updates the protein value in this nutritional profile to the specified amount.
     * 
     * @param protein The new protein content in grams (g).
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }
    
    /**
     * @brief Gets the carbohydrate content.
     * @details Returns the current carbohydrate value stored in this nutritional profile.
     *          Carbohydrates are the body's primary source of energy.
     * 
     * @return The carbohydrate content in grams (g).
     */
    public double getCarbohydrates() {
        return carbohydrates;
    }
    
    /**
     * @brief Sets the carbohydrate content.
     * @details Updates the carbohydrate value in this nutritional profile to the specified amount.
     * 
     * @param carbohydrates The new carbohydrate content in grams (g).
     */
    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
    
    /**
     * @brief Gets the fat content.
     * @details Returns the current fat value stored in this nutritional profile.
     *          Fat is an essential macronutrient that provides energy and supports cell growth.
     * 
     * @return The fat content in grams (g).
     */
    public double getFat() {
        return fat;
    }
    
    /**
     * @brief Sets the fat content.
     * @details Updates the fat value in this nutritional profile to the specified amount.
     * 
     * @param fat The new fat content in grams (g).
     */
    public void setFat(double fat) {
        this.fat = fat;
    }
    
    /**
     * @brief Gets the fiber content.
     * @details Returns the current fiber value stored in this nutritional profile.
     *          Dietary fiber is important for digestive health and can help regulate blood sugar levels.
     * 
     * @return The fiber content in grams (g).
     */
    public double getFiber() {
        return fiber;
    }
    
    /**
     * @brief Sets the fiber content.
     * @details Updates the fiber value in this nutritional profile to the specified amount.
     * 
     * @param fiber The new fiber content in grams (g).
     */
    public void setFiber(double fiber) {
        this.fiber = fiber;
    }
    
    /**
     * @brief Combines this nutritional profile with another one.
     * @details Creates a new NutritionInfo object that contains the sum of all nutritional values
     *          from this object and the provided object. This method is particularly useful for:
     *          - Calculating total nutritional content of multiple food items
     *          - Accumulating nutritional intake over time
     *          - Combining ingredients in a recipe
     *          
     *          The original objects remain unchanged, and a new object with the combined values is returned.
     * 
     * @param other The NutritionInfo object to add to this one.
     * @return A new NutritionInfo object containing the sum of all nutritional values from both objects.
     */
    public NutritionInfo add(NutritionInfo other) {
        return new NutritionInfo(
            this.calories + other.calories,
            this.protein + other.protein,
            this.carbohydrates + other.carbohydrates,
            this.fat + other.fat,
            this.fiber + other.fiber
        );
    }
    
    /**
     * @brief Provides a string representation of the nutritional information.
     * @details Formats all nutritional values into a human-readable string with one decimal place precision.
     *          The string includes labels for each nutritional component and their values.
     *          This method uses the US locale to ensure consistent decimal formatting regardless of system settings.
     * 
     * @return A formatted string containing all nutritional values with labels.
     */
    @Override
    public String toString() {
        return String.format(Locale.US, 
            "Calories: %.1f\n" +
            "Protein: %.1f\n" +
            "Carbs: %.1f\n" +
            "Fat: %.1f\n" +
            "Fiber: %.1f",
            calories, protein, carbohydrates, fat, fiber);
    }
} 