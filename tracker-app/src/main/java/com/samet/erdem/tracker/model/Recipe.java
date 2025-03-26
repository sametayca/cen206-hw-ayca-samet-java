package com.samet.erdem.tracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @class Recipe
 * @brief Class representing a recipe in the system.
 * @details This class extends Food and adds recipe-specific functionality.
 */
public class Recipe extends Food {
    private List<Ingredient> ingredients;
    private List<String> instructions;
    private int preparationTime;
    private int cookingTime;
    private int servings;
    
    public Recipe(String id, String name, double servingSize, String servingUnit) {
        super(id, name, servingSize, servingUnit);
        this.ingredients = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.preparationTime = 0;
        this.cookingTime = 0;
        this.servings = 1;
    }
    
    // Getters and Setters
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        calculateTotalNutrition();
    }
    
    public List<String> getInstructions() {
        return instructions;
    }
    
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
    
    public int getPreparationTime() {
        return preparationTime;
    }
    
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
    
    public int getCookingTime() {
        return cookingTime;
    }
    
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
    
    public int getServings() {
        return servings;
    }
    
    public void setServings(int servings) {
        this.servings = servings;
        calculateTotalNutrition();
    }
    
    // Methods
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        calculateTotalNutrition();
    }
    
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        calculateTotalNutrition();
    }
    
    public void addInstruction(String instruction) {
        instructions.add(instruction);
    }
    
    private void calculateTotalNutrition() {
        NutritionInfo total = new NutritionInfo();
        for (Ingredient ingredient : ingredients) {
            total = total.add(ingredient.getNutritionInfo());
        }
        setNutritionInfo(total);
    }
    
    @Override
    public double calculateCalories() {
        return getNutritionInfo().getCalories() / servings;
    }
    
    @Override
    public String getFoodType() {
        return "Recipe";
    }
    
    public int getTotalTime() {
        return preparationTime + cookingTime;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe: ").append(getName()).append("\n");
        sb.append("Servings: ").append(servings).append("\n");
        sb.append("Preparation Time: ").append(preparationTime).append(" minutes\n");
        sb.append("Cooking Time: ").append(cookingTime).append(" minutes\n");
        sb.append("Total Time: ").append(getTotalTime()).append(" minutes\n");
        sb.append("Nutrition per serving:\n").append(getNutritionInfo());
        return sb.toString();
    }
} 