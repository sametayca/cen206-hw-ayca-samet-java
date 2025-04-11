/**
 * @file ITrackable.java
 * @brief Interface for trackable items in the nutrition tracking system.
 * @details This file contains the implementation of the ITrackable interface,
 *          which defines the basic tracking functionality that all trackable items must implement.
 */
package com.samet.erdem.tracker.model;

/**
 * @interface ITrackable
 * @brief Interface for trackable items in the system.
 * @details This interface defines the basic tracking functionality that all trackable items must implement.
 */
public interface ITrackable {
    /**
     * @brief Get the unique identifier of the trackable item.
     * @return String representing the unique identifier.
     */
    String getId();
    
    /**
     * @brief Get the name of the trackable item.
     * @return String representing the name.
     */
    String getName();
    
    /**
     * @brief Get the current status of the trackable item.
     * @return String representing the status.
     */
    String getStatus();
    
    /**
     * @brief Update the status of the trackable item.
     * @param status The new status to set.
     */
    void updateStatus(String status);
} 