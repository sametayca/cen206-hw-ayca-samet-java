package com.samet.erdem.tracker.observer;

import com.samet.erdem.tracker.model.NutritionInfo;

/**
 * @class ConsoleNutritionObserver
 * @brief Console-based implementation of NutritionObserver.
 * @details This class implements the NutritionObserver interface for console output.
 */
public class ConsoleNutritionObserver implements NutritionObserver {
    @Override
    public void onNutritionUpdate(NutritionInfo nutritionInfo) {
        System.out.println("\n=== Nutrition Update ===");
        System.out.println(nutritionInfo);
        System.out.println("=====================\n");
    }
    
    @Override
    public void onGoalReached(String message) {
        System.out.println("\n=== Goal Achieved! ===");
        System.out.println(message);
        System.out.println("=====================\n");
    }
    
    @Override
    public void onWarning(String message) {
        System.out.println("\n=== Warning! ===");
        System.out.println(message);
        System.out.println("=====================\n");
    }
} 