package com.samet.erdem.tracker.factory;

import com.samet.erdem.tracker.model.Recipe;
import com.samet.erdem.tracker.model.Ingredient;
import java.util.List;
import java.util.UUID;

/**
 * @class RecipeFactory
 * @brief Factory class for creating different types of recipes.
 * @details This class implements the Factory pattern for creating recipe objects.
 */
public class RecipeFactory {
    public static Recipe createBasicRecipe(String name, double servingSize, String servingUnit) {
        return new Recipe(UUID.randomUUID().toString(), name, servingSize, servingUnit);
    }
    
    public static Recipe createRecipeWithIngredients(String name, double servingSize, String servingUnit, List<Ingredient> ingredients) {
        Recipe recipe = createBasicRecipe(name, servingSize, servingUnit);
        recipe.setIngredients(ingredients);
        return recipe;
    }
    
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
    
    public static Ingredient createIngredient(String name, double servingSize, String servingUnit) {
        return new Ingredient(UUID.randomUUID().toString(), name, servingSize, servingUnit);
    }
    
    public static Ingredient createAllergenIngredient(String name, double servingSize, String servingUnit) {
        Ingredient ingredient = createIngredient(name, servingSize, servingUnit);
        ingredient.setAllergen(true);
        return ingredient;
    }
    
    public static Ingredient createCategorizedIngredient(String name, double servingSize, String servingUnit, String category) {
        Ingredient ingredient = createIngredient(name, servingSize, servingUnit);
        ingredient.setCategory(category);
        return ingredient;
    }
} 