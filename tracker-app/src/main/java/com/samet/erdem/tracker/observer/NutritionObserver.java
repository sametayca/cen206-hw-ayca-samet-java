package com.samet.erdem.tracker.observer;

import com.samet.erdem.tracker.model.NutritionInfo;

/**
 * @interface NutritionObserver
 * @brief Interface for nutrition tracking observers.
 * @details This interface defines the contract for nutrition tracking observers.
 */
public interface NutritionObserver {
    /**
     * @brief Called when nutrition information is updated.
     * @param nutritionInfo The updated nutrition information.
     */
    void onNutritionUpdate(NutritionInfo nutritionInfo);
    
    /**
     * @brief Called when a nutrition goal is reached.
     * @param message The message describing the goal achievement.
     */
    void onGoalReached(String message);
    
    /**
     * @brief Called when a nutrition warning is triggered.
     * @param message The warning message.
     */
    void onWarning(String message);
} 