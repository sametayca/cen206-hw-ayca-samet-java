package com.samet.erdem.tracker.model;

/**
 * @class Ingredient
 * @brief Class representing an ingredient in a recipe.
 * @details This class extends Food and adds ingredient-specific functionality.
 */
public class Ingredient extends Food {
    private String category;
    private boolean isAllergen;
    
    public Ingredient(String id, String name, double servingSize, String servingUnit) {
        super(id, name, servingSize, servingUnit);
        this.category = "Uncategorized";
        this.isAllergen = false;
    }
    
    // Getters and Setters
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public boolean isAllergen() {
        return isAllergen;
    }
    
    public void setAllergen(boolean allergen) {
        isAllergen = allergen;
    }
    
    @Override
    public double calculateCalories() {
        return getNutritionInfo().getCalories();
    }
    
    @Override
    public String getFoodType() {
        return "Ingredient";
    }
    
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