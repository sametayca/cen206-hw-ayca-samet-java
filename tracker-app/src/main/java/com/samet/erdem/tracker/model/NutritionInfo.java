package com.samet.erdem.tracker.model;

/**
 * @class NutritionInfo
 * @brief Class to store nutritional information for food items.
 * @details This class contains all the nutritional values for a food item.
 */
public class NutritionInfo {
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fat;
    private double fiber;
    
    public NutritionInfo() {
        this.calories = 0.0;
        this.protein = 0.0;
        this.carbohydrates = 0.0;
        this.fat = 0.0;
        this.fiber = 0.0;
    }
    
    public NutritionInfo(double calories, double protein, double carbohydrates, double fat, double fiber) {
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.fiber = fiber;
    }
    
    // Getters and Setters
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
    
    public double getCarbohydrates() {
        return carbohydrates;
    }
    
    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
    
    public double getFat() {
        return fat;
    }
    
    public void setFat(double fat) {
        this.fat = fat;
    }
    
    public double getFiber() {
        return fiber;
    }
    
    public void setFiber(double fiber) {
        this.fiber = fiber;
    }
    
    /**
     * @brief Add another NutritionInfo object to this one.
     * @param other The NutritionInfo object to add.
     * @return A new NutritionInfo object with combined values.
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
    
    @Override
    public String toString() {
        return String.format("Calories: %.2f, Protein: %.2fg, Carbs: %.2fg, Fat: %.2fg, Fiber: %.2fg",
            calories, protein, carbohydrates, fat, fiber);
    }
} 