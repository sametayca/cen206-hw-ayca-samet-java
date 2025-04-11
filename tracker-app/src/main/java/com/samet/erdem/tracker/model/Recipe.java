/**
 * @file Recipe.java
 * @brief Model class representing a recipe in the nutrition tracking system.
 * @details This file contains the implementation of the Recipe class,
 *          which represents a collection of ingredients with preparation instructions.
 */
package com.samet.erdem.tracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @class Recipe
 * @brief Class representing a recipe in the system.
 * @details This class extends Food and adds recipe-specific functionality.
 *          It represents a collection of ingredients with preparation instructions
 *          and cooking information.
 *          
 *          The class provides functionality to:
 *          - Manage a list of ingredients
 *          - Store preparation instructions
 *          - Track preparation and cooking times
 *          - Calculate combined nutritional information
 *          - Adjust for multiple servings
 */
public class Recipe extends Food {
    /**
     * @brief List of ingredients used in the recipe.
     * @details Stores all the ingredients used in the recipe.
     */
    private List<Ingredient> ingredients;
    
    /**
     * @brief Instructions for preparing the recipe.
     * @details Stores the step-by-step instructions for preparing the recipe.
     */
    private List<String> instructions;
    
    /**
     * @brief Time required to prepare the recipe.
     * @details Stores the time in minutes required to prepare the recipe.
     */
    private int preparationTime;
    
    /**
     * @brief Time required to cook the recipe.
     * @details Stores the time in minutes required to cook the recipe.
     */
    private int cookingTime;
    
    /**
     * @brief Number of servings the recipe yields.
     * @details Stores the number of servings the recipe will yield.
     */
    private int servings;
    
    /**
     * @brief Constructor for creating a new recipe.
     * @details Initializes a recipe with the specified ID, name, serving size, and unit.
     *          Creates empty lists for ingredients and instructions, and sets default values
     *          for preparation time, cooking time, and servings.
     * 
     * @param id The unique identifier for the recipe.
     * @param name The name or description of the recipe.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving.
     */
    public Recipe(String id, String name, double servingSize, String servingUnit) {
        super(id, name, servingSize, servingUnit);
        this.ingredients = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.preparationTime = 0;
        this.cookingTime = 0;
        this.servings = 1;
    }
    
    // Getters and Setters
    /**
     * @brief Gets the recipe's ingredients.
     * @details Returns the list of ingredients used in this recipe.
     * 
     * @return List of Ingredient objects.
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    /**
     * @brief Sets the recipe's ingredients.
     * @details Updates the list of ingredients used in this recipe and
     *          recalculates the total nutritional information.
     * 
     * @param ingredients The new list of ingredients.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        calculateTotalNutrition();
    }
    
    /**
     * @brief Gets the recipe's preparation instructions.
     * @details Returns the list of step-by-step instructions for preparing this recipe.
     * 
     * @return List of instruction strings.
     */
    public List<String> getInstructions() {
        return instructions;
    }
    
    /**
     * @brief Sets the recipe's preparation instructions.
     * @details Updates the list of step-by-step instructions for preparing this recipe.
     * 
     * @param instructions The new list of instruction strings.
     */
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
    
    /**
     * @brief Gets the recipe's preparation time.
     * @details Returns the time required to prepare this recipe before cooking.
     * 
     * @return Preparation time in minutes.
     */
    public int getPreparationTime() {
        return preparationTime;
    }
    
    /**
     * @brief Sets the recipe's preparation time.
     * @details Updates the time required to prepare this recipe before cooking.
     * 
     * @param preparationTime The new preparation time in minutes.
     */
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
    
    /**
     * @brief Gets the recipe's cooking time.
     * @details Returns the time required to cook this recipe after preparation.
     * 
     * @return Cooking time in minutes.
     */
    public int getCookingTime() {
        return cookingTime;
    }
    
    /**
     * @brief Sets the recipe's cooking time.
     * @details Updates the time required to cook this recipe after preparation.
     * 
     * @param cookingTime The new cooking time in minutes.
     */
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
    
    /**
     * @brief Gets the number of servings the recipe makes.
     * @details Returns the number of servings that this recipe produces.
     *          This affects the per-serving nutritional calculations.
     * 
     * @return Number of servings.
     */
    public int getServings() {
        return servings;
    }
    
    /**
     * @brief Sets the number of servings the recipe makes.
     * @details Updates the number of servings that this recipe produces and
     *          recalculates the total nutritional information per serving.
     * 
     * @param servings The new number of servings.
     */
    public void setServings(int servings) {
        this.servings = servings;
        calculateTotalNutrition();
    }
    
    // Methods
    /**
     * @brief Adds an ingredient to the recipe.
     * @details Adds the specified ingredient to the recipe's ingredient list and
     *          recalculates the total nutritional information.
     * 
     * @param ingredient The ingredient to add.
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        calculateTotalNutrition();
    }
    
    /**
     * @brief Removes an ingredient from the recipe.
     * @details Removes the specified ingredient from the recipe's ingredient list and
     *          recalculates the total nutritional information.
     * 
     * @param ingredient The ingredient to remove.
     */
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        calculateTotalNutrition();
    }
    
    /**
     * @brief Adds an instruction to the recipe.
     * @details Adds the specified instruction to the recipe's instruction list.
     * 
     * @param instruction The instruction to add.
     */
    public void addInstruction(String instruction) {
        instructions.add(instruction);
    }
    
    /**
     * @brief Calculates the total nutritional information for the recipe.
     * @details Combines the nutritional information from all ingredients and
     *          updates the recipe's nutritional profile.
     */
    private void calculateTotalNutrition() {
        NutritionInfo total = new NutritionInfo();
        for (Ingredient ingredient : ingredients) {
            total = total.add(ingredient.getNutritionInfo());
        }
        setNutritionInfo(total);
    }
    
    /**
     * @brief Calculates the caloric content per serving.
     * @details Implementation of the abstract method from the Food class.
     *          Divides the total calories by the number of servings.
     * 
     * @return The caloric content per serving in kilocalories (kcal).
     */
    @Override
    public double calculateCalories() {
        return getNutritionInfo().getCalories() / servings;
    }
    
    /**
     * @brief Gets the type of food.
     * @details Implementation of the abstract method from the Food class.
     *          Always returns "Recipe" to identify this as a recipe.
     * 
     * @return The string "Recipe".
     */
    @Override
    public String getFoodType() {
        return "Recipe";
    }
    
    /**
     * @brief Calculates the total time required for the recipe.
     * @details Combines the preparation time and cooking time to get the total time.
     * 
     * @return Total time in minutes.
     */
    public int getTotalTime() {
        return preparationTime + cookingTime;
    }
    
    /**
     * @brief Provides a string representation of the recipe.
     * @details Formats the recipe's information into a human-readable string.
     *          Includes the name, servings, total time, and nutritional information per serving.
     * 
     * @return A formatted string representation of the recipe.
     */
    @Override
    public String toString() {
        return String.format(Locale.US,
            "Recipe: %s\n" +
            "Servings: %d\n" +
            "Total Time: %d minutes\n" +
            "Nutrition per serving:\n" +
            "Calories: %.1f\n" +
            "Protein: %.1f\n" +
            "Carbs: %.1f\n" +
            "Fat: %.1f",
            getName(), servings, getTotalTime(),
            getNutritionInfo().getCalories(),
            getNutritionInfo().getProtein(),
            getNutritionInfo().getCarbohydrates(),
            getNutritionInfo().getFat());
    }
}