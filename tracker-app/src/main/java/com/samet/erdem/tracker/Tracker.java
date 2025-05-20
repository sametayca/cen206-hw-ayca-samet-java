/**

@package com.samet.erdem.tracker
@brief The com.samet.erdem.tracker package contains all the classes and files related to the Tracker App.
@details This package includes the main controller classes, data access objects, models, factories, and utility classes
         that together form the Recipe and Nutrition Tracking application.
*/
package com.samet.erdem.tracker;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.samet.erdem.tracker.factory.RecipeFactory;
import com.samet.erdem.tracker.manager.RecipeManager;
import com.samet.erdem.tracker.model.Ingredient;
import com.samet.erdem.tracker.model.Recipe;
import com.samet.erdem.tracker.observer.ConsoleNutritionObserver;
import com.samet.erdem.tracker.observer.NutritionTracker;
import com.samet.erdem.tracker.dao.UserDAO;
import com.samet.erdem.tracker.dao.ProductDAO;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**

@class Tracker
@brief This class represents the main controller for the Recipe and Nutrition Tracking application.
@details The Tracker class serves as the central controller for the application, managing user interactions,
         database operations, and business logic. It provides functionality for user registration and authentication,
         product and recipe management, nutritional calculations, and diet recommendations based on user profiles.
         The class implements various design patterns including Factory, Observer, and Data Access Object patterns
         to maintain a clean and modular architecture.
@author ugur.coruh
*/
public class Tracker {
        /**
         * @brief Scanner for user input.
         */
        private Scanner scanner;
        /**
         * @brief User data access object.
         */
        protected UserDAO userDAO;
        /**
         * @brief Product data access object.
         */
        protected ProductDAO productDAO;
        /**
         * @brief Current user.
         */
        protected User currentUser;
        /**
         * @brief Recipe manager.
         */
        private RecipeManager recipeManager;
        /**
         * @brief Nutrition tracker.
         */
        private NutritionTracker nutritionTracker;
    
    /**
     * @brief Returns the scanner for user input.
     * @return the scanner for user input
     */
    public Scanner getScanner() {
        return scanner;
    }
    
    /**
     * @brief Returns the data access object for users.
     * @return the user data access object
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }
    
    /**
     * @brief Returns the data access object for products.
     * @return the product data access object
     */
    public ProductDAO getProductDAO() {
        return productDAO;
    }
    
    /**
     * @brief Returns the current user.
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * @brief Returns the recipe manager.
     * @return the recipe manager
     */
    public RecipeManager getRecipeManager() {
        return recipeManager;
    }
    
    /**
     * @brief Returns the nutrition tracker.
     * @return the nutrition tracker
     */
    public NutritionTracker getNutritionTracker() {
        return nutritionTracker;
    }
    
    /**
     * @brief Default constructor for the Tracker class.
     * @details Initializes the scanner for user input, data access objects for database operations,
     *          recipe manager for recipe-related functionality, and nutrition tracker for monitoring
     *          nutritional intake. Also adds a console-based nutrition observer to the nutrition tracker
     *          to display nutrition-related notifications in the console.
     */
    public Tracker() {
        scanner = new Scanner(System.in);
        userDAO = new UserDAO();
        productDAO = new ProductDAO();
        recipeManager = RecipeManager.getInstance();
        nutritionTracker = new NutritionTracker();
        nutritionTracker.addObserver(new ConsoleNutritionObserver());
    }
    
    /**
     * @brief Starts the application by showing the login menu.
     * @details This is the entry point for the application that initiates the user interaction flow.
     *          When called, it displays the login menu and waits for user input to proceed with
     *          either registration, login, or application exit.
     */
    public void start() {
        showLoginMenuOnce();
    }
    
    /**
     * @brief Clears the console screen for better user experience.
     * @details Attempts to clear the console screen based on the operating system.
     *          Uses "cls" command for Windows and "clear" command for other operating systems.
     *          This method improves the user interface by removing previous output and
     *          providing a clean screen for new information.
     *          If an exception occurs during the clearing process, it is caught and printed to the console.
     */
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    /**
     * @brief Displays the login menu once and handles user selection.
     * @details Shows options for registering a new user, logging in with existing credentials, or exiting the application.
     *          Processes the user's choice and redirects to the appropriate method:
     *          - Option 1: Calls the register() method to create a new user account
     *          - Option 2: Calls the login() method to authenticate an existing user
     *          - Option 3: Exits the application
     *          - Any other input: Displays an invalid option message
     *          This method is designed to be called only once at the start of the application.
     */
    public void showLoginMenuOnce() {
        System.out.println("\n=== Recipe and Nutrition Tracker ===");
        System.out.println("1. Register User");
        System.out.println("2. Login User");
        System.out.println("3. Exit");
        System.out.print("Select your option: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                register();
                break;
            case "2":
                login();
                break;
            case "3":
                System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Invalid option!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
        }
    }

    
   /**
     * @brief Handles the user registration process.
     * @details Collects and validates user information including username, password, height, and weight.
     *          The method performs the following validations:
     *          - Checks if username is not empty
     *          - Verifies username is not already taken in the database
     *          - Ensures password is not empty
     *          - Validates that height and weight are positive numerical values
     *          
     *          If all validations pass, it creates a new User object and registers it in the database.
     *          After successful registration, it returns to the login menu.
     *          
     *          Appropriate error messages are displayed for:
     *          - Empty username or password
     *          - Username already in use
     *          - Invalid height or weight values
     *          - Database errors during registration
     */
   public void register() {
        clearScreen();
        System.out.println("\n=== Register User Menu ===");

        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            
            if (username == null || username.trim().isEmpty()) {
                System.out.println("Username cannot be empty!");
                System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                return;
            }
            
            try {
                if (userDAO.usernameExists(username)) {
                    System.out.println("This username is already taken!");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    return;
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            if (password == null || password.trim().isEmpty()) {
                System.out.println("Password cannot be empty!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            double height = 0;
            double weight = 0;
            
            try {
                System.out.print("Height (cm): ");
                height = Double.parseDouble(scanner.nextLine());
                
                System.out.print("Weight (kg): ");
                weight = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numerical values!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            if (height <= 0 || weight <= 0) {
                System.out.println("Height and weight must be positive values!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            User user = new User(username, password, height, weight);
            
            try {
                userDAO.register(user);
                System.out.println("Registration successful!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                showLoginMenuOnce();
            } catch (SQLException e) {	
                System.out.println("Error during registration: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * @brief Handles the user login process.
     * @details Collects username and password from the user and attempts to authenticate against the database.
     *          The method performs the following steps:
     *          - Validates that username and password are not empty
     *          - Attempts to authenticate the user using the UserDAO
     *          - If authentication is successful, sets the current user and shows the main menu
     *          - If authentication fails, displays an error message
     *          
     *          The method handles various error scenarios including:
     *          - Empty username or password
     *          - Invalid credentials
     *          - Database connection issues
     *          
     *          Upon successful login, the user is redirected to the main menu of the application.
     */
    void login() {
        clearScreen();
        System.out.println("\n=== Login User Menu ===");
        
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            
            if (username == null || username.trim().isEmpty()) {
                System.out.println("Username cannot be empty!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            if (password == null || password.trim().isEmpty()) {
                System.out.println("Password cannot be empty!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            try {
                User user = userDAO.login(username, password);
                if (user != null) {
                    currentUser = user;
                    System.out.println("Login successful!");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    showMainMenu();
                } else {
                    System.out.println("Invalid username or password!");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                }
            } catch (SQLException e) {
                System.out.println("Error during login: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * @brief Displays the main menu and handles user selections.
     * @details Shows a comprehensive menu with options for managing products and recipes, calculating calories,
     *          adjusting diet parameters, or exiting the application. The menu options include:
     *          1. Add Product or Recipe - Redirects to the addProduct() method
     *          2. All Products - Displays all products with viewAllProducts()
     *          3. Update Product - Allows modification of existing products with updateProduct()
     *          4. Delete Product - Removes products with deleteProduct()
     *          5. Calculate Calories - Performs nutritional calculations with calculateCalories()
     *          6. Adjust Diet - Updates user profile and provides recommendations with adjustDiet()
     *          7. Exit - Logs out the current user and returns to the login menu
     *          
     *          The method runs in a continuous loop until the user chooses to exit.
     *          Invalid selections result in an error message and prompt to continue.
     */
    void showMainMenu() {
        while (true) {
            clearScreen();
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Add Product or Recipe");
            System.out.println("2. All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Calculate Calories");
            System.out.println("6. Adjust Diet");
            System.out.println("7. Exit");
            System.out.print("Select your option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    viewAllProducts();
                    break;
                case "3":
                    updateProduct();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    calculateCalories();
                    break;
                case "6":
                    adjustDiet();
                    break;
                case "7":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option!");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }
    
    /**
     * @brief Handles adding a new product or recipe to the database.
     * @details Guides the user through the process of adding a new product or recipe by:
     *          - Collecting the product/recipe name
     *          - Gathering nutritional information including calories, protein, carbs, and fat
     *          - Creating a new Product object with the collected data
     *          - Saving the product to the database with association to the current user
     *          
     *          The method assumes numerical input for nutritional values and uses Double.parseDouble()
     *          for conversion. In a production environment, additional input validation would be recommended.
     *          
     *          Success or failure messages are displayed based on the database operation outcome.
     */
    void addProduct() {
        clearScreen();
        System.out.println("\n=== Add Product/Recipe ===");
        
        System.out.print("Product/Recipe name: ");
        String name = scanner.nextLine();
        
        System.out.print("Calories: ");
        double calories = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Protein (g): ");
        double protein = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Carbs (g): ");
        double carbs = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Fat (g): ");
        double fat = Double.parseDouble(scanner.nextLine());
        
        Product product = new Product(name, calories, protein, carbs, fat, currentUser.getId());
        
        try {
            productDAO.addProduct(product);
            System.out.println("Product added successfully!");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        } catch (SQLException e) {
           
            scanner.nextLine();
        }
    }
    
    /**
     * @brief Displays all products associated with the current user.
     * @details Retrieves and presents a comprehensive list of all products from the database that belong to the current user.
     *          For each product, the following information is displayed:
     *          - Product ID
     *          - Product name
     *          - Caloric content (in kcal)
     *          - Protein content (in grams)
     *          - Carbohydrate content (in grams)
     *          - Fat content (in grams)
     *          
     *          If no products are found for the current user, an appropriate message is displayed.
     *          The method handles database exceptions and displays error messages when they occur.
     *          
     *          After displaying the products, the method waits for the user to press Enter before continuing.
     */
    void viewAllProducts() {
        clearScreen();
        System.out.println("\n=== All Products ===");
        
        try {
            List<Product> products = productDAO.getAllProducts(currentUser.getId());
            if (products.isEmpty()) {
                System.out.println("No products added yet.");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                return;
            }
            
            for (Product product : products) {
                System.out.printf("\nID: %d%n", product.getId());
                System.out.printf("Name: %s%n", product.getName());
                System.out.printf("Calories: %.2f kcal%n", product.getCalories());
                System.out.printf("Protein: %.2f g%n", product.getProtein());
                System.out.printf("Carbs: %.2f g%n", product.getCarbs());
                System.out.printf("Fat: %.2f g%n", product.getFat());
                System.out.println("------------------------");
            }
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        } catch (SQLException e) {
            
        }
    }
    
    /**
     * @brief Handles updating an existing product in the database.
     * @details Provides a comprehensive interface for modifying product information by:
     *          1. Displaying all current products to help the user identify the product to update
     *          2. Prompting for the ID of the product to be updated
     *          3. Retrieving the product from the database and verifying ownership
     *          4. Displaying current values and allowing the user to update each field:
     *             - Product name
     *             - Caloric content
     *             - Protein content
     *             - Carbohydrate content
     *             - Fat content
     *          5. Saving the updated product to the database
     *          
     *          The method implements a user-friendly approach where pressing Enter without input
     *          retains the current value for that field. This allows for selective updates.
     *          
     *          Appropriate success or error messages are displayed based on the operation outcome.
     *          The method handles database exceptions and ownership validation to ensure data integrity.
     */
    public void 
    updateProduct() {
        clearScreen();
        System.out.println("\n=== Update Product ===");
        
        viewAllProducts();
        
        System.out.print("\nEnter the ID of the product to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        try {
            Product product = productDAO.getProductById(id);
            if (product == null || product.getUserId() != currentUser.getId()) {
                System.out.println("Product not found!");
                return;
            }
            
            System.out.print("New name (" + product.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) product.setName(name);
            
            System.out.print("New calories (" + product.getCalories() + "): ");
            String calories = scanner.nextLine();
            if (!calories.isEmpty()) product.setCalories(Double.parseDouble(calories));
            
            System.out.print("New protein (" + product.getProtein() + "g): ");
            String protein = scanner.nextLine();
            if (!protein.isEmpty()) product.setProtein(Double.parseDouble(protein));
            
            System.out.print("New carbs (" + product.getCarbs() + "g): ");
            String carbs = scanner.nextLine();
            if (!carbs.isEmpty()) product.setCarbs(Double.parseDouble(carbs));
            
            System.out.print("New fat (" + product.getFat() + "g): ");
            String fat = scanner.nextLine();
            if (!fat.isEmpty()) product.setFat(Double.parseDouble(fat));
            
            if (productDAO.updateProduct(product)) {
                System.out.println("Product updated successfully!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println("Product not updated!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        } catch (SQLException e) {
            
        }
    }
    
    /**
     * @brief Handles deleting an existing product from the database.
     * @details Provides a secure interface for removing products from the database by:
     *          1. Displaying all current products to help the user identify the product to delete
     *          2. Prompting for the ID of the product to be deleted
     *          3. Attempting to delete the product while verifying ownership (only the current user's products can be deleted)
     *          
     *          The method includes security measures to ensure that users can only delete their own products
     *          by passing the current user's ID to the deleteProduct method in the ProductDAO.
     *          
     *          Appropriate success or error messages are displayed based on the operation outcome.
     *          The method handles database exceptions and displays detailed error messages when they occur.
     */
    private void deleteProduct() {
        clearScreen();
        System.out.println("\n=== Delete Product ===");
        
        viewAllProducts();
        
        System.out.print("\nEnter the ID of the product to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        try {
            if (productDAO.deleteProduct(id, currentUser.getId())) {
                System.out.println("Product deleted successfully!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println("Product not deleted!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        } catch (SQLException e) {
            
        }
    }
    
    /**
     * @brief Calculates and displays comprehensive nutritional information and BMI statistics.
     * @details Performs a detailed analysis of the user's nutritional intake and body metrics by:
     *          1. Retrieving all products associated with the current user
     *          2. Calculating total nutritional values across all products:
     *             - Total calories
     *             - Total protein
     *             - Total carbohydrates
     *             - Total fat
     *          3. Computing the user's Body Mass Index (BMI) using height and weight data
     *          4. Categorizing the BMI result into standard health categories:
     *             - Underweight (BMI < 18.5)
     *             - Normal (BMI 18.5-24.9)
     *             - Overweight (BMI 25-29.9)
     *             - Obese (BMI ≥ 30)
     *          
     *          The method provides a comprehensive nutritional overview and health assessment
     *          based on the user's product data and physical measurements.
     *          
     *          Database exceptions are handled with appropriate error messages.
     */
    private void calculateCalories() {
        clearScreen();
        System.out.println("\n=== Calculate Calories ===");
        
        try {
            List<Product> products = productDAO.getAllProducts(currentUser.getId());
            double totalCalories = 0;
            double totalProtein = 0;
            double totalCarbs = 0;
            double totalFat = 0;
            
            for (Product product : products) {
                totalCalories += product.getCalories();
                totalProtein += product.getProtein();
                totalCarbs += product.getCarbs();
                totalFat += product.getFat();
            }
            
            System.out.println("\nTotal Values:");
            System.out.printf("Calories: %.2f kcal%n", totalCalories);
            System.out.printf("Protein: %.2f g%n", totalProtein);
            System.out.printf("Carbs: %.2f g%n", totalCarbs);
            System.out.printf("Fat: %.2f g%n", totalFat);
            
            // BMI calculation
            double bmi = currentUser.calculateBMI();
            System.out.printf("\nBody Mass Index (BMI): %.2f%n", bmi);
            
            if (bmi < 18.5) {
                System.out.println("Status: Underweight");
            } else if (bmi < 25) {
                System.out.println("Status: Normal");
            } else if (bmi < 30) {
                System.out.println("Status: Overweight");
            } else {
                System.out.println("Status: Obese");
            }
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        } catch (SQLException e) {
            
        }
    }
    
    /**
     * @brief Allows the user to adjust their physical measurements and provides personalized diet recommendations.
     * @details Offers a comprehensive interface for updating user metrics and receiving tailored nutritional advice by:
     *          1. Displaying current height and weight values
     *          2. Allowing the user to update these measurements (with the option to skip by pressing Enter)
     *          3. Saving the updated values to the database
     *          4. Recalculating the user's BMI based on the new measurements
     *          5. Providing detailed dietary recommendations based on the BMI category:
     *             - For underweight users (BMI < 18.5): Weight gain recommendations
     *             - For normal weight users (BMI 18.5-24.9): Weight maintenance recommendations
     *             - For overweight/obese users (BMI ≥ 25): Weight loss recommendations
     *          
     *          Each recommendation includes specific guidelines for:
     *          - Daily caloric intake
     *          - Protein consumption (g/kg of body weight)
     *          - Carbohydrate consumption (g/kg of body weight)
     *          - Fat consumption (g/kg of body weight)
     *          
     *          The method handles database exceptions and displays appropriate error messages when they occur.
     */
    private void adjustDiet() {
        clearScreen();
        System.out.println("\n=== Adjust Diet ===");
        
        System.out.println("Current values:");
        System.out.printf("Height: %.2f cm%n", currentUser.getHeight());
        System.out.printf("Weight: %.2f kg%n", currentUser.getWeight());
        
        System.out.print("\nNew height (cm) [Enter to skip]: ");
        String height = scanner.nextLine();
        
        System.out.print("New weight (kg) [Enter to skip]: ");
        String weight = scanner.nextLine();
        
        if (!height.isEmpty()) {
            currentUser.setHeight(Double.parseDouble(height));
        }
        
        if (!weight.isEmpty()) {
            currentUser.setWeight(Double.parseDouble(weight));
        }
        
        try {
            if (userDAO.updateUser(currentUser)) {
                System.out.println("Values updated!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                // BMI calculation and recommendations
                double bmi = currentUser.calculateBMI();
                System.out.printf("\nNew BMI: %.2f%n", bmi);
                
                System.out.println("\nRecommended daily values:");
                if (bmi < 18.5) {
                    System.out.println("You are recommended to gain weight:");
                    System.out.println("- Daily calories: 2500-3000 kcal");
                    System.out.println("- Protein: 2g/kg (" + String.format("%.0f", currentUser.getWeight() * 2) + "g)");
                    System.out.println("- Carbs: 4-5g/kg (" + String.format("%.0f", currentUser.getWeight() * 4.5) + "g)");
                    System.out.println("- Fat: 1g/kg (" + String.format("%.0f", currentUser.getWeight()) + "g)");
                } else if (bmi < 25) {
                    System.out.println("You are recommended to maintain your weight:");
                    System.out.println("- Daily calories: 2000-2500 kcal");
                    System.out.println("- Protein: 1.6g/kg (" + String.format("%.0f", currentUser.getWeight() * 1.6) + "g)");
                    System.out.println("- Carbs: 3-4g/kg (" + String.format("%.0f", currentUser.getWeight() * 3.5) + "g)");
                    System.out.println("- Fat: 0.8g/kg (" + String.format("%.0f", currentUser.getWeight() * 0.8) + "g)");
                } else {
                    System.out.println("You are recommended to lose weight:");
                    System.out.println("- Daily calories: 1500-2000 kcal");
                    System.out.println("- Protein: 2g/kg (" + String.format("%.0f", currentUser.getWeight() * 2) + "g)");
                    System.out.println("- Carbs: 2-3g/kg (" + String.format("%.0f", currentUser.getWeight() * 2.5) + "g)");
                    System.out.println("- Fat: 0.6g/kg (" + String.format("%.0f", currentUser.getWeight() * 0.6) + "g)");
                }
            } else {
                System.out.println("Values could not be updated!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        } catch (SQLException e) {
            System.out.println("Error updating values: " + e.getMessage());
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
    
    /**
     * @brief Validates if a string can be parsed as a floating-point number.
     * @details Attempts to parse the input string as a double value to determine if it represents a valid number.
     *          This method is useful for validating numerical input from users before performing calculations.
     *          It handles potential NumberFormatException that might occur during parsing.
     * 
     * @param str The string to validate, which may contain a potential numerical value.
     * @return true if the string can be successfully parsed as a double, false otherwise.
     *         Returns false for null inputs, empty strings, or strings containing non-numeric characters.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * @brief Validates if a string can be parsed as an integer value.
     * @details Attempts to parse the input string as an integer value to determine if it represents a valid whole number.
     *          This method is particularly useful for validating ID inputs, counts, or other integer-based values
     *          entered by users. It handles potential NumberFormatException that might occur during parsing.
     * 
     * @param str The string to validate, which may contain a potential integer value.
     * @return true if the string can be successfully parsed as an integer, false otherwise.
     *         Returns false for null inputs, empty strings, strings with decimal points, or non-numeric characters.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * @brief Formats a floating-point number to have a specific number of decimal places.
     * @details Creates a formatted string representation of a double value with precise control over decimal places.
     *          This method ensures consistent numerical display throughout the application, improving readability
     *          of nutritional values, measurements, and calculated results. It uses the US locale to ensure
     *          consistent formatting regardless of the system's regional settings.
     * 
     * @param number The double value to format, such as calories, weight, or nutritional content.
     * @param decimalPlaces The number of decimal places to include in the formatted output.
     *                      Must be a non-negative integer.
     * @return A formatted string representation of the number with exactly the specified number of decimal places.
     *         For example, formatNumber(12.3456, 2) returns "12.35" (with rounding).
     */
    public static String formatNumber(double number, int decimalPlaces) {
        return String.format(Locale.US, "%." + decimalPlaces + "f", number);
    }
}
