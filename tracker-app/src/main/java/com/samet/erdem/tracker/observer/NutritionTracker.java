/**
 * @file NutritionTracker.java
 * @brief Implementation of the nutrition tracking system using the Observer pattern.
 * @details This file contains the implementation of the NutritionTracker class, which
 *          serves as the subject in the Observer design pattern for nutrition tracking.
 */
package com.samet.erdem.tracker.observer;

import com.samet.erdem.tracker.model.NutritionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @class NutritionTracker
 * @brief Class for tracking nutrition information and notifying observers.
 * @details This class implements the Subject role in the Observer design pattern for nutrition tracking.
 *          It maintains a list of registered observers, tracks current nutrition information,
 *          and defines daily nutritional goals. When nutrition information is updated or goals are
 *          reached, it notifies all registered observers.
 *          
 *          The class provides functionality to:
 *          - Register and remove nutrition observers
 *          - Update current nutrition information
 *          - Set and modify daily nutritional goals
 *          - Check if nutritional goals have been reached
 *          - Notify observers of updates, goal achievements, and warnings
 */
public class NutritionTracker {
    /**
     * @brief List of registered nutrition observers.
     */
    private List<NutritionObserver> observers;
    /**
     * @brief Current nutrition information.
     */
    private NutritionInfo currentNutrition;
    /**
     * @brief Daily nutritional goals.
     */
    private NutritionInfo dailyGoals;
    
    /**
     * @brief Default constructor for the NutritionTracker class.
     * @details Initializes the observer list, creates an empty current nutrition object,
     *          and sets default daily nutritional goals. The default goals are:
     *          - 2000 calories
     *          - 50g protein
     *          - 250g carbohydrates
     *          - 70g fat
     *          - 25g fiber
     *          These values represent a general balanced diet for an average adult.
     */
    public NutritionTracker() {
        observers = new ArrayList<>();
        currentNutrition = new NutritionInfo();
        dailyGoals = new NutritionInfo(2000, 50, 250, 70, 25); // Default daily goals
    }
    
    /**
     * @brief Registers a new observer to receive nutrition notifications.
     * @details Adds the specified observer to the list of registered observers.
     *          Once registered, the observer will receive notifications about
     *          nutrition updates, goal achievements, and warnings.
     * 
     * @param observer The NutritionObserver implementation to register.
     *                 This could be any class that implements the NutritionObserver interface.
     */
    public void addObserver(NutritionObserver observer) {
        observers.add(observer);
    }
    
    /**
     * @brief Removes an observer from the notification list.
     * @details Removes the specified observer from the list of registered observers.
     *          After removal, the observer will no longer receive notifications from this tracker.
     * 
     * @param observer The NutritionObserver implementation to remove from the notification list.
     */
    public void removeObserver(NutritionObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * @brief Updates the current nutrition information and triggers notifications.
     * @details Adds the new nutrition information to the current tracked values,
     *          notifies all observers about the update, and checks if any goals
     *          have been reached as a result of this update.
     * 
     * @param newNutrition A NutritionInfo object containing the nutritional values to add
     *                     to the current tracked nutrition information.
     */
    public void updateNutrition(NutritionInfo newNutrition) {
        currentNutrition = currentNutrition.add(newNutrition);
        notifyObservers();
        checkGoals();
    }
    
    /**
     * @brief Sets new daily nutritional goals.
     * @details Updates the daily nutritional goals with the provided values and
     *          checks if any of the new goals have already been reached with the
     *          current nutrition information.
     * 
     * @param goals A NutritionInfo object containing the new daily goal values for
     *              calories, protein, carbohydrates, fat, and fiber.
     */
    public void setDailyGoals(NutritionInfo goals) {
        this.dailyGoals = goals;
        checkGoals();
    }
    
    /**
     * @brief Notifies all registered observers about nutrition updates.
     * @details Iterates through all registered observers and calls their
     *          onNutritionUpdate method, passing the current nutrition information.
     *          This method is called whenever the nutrition information changes.
     */
    private void notifyObservers() {
        for (NutritionObserver observer : observers) {
            observer.onNutritionUpdate(currentNutrition);
        }
    }
    
    /**
     * @brief Checks if any nutritional goals have been reached.
     * @details Compares the current nutrition values with the daily goals and
     *          triggers appropriate notifications when goals are reached or exceeded.
     *          This method checks each nutritional component (calories, protein,
     *          carbohydrates, fat, and fiber) individually and sends specific
     *          notifications for each goal that has been reached.
     *          
     *          It also checks for excessive intake (more than 20% above the goal)
     *          and triggers warning notifications in such cases.
     */
    private void checkGoals() {
        // Check calories
        if (currentNutrition.getCalories() >= dailyGoals.getCalories()) {
            notifyGoalReached("Daily calorie goal reached!");
        }
        
        // Check protein
        if (currentNutrition.getProtein() >= dailyGoals.getProtein()) {
            notifyGoalReached("Daily protein goal reached!");
        }
        
        // Check carbohydrates
        if (currentNutrition.getCarbohydrates() >= dailyGoals.getCarbohydrates()) {
            notifyGoalReached("Daily carbohydrate goal reached!");
        }
        
        // Check fat
        if (currentNutrition.getFat() >= dailyGoals.getFat()) {
            notifyGoalReached("Daily fat goal reached!");
        }
        
        // Check fiber
        if (currentNutrition.getFiber() >= dailyGoals.getFiber()) {
            notifyGoalReached("Daily fiber goal reached!");
        }
        
        // Check for warnings
        if (currentNutrition.getCalories() > dailyGoals.getCalories() * 1.2) {
            notifyWarning("Calorie intake is significantly above daily goal!");
        }
    }
    
    /**
     * @brief Notifies all registered observers about a goal being reached.
     * @details Iterates through all registered observers and calls their
     *          onGoalReached method, passing the specified message.
     *          This method is called when a nutritional goal has been reached.
     * 
     * @param message A descriptive message indicating which goal has been reached.
     */
    private void notifyGoalReached(String message) {
        for (NutritionObserver observer : observers) {
            observer.onGoalReached(message);
        }
    }
    
    /**
     * @brief Notifies all registered observers about a warning condition.
     * @details Iterates through all registered observers and calls their
     *          onWarning method, passing the specified warning message.
     *          This method is called when a warning condition is detected,
     *          such as excessive intake of a particular nutrient.
     * 
     * @param message A descriptive warning message explaining the issue.
     */
    private void notifyWarning(String message) {
        for (NutritionObserver observer : observers) {
            observer.onWarning(message);
        }
    }
    
    /**
     * @brief Gets the current nutrition information.
     * @details Returns a reference to the current nutrition information object,
     *          which contains the tracked values for calories, protein,
     *          carbohydrates, fat, and fiber.
     * 
     * @return The current NutritionInfo object with accumulated nutritional values.
     */
    public NutritionInfo getCurrentNutrition() {
        return currentNutrition;
    }
    
    /**
     * @brief Gets the current daily nutritional goals.
     * @details Returns a reference to the daily goals nutrition information object,
     *          which contains the target values for calories, protein,
     *          carbohydrates, fat, and fiber.
     * 
     * @return The NutritionInfo object containing the daily nutritional goals.
     */
    public NutritionInfo getDailyGoals() {
        return dailyGoals;
    }
    
    /**
     * @brief Resets the daily nutrition tracking to zero.
     * @details Creates a new empty NutritionInfo object and sets it as the current
     *          nutrition information, effectively resetting all tracked nutritional
     *          values to zero. Also notifies all observers about this reset.
     *          This method is typically called at the start of a new day to begin
     *          fresh nutrition tracking.
     */
    public void resetDailyNutrition() {
        currentNutrition = new NutritionInfo();
        notifyObservers();
    }
} 