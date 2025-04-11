/**

@file TrackerApp.java
@brief This file serves as the main application file for the Tracker App.
@details This file contains the entry point of the application, which is the main method. It initializes the necessary components and executes the Tracker App.
*/
/**

@package com.samet.erdem.tracker
@brief The com.samet.erdem.tracker package contains all the classes and files related to the Tracker App.
*/
package com.samet.erdem.tracker;

/**
 * @class TrackerApp
 * @brief Main application class for the Recipe and Nutrition Tracker.
 */
public class TrackerApp {
    /**
     * @brief Main method that serves as the entry point of the application.
     * @details Initializes the Tracker object and starts the application.
     *          This method is the starting point of the program and is executed
     *          when the application is launched.
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.start();
    }
}
  
