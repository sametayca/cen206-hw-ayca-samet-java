package com.samet.erdem.tracker.observer;

import com.samet.erdem.tracker.model.NutritionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @class NutritionTracker
 * @brief Class for tracking nutrition information and notifying observers.
 * @details This class implements the Observer pattern for nutrition tracking.
 */
public class NutritionTracker {
    private List<NutritionObserver> observers;
    private NutritionInfo currentNutrition;
    private NutritionInfo dailyGoals;
    
    public NutritionTracker() {
        observers = new ArrayList<>();
        currentNutrition = new NutritionInfo();
        dailyGoals = new NutritionInfo(2000, 50, 250, 70, 25); // Default daily goals
    }
    
    public void addObserver(NutritionObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(NutritionObserver observer) {
        observers.remove(observer);
    }
    
    public void updateNutrition(NutritionInfo newNutrition) {
        currentNutrition = currentNutrition.add(newNutrition);
        notifyObservers();
        checkGoals();
    }
    
    public void setDailyGoals(NutritionInfo goals) {
        this.dailyGoals = goals;
        checkGoals();
    }
    
    private void notifyObservers() {
        for (NutritionObserver observer : observers) {
            observer.onNutritionUpdate(currentNutrition);
        }
    }
    
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
    
    private void notifyGoalReached(String message) {
        for (NutritionObserver observer : observers) {
            observer.onGoalReached(message);
        }
    }
    
    private void notifyWarning(String message) {
        for (NutritionObserver observer : observers) {
            observer.onWarning(message);
        }
    }
    
    public NutritionInfo getCurrentNutrition() {
        return currentNutrition;
    }
    
    public NutritionInfo getDailyGoals() {
        return dailyGoals;
    }
    
    public void resetDailyNutrition() {
        currentNutrition = new NutritionInfo();
        notifyObservers();
    }
} 