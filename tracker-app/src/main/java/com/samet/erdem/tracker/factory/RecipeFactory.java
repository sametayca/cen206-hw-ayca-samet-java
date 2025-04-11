/**
 * @file RecipeFactory.java
 * @brief Factory class for creating different types of recipes.
 * @details This file contains the implementation of the RecipeFactory class,
 *          which provides a set of static methods for creating recipe and ingredient objects.
 */
package com.samet.erdem.tracker.factory;

import com.samet.erdem.tracker.model.Recipe;
import com.samet.erdem.tracker.model.Ingredient;
import java.util.List;
import java.util.UUID;

/**
 * @class RecipeFactory
 * @brief Factory class for creating different types of recipes.
 * @details This class implements the Factory design pattern for creating recipe and ingredient objects.
 *          It provides a set of static methods that encapsulate the creation logic for different
 *          types of recipes and ingredients, with varying levels of complexity and attributes.
 *          
 *          The Factory pattern is used here to:
 *          - Centralize object creation logic
 *          - Provide a consistent way to create recipes and ingredients
 *          - Hide the complexity of object initialization
 *          - Generate unique IDs for each created object
 */
public class RecipeFactory {
    /**
     * @brief Creates a basic recipe with minimal information.
     * @details Creates a new Recipe object with just the essential information:
     *          a name, serving size, and serving unit. The method automatically
     *          generates a unique ID for the recipe using UUID.
     * 
     * @param name The name of the recipe.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving size (e.g., "g", "ml", "piece").
     * @return A new Recipe object with the specified attributes and a unique ID.
     */
    public static Recipe createBasicRecipe(String name, double servingSize, String servingUnit) {
        return new Recipe(UUID.randomUUID().toString(), name, servingSize, servingUnit);
    }
    
    /**
     * @brief Creates a recipe with ingredients.
     * @details Creates a new Recipe object with name, serving information, and a list of ingredients.
     *          This method builds upon the basic recipe by adding ingredients to it.
     * 
     * @param name The name of the recipe.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving size.
     * @param ingredients A list of Ingredient objects that make up the recipe.
     * @return A new Recipe object with the specified attributes, ingredients, and a unique ID.
     */
    public static Recipe createRecipeWithIngredients(String name, double servingSize, String servingUnit, List<Ingredient> ingredients) {
        Recipe recipe = createBasicRecipe(name, servingSize, servingUnit);
        recipe.setIngredients(ingredients);
        return recipe;
    }
    
    /**
     * @brief Creates a complete recipe with all information.
     * @details Creates a comprehensive Recipe object with all possible attributes:
     *          name, serving information, ingredients, cooking instructions, preparation time,
     *          cooking time, and number of servings. This method provides the most detailed
     *          recipe creation option.
     * 
     * @param name The name of the recipe.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving size.
     * @param ingredients A list of Ingredient objects that make up the recipe.
     * @param instructions A list of step-by-step instructions for preparing the recipe.
     * @param preparationTime The time required for preparation in minutes.
     * @param cookingTime The time required for cooking in minutes.
     * @param servings The number of servings the recipe yields.
     * @return A fully detailed Recipe object with all specified attributes and a unique ID.
     */
    public static Recipe createCompleteRecipe(String name, double servingSize, String servingUnit, 
                                            List<Ingredient> ingredients, List<String> instructions,
                                            int preparationTime, int cookingTime, int servings) {
        Recipe recipe = createRecipeWithIngredients(name, servingSize, servingUnit, ingredients);
        recipe.setInstructions(instructions);
        recipe.setPreparationTime(preparationTime);
        recipe.setCookingTime(cookingTime);
        recipe.setServings(servings);
        return recipe;
    }
    
    /**
     * @brief Creates a basic ingredient.
     * @details Creates a new Ingredient object with the essential information:
     *          a name, serving size, and serving unit. The method automatically
     *          generates a unique ID for the ingredient using UUID.
     * 
     * @param name The name of the ingredient.
     * @param servingSize The size of a single serving of the ingredient.
     * @param servingUnit The unit of measurement for the serving size (e.g., "g", "ml", "tbsp").
     * @return A new Ingredient object with the specified attributes and a unique ID.
     */
    public static Ingredient createIngredient(String name, double servingSize, String servingUnit) {
        return new Ingredient(UUID.randomUUID().toString(), name, servingSize, servingUnit);
    }
    
    /**
     * @brief Creates an ingredient marked as an allergen.
     * @details Creates a new Ingredient object and marks it as an allergen.
     *          This is useful for tracking potential allergens in recipes
     *          and providing warnings to users with allergies.
     * 
     * @param name The name of the ingredient.
     * @param servingSize The size of a single serving of the ingredient.
     * @param servingUnit The unit of measurement for the serving size.
     * @return A new Ingredient object marked as an allergen with the specified attributes and a unique ID.
     */
    public static Ingredient createAllergenIngredient(String name, double servingSize, String servingUnit) {
        Ingredient ingredient = createIngredient(name, servingSize, servingUnit);
        ingredient.setAllergen(true);
        return ingredient;
    }
    
    /**
     * @brief Creates an ingredient with a specific category.
     * @details Creates a new Ingredient object and assigns it to a specific category.
     *          Categories can be used to organize ingredients (e.g., "Dairy", "Vegetables", "Grains").
     * 
     * @param name The name of the ingredient.
     * @param servingSize The size of a single serving of the ingredient.
     * @param servingUnit The unit of measurement for the serving size.
     * @param category The category to which the ingredient belongs.
     * @return A new Ingredient object with the specified category, attributes, and a unique ID.
     */
    public static Ingredient createCategorizedIngredient(String name, double servingSize, String servingUnit, String category) {
        Ingredient ingredient = createIngredient(name, servingSize, servingUnit);
        ingredient.setCategory(category);
        return ingredient;
    }
} 