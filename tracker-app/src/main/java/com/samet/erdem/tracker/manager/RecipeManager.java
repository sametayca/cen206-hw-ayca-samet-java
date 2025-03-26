package com.samet.erdem.tracker.manager;

import com.samet.erdem.tracker.model.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @class RecipeManager
 * @brief Singleton class managing recipe operations.
 * @details This class implements the Singleton pattern and manages all recipe-related operations.
 */
public class RecipeManager {
    private static RecipeManager instance;
    private List<Recipe> recipes;
    
    private RecipeManager() {
        recipes = new ArrayList<>();
    }
    
    public static RecipeManager getInstance() {
        if (instance == null) {
            instance = new RecipeManager();
        }
        return instance;
    }
    
    // Recipe Management Methods
    public Recipe createRecipe(String name, double servingSize, String servingUnit) {
        String id = UUID.randomUUID().toString();
        Recipe recipe = new Recipe(id, name, servingSize, servingUnit);
        recipes.add(recipe);
        return recipe;
    }
    
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }
    
    public Recipe getRecipeById(String id) {
        return recipes.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }
    
    public List<Recipe> searchRecipes(String keyword) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }
    
    public void updateRecipe(Recipe recipe) {
        int index = -1;
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getId().equals(recipe.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            recipes.set(index, recipe);
        }
    }
    
    public void clearAllRecipes() {
        recipes.clear();
    }
    
    public int getRecipeCount() {
        return recipes.size();
    }
} 