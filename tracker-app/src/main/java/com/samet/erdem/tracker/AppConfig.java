package com.samet.erdem.tracker;

/**
 * @class AppConfig
 * @brief Holds global configuration settings for the application.
 * @details Currently used to toggle test mode for GUI testing and automation.
 */
public class AppConfig {

    /**
     * @brief Flag indicating whether the application is in test mode.
     * @details If true, certain UI behaviors (like visibility) may be skipped during testing.
     */
    public static boolean isTestMode = false;
}
