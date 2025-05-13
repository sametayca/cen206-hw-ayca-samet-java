package com.samet.erdem.tracker.model;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private int userId;
    private Timestamp createdAt;

    /**
     * @brief Portion description of the product (e.g., "100g", "1 glass").
     */
    private String portion;

    // Constructors
    public Product() {
        // Default constructor
    }

    public Product(String name, double calories, double protein, double carbs, double fat, int userId) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.userId = userId;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @brief Gets the portion description.
     * @return Portion string (e.g., "100g", "1 piece").
     */
    public String getPortion() {
        return portion;
    }

    /**
     * @brief Sets the portion description.
     * @param portion The new portion to set.
     */
    public void setPortion(String portion) {
        this.portion = portion;
    }

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
