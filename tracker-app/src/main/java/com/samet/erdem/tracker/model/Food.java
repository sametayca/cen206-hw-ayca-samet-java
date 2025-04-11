/**
 * @file Food.java
 * @brief Abstract base class for all food items in the nutrition tracking system.
 * @details This file contains the implementation of the Food abstract class,
 *          which serves as the foundation for all food items in the system.
 */
package com.samet.erdem.tracker.model;

/**
 * @class Food
 * @brief Abstract base class for all food items in the system.
 * @details This class defines the basic properties and behaviors that all food items must have.
 *          It implements the ITrackable interface and provides common functionality for
 *          food-related entities such as ingredients and recipes.
 *          
 *          The class stores essential food information including:
 *          - Identification (id, name)
 *          - Serving information (size and unit)
 *          - Nutritional content
 *          - Status tracking
 */
public abstract class Food implements ITrackable {
    /**
     * @brief Unique identifier for the food item.
     * @details This property stores the unique identifier for the food item.
     */
    private String id;

    /**
     * @brief Name or description of the food item.
     * @details This property stores the name or description of the food item.
     */
    private String name;

    /**
     * @brief Size of a single serving.
     * @details This property stores the size of a single serving of the food item.
     */
    private double servingSize;

    /**
     * @brief Unit of measurement for the serving size.
     * @details This property stores the unit of measurement for the serving size.
     */
    private String servingUnit;

    /**
     * @brief Nutritional content of the food item.
     * @details This property stores the nutritional content of the food item.
     */
    private NutritionInfo nutritionInfo;

    /**
     * @brief Status tracking for the food item.
     * @details This property stores the status of the food item, such as "Active" or "Inactive".
     */
    private String status;
    
    /**
     * @brief Constructor for creating a new food item.
     * @details Initializes a food item with the specified ID, name, serving size, and unit.
     *          Also creates an empty nutrition info object and sets the status to "Active".
     * 
     * @param id The unique identifier for the food item.
     * @param name The name or description of the food item.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving (e.g., "g", "ml", "oz").
     */
    /**
     * @brief Constructor for creating a new food item.
     * @details Initializes a food item with the specified ID, name, serving size, and unit.
     *          Also creates an empty nutrition info object and sets the status to "Active".
     * 
     * @param id The unique identifier for the food item.
     * @param name The name or description of the food item.
     * @param servingSize The size of a single serving.
     * @param servingUnit The unit of measurement for the serving (e.g., "g", "ml", "oz").
     */
    protected Food(String id, String name, double servingSize, String servingUnit) {
        this.id = id;
        this.name = name;
        this.servingSize = servingSize;
        this.servingUnit = servingUnit;
        this.nutritionInfo = new NutritionInfo();
        this.status = "Active";
    }
    
    // ITrackable interface implementation
    /**
     * @brief Gets the food item's ID.
     * @details Implementation of the ITrackable interface method.
     * 
     * @return The food item's unique identifier.
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * @brief Gets the food item's name.
     * @details Implementation of the ITrackable interface method.
     * 
     * @return The food item's name.
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * @brief Gets the food item's status.
     * @details Implementation of the ITrackable interface method.
     *          Status can be used to track whether the food item is active, deleted, etc.
     * 
     * @return The current status of the food item.
     */
    @Override
    public String getStatus() {
        return status;
    }
    
    /**
     * @brief Updates the food item's status.
     * @details Implementation of the ITrackable interface method.
     *          This method allows changing the status of the food item (e.g., from "Active" to "Deleted").
     * 
     * @param status The new status to set.
     */
    @Override
    public void updateStatus(String status) {
        this.status = status;
    }
    
    // Getters and Setters
    /**
     * @brief Sets the food item's ID.
     * @details Updates the unique identifier of this food item.
     * 
     * @param id The new ID to assign to the food item.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @brief Sets the food item's name.
     * @details Updates the name or description of this food item.
     * 
     * @param name The new name to assign to the food item.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @brief Gets the food item's serving size.
     * @details Returns the size of a single serving of this food item.
     * 
     * @return The serving size.
     */
    public double getServingSize() {
        return servingSize;
    }
    
    /**
     * @brief Sets the food item's serving size.
     * @details Updates the size of a single serving of this food item.
     * 
     * @param servingSize The new serving size.
     */
    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }
    
    /**
     * @brief Gets the food item's serving unit.
     * @details Returns the unit of measurement for a serving of this food item.
     * 
     * @return The serving unit (e.g., "g", "ml", "oz").
     */
    public String getServingUnit() {
        return servingUnit;
    }
    
    /**
     * @brief Sets the food item's serving unit.
     * @details Updates the unit of measurement for a serving of this food item.
     * 
     * @param servingUnit The new serving unit (e.g., "g", "ml", "oz").
     */
    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }
    
    /**
     * @brief Gets the food item's nutritional information.
     * @details Returns the nutritional profile associated with this food item.
     * 
     * @return The NutritionInfo object containing nutritional values.
     */
    public NutritionInfo getNutritionInfo() {
        return nutritionInfo;
    }
    
    /**
     * @brief Sets the food item's nutritional information.
     * @details Updates the nutritional profile associated with this food item.
     * 
     * @param nutritionInfo The new NutritionInfo object to assign.
     */
    public void setNutritionInfo(NutritionInfo nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }
    
    /**
     * @brief Calculates the caloric content of this food item.
     * @details Abstract method that must be implemented by subclasses to calculate
     *          the calories based on their specific implementation.
     * 
     * @return The calculated calories.
     */
    public abstract double calculateCalories();
    
    /**
     * @brief Gets the type of food item.
     * @details Abstract method that must be implemented by subclasses to identify
     *          the specific type of food (e.g., "Ingredient", "Recipe").
     * 
     * @return A string identifying the food type.
     */
    public abstract String getFoodType();
}