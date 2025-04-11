/**

@file Tracker.java
@brief This file serves as a demonstration file for the Tracker class.
@details This file contains the implementation of the Tracker class, which provides various mathematical operations.
*/

/**

@package com.samet.erdem.tracker
@brief The com.samet.erdem.tracker package contains all the classes and files related to the Tracker App.
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
@brief This class represents a Tracker that performs mathematical operations.
@details The Tracker class provides methods to perform mathematical operations such as addition, subtraction, multiplication, and division. It also supports logging functionality using the logger object.
@author ugur.coruh
*/
public class Tracker {
    private Scanner scanner;
    private UserDAO userDAO;
    protected ProductDAO productDAO;
    protected User currentUser;
    private RecipeManager recipeManager;
    private NutritionTracker nutritionTracker;
    
    public Tracker() {
        scanner = new Scanner(System.in);
        userDAO = new UserDAO();
        productDAO = new ProductDAO();
        recipeManager = RecipeManager.getInstance();
        nutritionTracker = new NutritionTracker();
        nutritionTracker.addObserver(new ConsoleNutritionObserver());
    }
    
    public void start() {
        showLoginMenuOnce();
    }
    
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
            } catch (SQLException e) {	
                System.out.println("Error during registration: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
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
            System.out.println("Error adding product: " + e.getMessage());
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
    
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
            System.out.println("Error listing products: " + e.getMessage());
        }
    }
    
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
            System.out.println("Error updating product: " + e.getMessage());
        }
    }
    
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
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }
    
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
            System.out.println("Error calculating calories: " + e.getMessage());
        }
    }
    
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
     * @brief Validates if a string can be parsed as a number.
     * @param str The string to validate.
     * @return true if the string can be parsed as a number, false otherwise.
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
     * @brief Validates if a string can be parsed as an integer.
     * @param str The string to validate.
     * @return true if the string can be parsed as an integer, false otherwise.
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
     * @brief Formats a number to have a specific number of decimal places.
     * @param number The number to format.
     * @param decimalPlaces The number of decimal places.
     * @return The formatted string.
     */
    public static String formatNumber(double number, int decimalPlaces) {
        return String.format(Locale.US, "%." + decimalPlaces + "f", number);
    }
}
