package com.samet.erdem.tracker.model;

/**
 * @class Food
 * @brief Abstract base class for all food items in the system.
 * @details This class defines the basic properties and behaviors that all food items must have.
 */
public abstract class Food implements ITrackable {
    private String id;
    private String name;
    private double servingSize;
    private String servingUnit;
    private NutritionInfo nutritionInfo;
    private String status;
    
    protected Food(String id, String name, double servingSize, String servingUnit) {
        this.id = id;
        this.name = name;
        this.servingSize = servingSize;
        this.servingUnit = servingUnit;
        this.nutritionInfo = new NutritionInfo();
        this.status = "Active";
    }
    
    // ITrackable interface implementation
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getStatus() {
        return status;
    }
    
    @Override
    public void updateStatus(String status) {
        this.status = status;
    }
    
    // Getters and Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getServingSize() {
        return servingSize;
    }
    
    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }
    
    public String getServingUnit() {
        return servingUnit;
    }
    
    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }
    
    public NutritionInfo getNutritionInfo() {
        return nutritionInfo;
    }
    
    public void setNutritionInfo(NutritionInfo nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }
    
    // Abstract methods that must be implemented by subclasses
    public abstract double calculateCalories();
    public abstract String getFoodType();
} 