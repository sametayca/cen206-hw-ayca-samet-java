/**
 * @file ConsoleNutritionObserver.java
 * @author Samet ERDEM
 * @brief An observer class that prints the nutrition data to the console when
 *        the data changes.
 * @details When the observed data changes, this class prints the new data in
 *          a formatted way to the console.
 * @note The formatting of the output is subject to change.
 */
package com.samet.erdem.tracker.observer;
/**
 * @package com.samet.erdem.tracker.observer
 * @brief Package for observer classes that react to nutrition-related events.
 * @details This package contains the implementation of the observer pattern for
 *          nutrition tracking. It includes classes like ConsoleNutritionObserver
 *          that react to nutrition information updates, goal achievements, and
 *          warnings by displaying formatted messages in the console.
 */

import com.samet.erdem.tracker.model.NutritionInfo;

/**
 * @class ConsoleNutritionObserver
 * @brief Console-based implementation of NutritionObserver.
 * @details This class implements the NutritionObserver interface to provide console-based
 *          output for nutrition-related events. It displays formatted messages in the console
 *          when nutrition information is updated, goals are reached, or warnings are triggered.
 *          This implementation is part of the Observer design pattern, where it acts as a concrete
 *          observer that responds to notifications from the NutritionTracker subject.
 */
public class ConsoleNutritionObserver implements NutritionObserver {
    /**
     * @brief Handles nutrition information updates by displaying them in the console.
     * @details When nutrition information is updated in the system, this method is called
     *          to display the updated information in a formatted way in the console.
     *          The method creates a visually distinct section with headers and separators
     *          to make the nutrition update stand out in the console output.
     * 
     * @param nutritionInfo The updated nutrition information object containing details like
     *                      calories, protein, carbohydrates, fat, and fiber values.
     */
    @Override
    public void onNutritionUpdate(NutritionInfo nutritionInfo) {
        System.out.println("\n=== Nutrition Update ===");
        System.out.println(nutritionInfo);
        System.out.println("=====================\n");
    }
    
    /**
     * @brief Handles goal achievement notifications by displaying them in the console.
     * @details When a nutrition-related goal is achieved (such as reaching daily protein intake),
     *          this method is called to display a congratulatory message in the console.
     *          The method formats the message with distinct headers to make the achievement
     *          notification visually prominent for the user.
     * 
     * @param message A descriptive message about the specific goal that was achieved,
     *                such as "Daily protein goal reached!" or "Calorie target met!".
     */
    @Override
    public void onGoalReached(String message) {
        System.out.println("\n=== Goal Achieved! ===");
        System.out.println(message);
        System.out.println("=====================\n");
    }
    
    /**
     * @brief Handles warning notifications by displaying them in the console.
     * @details When a nutrition-related warning is triggered (such as excessive calorie intake),
     *          this method is called to display an alert message in the console.
     *          The method formats the warning with distinct headers to ensure the user
     *          notices the important information that requires attention.
     * 
     * @param message A descriptive warning message explaining the issue that requires attention,
     *                such as "Calorie intake is significantly above daily goal!".
     */
    @Override
    public void onWarning(String message) {
        System.out.println("\n=== Warning! ===");
        System.out.println(message);
        System.out.println("=====================\n");
    }
} 