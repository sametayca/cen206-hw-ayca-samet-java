/**
 * @file RecipeManager.java
 * @brief Singleton class for managing recipe operations in the nutrition tracking system.
 * @details This file contains the implementation of the RecipeManager class,
 *          which implements the Singleton design pattern for centralized recipe management.
 */
package com.samet.erdem.tracker.manager;

import com.samet.erdem.tracker.model.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @class RecipeManager
 * @brief Singleton class managing recipe operations.
 * @details This class implements the Singleton design pattern to provide a centralized
 *          management system for recipes in the application. It maintains a collection
 *          of recipes and provides methods for creating, retrieving, updating, and
 *          deleting recipes.
 *          
 *          The Singleton pattern ensures that only one instance of RecipeManager exists
 *          throughout the application, providing a global point of access to recipe operations.
 *          
 *          The class provides functionality to:
 *          - Create and store new recipes
 *          - Retrieve recipes by ID or search criteria
 *          - Update existing recipes
 *          - Remove recipes from the collection
 *          - Manage the entire recipe collection
 */
public class RecipeManager {
    /**
     * @brief Singleton instance of the RecipeManager class.
     * @details This field stores the singleton instance of the RecipeManager class.
     *          It is used to ensure that only one instance of the class exists.
     */
    private static RecipeManager instance;
    /**
     * @brief Collection of recipes managed by the RecipeManager.
     * @details This field stores a list of Recipe objects that are managed by the RecipeManager.
     *          It is initialized in the constructor and is used to store and retrieve recipes.
     */
    private List<Recipe> recipes;
    
    /**
     * @brief Default constructor for the RecipeManager class.
     * @details Initializes a new RecipeManager with an empty recipe collection.
     *          This constructor is private to enforce the Singleton pattern,
     *          preventing direct instantiation from outside the class.
     */
    public RecipeManager() {
        recipes = new ArrayList<>();
    }
    
    /**
     * @brief Gets the singleton instance of the RecipeManager class.
     * @details Returns the existing instance of RecipeManager if it exists,
     *          or creates a new instance if one doesn't exist yet. This method
     *          ensures that only one instance of RecipeManager is used throughout
     *          the application.
     * 
     * @return The singleton instance of RecipeManager.
     */
    public static RecipeManager getInstance() {
        if (instance == null) {
            instance = new RecipeManager();
        }
        return instance;
    }
    
    // Recipe Management Methods
    /**
     * @brief Creates a new recipe and adds it to the collection.
     * @details Creates a new Recipe object with the specified attributes,
     *          generates a unique ID for it, adds it to the recipe collection,
     *          and returns the created recipe.
     * 
     * @param name The name of the recipe.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving size (e.g., "g", "ml", "piece").
     * @return The newly created Recipe object with a unique ID.
     */
    public Recipe createRecipe(String name, double servingSize, String servingUnit) {
        String id = UUID.randomUUID().toString();
        Recipe recipe = new Recipe(id, name, servingSize, servingUnit);
        recipes.add(recipe);
        return recipe;
    }
    
    /**
     * @brief Adds an existing recipe to the collection.
     * @details Adds a pre-created Recipe object to the recipe collection.
     *          This method is useful when importing recipes or when recipes
     *          are created using the RecipeFactory.
     * 
     * @param recipe The Recipe object to add to the collection.
     */
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    
    /**
     * @brief Removes a recipe from the collection.
     * @details Removes the specified Recipe object from the recipe collection.
     *          If the recipe is not in the collection, no action is taken.
     * 
     * @param recipe The Recipe object to remove from the collection.
     */
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }
    
    /**
     * @brief Retrieves a recipe by its ID.
     * @details Searches the recipe collection for a recipe with the specified ID
     *          and returns it if found. If no matching recipe is found, returns null.
     *          This method uses Java streams for efficient searching.
     * 
     * @param id The unique identifier of the recipe to retrieve.
     * @return The Recipe object with the specified ID, or null if not found.
     */
    public Recipe getRecipeById(String id) {
        return recipes.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * @brief Retrieves all recipes in the collection.
     * @details Returns a new list containing all recipes in the collection.
     *          The returned list is a copy of the internal collection to prevent
     *          external modification of the original collection.
     * 
     * @return A new List containing all Recipe objects in the collection.
     */
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }
    
    /**
     * @brief Searches for recipes by keyword.
     * @details Searches the recipe collection for recipes whose names contain
     *          the specified keyword (case-insensitive) and returns a list of
     *          matching recipes.
     * 
     * @param keyword The keyword to search for in recipe names.
     * @return A List of Recipe objects whose names contain the keyword.
     */
    public List<Recipe> searchRecipes(String keyword) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }
    
    /**
     * @brief Updates an existing recipe in the collection.
     * @details Finds a recipe in the collection with the same ID as the provided recipe
     *          and replaces it with the updated version. If no matching recipe is found,
     *          no action is taken.
     * 
     * @param recipe The updated Recipe object with the same ID as the recipe to update.
     */
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
    
    /**
     * @brief Removes all recipes from the collection.
     * @details Clears the entire recipe collection, removing all stored recipes.
     *          This method is useful for resetting the recipe database or when
     *          switching users.
     */
    public void clearAllRecipes() {
        recipes.clear();
    }
    
    /**
     * @brief Gets the number of recipes in the collection.
     * @details Returns the total count of recipes currently stored in the collection.
     * 
     * @return The number of Recipe objects in the collection.
     */
    public int getRecipeCount() {
        return recipes.size();
    }
} 