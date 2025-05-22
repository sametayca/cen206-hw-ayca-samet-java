package com.samet.erdem.tracker.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import com.samet.erdem.tracker.model.User;

/**
 * @brief Main menu frame for the Recipe & Nutrition Tracker application.
 * 
 * This class represents the main menu window of the application, providing access to all major features
 * through a set of buttons. It includes functionality for managing products/recipes, calculating calories,
 * adjusting diet plans, and user authentication.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class MainMenuFrame extends JFrame {

    /**
     * @brief Serialization version UID for this class.
     * @details Ensures class compatibility during the deserialization process.
     */
    private static final long serialVersionUID = 1L;
    
    /** Main content panel of the frame */
    private JPanel contentPane;
    
    /** Panel containing all navigation buttons */
    private JPanel buttonPanel;
    
    /** Current user logged into the application */
    private User currentUser;

    /** Button to add new products or recipes */
    private JButton btnAddProduct;
    
    /** Button to view all products and recipes */
    private JButton btnViewProducts;
    
    /** Button to update existing products or recipes */
    private JButton btnUpdateProduct;
    
    /** Button to delete products or recipes */
    private JButton btnDeleteProduct;
    
    /** Button to calculate total nutrition values */
    private JButton btnCalculateCalories;
    
    /** Button to adjust diet-related measurements */
    private JButton btnAdjustDiet;
    
    /** Button to log out of the application */
    private JButton btnLogout;

    /**
     * @brief Main method to launch the application.
     * 
     * Creates a dummy user and initializes the main menu frame for testing purposes.
     * This method is primarily used for testing the GUI independently.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                User dummyUser = new User();
                dummyUser.setUsername("Guest");
                MainMenuFrame frame = new MainMenuFrame(dummyUser);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @brief Constructs a new MainMenuFrame for the specified user.
     * 
     * Initializes the main menu window with all necessary UI components including:
     * - Welcome header with user's name
     * - Application logo
     * - Navigation buttons for all major features
     * - Action listeners for all buttons
     * 
     * The frame is styled with a modern, clean design using a blue color scheme
     * and includes proper spacing and layout management.
     * 
     * @param user The User object representing the currently logged-in user
     */
    public MainMenuFrame(User user) {
        this.currentUser = user;

        setTitle("Recipe & Nutrition Tracker - Welcome " + user.getUsername());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 249, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(245, 249, 255));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel lblWelcome = new JLabel("Welcome, " + user.getUsername() + "!");
        lblWelcome.setForeground(new Color(33, 150, 243));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerPanel.add(lblWelcome);

        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/logo2.png")));
        headerPanel.add(lblLogo);

        contentPane.add(headerPanel, BorderLayout.NORTH);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 249, 255));
        buttonPanel.setBorder(new EmptyBorder(30, 200, 30, 200));
        buttonPanel.setLayout(new GridLayout(7, 1, 20, 20));

        // Butonları field olarak oluştur ve ekle
        btnAddProduct = new JButton("Add Product/Recipe");
        btnAddProduct.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAddProduct.setForeground(new Color(33, 150, 243));
        btnAddProduct.setBackground(Color.WHITE);
        btnAddProduct.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnAddProduct.setFocusPainted(false);
        buttonPanel.add(btnAddProduct);

        btnViewProducts = new JButton("View All Products");
        btnViewProducts.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnViewProducts.setForeground(new Color(33, 150, 243));
        btnViewProducts.setBackground(Color.WHITE);
        btnViewProducts.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnViewProducts.setFocusPainted(false);
        buttonPanel.add(btnViewProducts);

        btnUpdateProduct = new JButton("Update Product");
        btnUpdateProduct.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnUpdateProduct.setForeground(new Color(33, 150, 243));
        btnUpdateProduct.setBackground(Color.WHITE);
        btnUpdateProduct.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnUpdateProduct.setFocusPainted(false);
        buttonPanel.add(btnUpdateProduct);

        btnDeleteProduct = new JButton("Delete Product");
        btnDeleteProduct.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDeleteProduct.setForeground(new Color(33, 150, 243));
        btnDeleteProduct.setBackground(Color.WHITE);
        btnDeleteProduct.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnDeleteProduct.setFocusPainted(false);
        buttonPanel.add(btnDeleteProduct);

        btnCalculateCalories = new JButton("Calculate Calories");
        btnCalculateCalories.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCalculateCalories.setForeground(new Color(33, 150, 243));
        btnCalculateCalories.setBackground(Color.WHITE);
        btnCalculateCalories.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnCalculateCalories.setFocusPainted(false);
        buttonPanel.add(btnCalculateCalories);

        btnAdjustDiet = new JButton("Adjust Diet");
        btnAdjustDiet.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAdjustDiet.setForeground(new Color(33, 150, 243));
        btnAdjustDiet.setBackground(Color.WHITE);
        btnAdjustDiet.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnAdjustDiet.setFocusPainted(false);
        buttonPanel.add(btnAdjustDiet);

        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogout.setForeground(new Color(33, 150, 243));
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setBorder(new LineBorder(new Color(33, 150, 243), 2, true));
        btnLogout.setFocusPainted(false);
        buttonPanel.add(btnLogout);

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        // ActionListener örneği (işlev eklemek için)
        btnLogout.addActionListener(e -> {
            LogoutFrame logoutFrame = new LogoutFrame((JFrame) this);
            logoutFrame.setVisible(true);
            logoutFrame.setLocationRelativeTo(null);
        });

        btnAddProduct.addActionListener(e -> {
            AddProductFrame addProductFrame = new AddProductFrame(currentUser);
            addProductFrame.setVisible(true);
            addProductFrame.setLocationRelativeTo(null);
        });

        btnViewProducts.addActionListener(e -> {
            AllProductsFrame allProductsFrame = new AllProductsFrame(currentUser);
            allProductsFrame.setVisible(true);
            allProductsFrame.setLocationRelativeTo(null);
        });

        btnUpdateProduct.addActionListener(e -> {
            UpdateProductFrame updateProductFrame = new UpdateProductFrame(currentUser);
            updateProductFrame.setVisible(true);
            updateProductFrame.setLocationRelativeTo(null);
        });

        btnDeleteProduct.addActionListener(e -> {
            DeleteProductFrame deleteProductFrame = new DeleteProductFrame(currentUser);
            deleteProductFrame.setVisible(true);
            deleteProductFrame.setLocationRelativeTo(null);
        });

        btnCalculateCalories.addActionListener(e -> {
            CalculateCaloriesFrame calculateCaloriesFrame = new CalculateCaloriesFrame(currentUser);
            calculateCaloriesFrame.setVisible(true);
            calculateCaloriesFrame.setLocationRelativeTo(null);
        });

        btnAdjustDiet.addActionListener(e -> {
            AdjustDietFrame adjustDietFrame = new AdjustDietFrame(currentUser);
            adjustDietFrame.setVisible(true);
            adjustDietFrame.setLocationRelativeTo(null);
        });
    }
}