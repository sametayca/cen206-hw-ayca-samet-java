/**
 * @file Ingredient.java
 * @brief Model class representing an ingredient in the nutrition tracking system.
 * @details This file contains the implementation of the Ingredient class,
 *          which represents a basic food ingredient that can be used in recipes.
 */
package com.samet.erdem.tracker.model;

/**
 * @class Ingredient
 * @brief Class representing an ingredient in a recipe.
 * @details This class extends Food and adds ingredient-specific functionality.
 *          It represents a basic food ingredient that can be used in recipes and
 *          tracks additional properties such as food category and allergen status.
 *          
 *          The class provides functionality to:
 *          - Categorize ingredients (e.g., "Dairy", "Grain", "Vegetable")
 *          - Track allergen status for allergy warnings
 *          - Calculate nutritional content
 *          - Format ingredient data for display
 */
public class Ingredient extends Food {
    private String category;
    private boolean isAllergen;
    
    /**
     * @brief Constructor for creating a new ingredient.
     * @details Initializes an ingredient with the specified ID, name, serving size, and unit.
     *          By default, sets the category to "Uncategorized" and allergen status to false.
     * 
     * @param id The unique identifier for the ingredient.
     * @param name The name or description of the ingredient.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving (e.g., "g", "ml", "oz").
     */
    public Ingredient(String id, String name, double servingSize, String servingUnit) {
        super(id, name, servingSize, servingUnit);
        this.category = "Uncategorized";
        this.isAllergen = false;
    }
    
    // Getters and Setters
    /**
     * @brief Gets the ingredient's category.
     * @details Returns the food category assigned to this ingredient.
     *          Categories help organize ingredients (e.g., "Dairy", "Grain", "Vegetable").
     * 
     * @return The ingredient's category.
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * @brief Sets the ingredient's category.
     * @details Updates the food category assigned to this ingredient.
     * 
     * @param category The new category to assign to the ingredient.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * @brief Checks if the ingredient is an allergen.
     * @details Returns whether this ingredient is flagged as a potential allergen.
     *          This information is important for allergy warnings in recipes.
     * 
     * @return True if the ingredient is an allergen, false otherwise.
     */
    public boolean isAllergen() {
        return isAllergen;
    }
    
    /**
     * @brief Sets the ingredient's allergen status.
     * @details Updates whether this ingredient should be flagged as a potential allergen.
     * 
     * @param allergen True to mark as an allergen, false otherwise.
     */
    public void setAllergen(boolean allergen) {
        isAllergen = allergen;
    }
    
    /**
     * @brief Calculates the caloric content of this ingredient.
     * @details Implementation of the abstract method from the Food class.
     *          For ingredients, this simply returns the calories stored in the nutrition info.
     * 
     * @return The caloric content in kilocalories (kcal).
     */
    @Override
    public double calculateCalories() {
        return getNutritionInfo().getCalories();
    }
    
    /**
     * @brief Gets the type of food.
     * @details Implementation of the abstract method from the Food class.
     *          Always returns "Ingredient" to identify this as an ingredient.
     * 
     * @return The string "Ingredient".
     */
    @Override
    public String getFoodType() {
        return "Ingredient";
    }
    
    /**
     * @brief Provides a string representation of the ingredient.
     * @details Formats the ingredient's information into a human-readable string.
     *          Includes the name, category, serving information, allergen status,
     *          and nutritional information.
     * 
     * @return A formatted string representation of the ingredient.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ingredient: ").append(getName()).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Serving: ").append(getServingSize()).append(" ").append(getServingUnit()).append("\n");
        sb.append("Allergen: ").append(isAllergen ? "Yes" : "No").append("\n");
        sb.append("Nutrition:\n").append(getNutritionInfo());
        return sb.toString();
    }
}