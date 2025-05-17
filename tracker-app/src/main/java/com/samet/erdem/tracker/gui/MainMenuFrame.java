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

public class MainMenuFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel buttonPanel;
    private User currentUser;

    // Field olarak tanımlanan butonlar
    private JButton btnAddProduct;
    private JButton btnViewProducts;
    private JButton btnUpdateProduct;
    private JButton btnDeleteProduct;
    private JButton btnCalculateCalories;
    private JButton btnAdjustDiet;
    private JButton btnLogout;

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