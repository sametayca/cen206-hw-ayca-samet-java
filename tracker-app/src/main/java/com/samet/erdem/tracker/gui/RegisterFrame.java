package com.samet.erdem.tracker.gui;

import javax.swing.*;
import java.awt.*;
import com.samet.erdem.tracker.dao.UserDAO;
import com.samet.erdem.tracker.model.User;

public class RegisterFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField heightField;
    private JTextField weightField;
    private JLabel feedbackLabel;
    private static final Color PRIMARY_COLOR = new Color(33, 150, 243);
    private static final Color BACKGROUND_COLOR = new Color(245, 249, 255);
    private static final Color TEXT_COLOR = new Color(33, 33, 33);
    private static final int CORNER_RADIUS = 15;
    private JLabel titleLabel;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblConfirmPassword;
    private JLabel lblHeight;
    private JLabel lblWeight;
    private JButton btnRegister;
    private JButton btnBackToLogin;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        EventQueue.invokeLater(() -> {
            try {
                RegisterFrame frame = new RegisterFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public RegisterFrame() {
        setResizable(false);
        setTitle("Create Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 520);
        setLocationRelativeTo(null);

        // Uygulama ikonu ayarı
        try {
            Image icon = new ImageIcon(getClass().getResource("/logo.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.err.println("Simge olarak logo yüklenemedi: " + e.getMessage());
        }

        // ContentPane oluşturma
        contentPane = new JPanel();
        contentPane.setBackground(BACKGROUND_COLOR);
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Başlık
        titleLabel = new JLabel("Create Account");
        titleLabel.setBounds(10, 11, 367, 30);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);

        // Kullanıcı adı label
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(20, 59, 63, 20);
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setForeground(TEXT_COLOR);
        contentPane.add(lblUsername);

        // Kullanıcı adı alanı
        usernameField = new JTextField();
        usernameField.setBounds(20, 83, 347, 32);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(usernameField);

        // Şifre label
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(20, 130, 58, 20);
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setForeground(TEXT_COLOR);
        contentPane.add(lblPassword);

        // Şifre alanı
        passwordField = new JPasswordField();
        passwordField.setBounds(20, 154, 347, 32);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(passwordField);

        // Şifre onay label
        lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setBounds(20, 201, 112, 20);
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblConfirmPassword.setForeground(TEXT_COLOR);
        contentPane.add(lblConfirmPassword);

        // Şifre onay alanı
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(20, 225, 347, 32);
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(confirmPasswordField);

        // Height label
        lblHeight = new JLabel("Height (cm)");
        lblHeight.setBounds(20, 272, 72, 20);
        lblHeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHeight.setForeground(TEXT_COLOR);
        contentPane.add(lblHeight);

        // Height field
        heightField = new JTextField();
        heightField.setBounds(20, 296, 347, 32);
        heightField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        heightField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(heightField);

        // Weight label
        lblWeight = new JLabel("Weight (kg)");
        lblWeight.setBounds(20, 343, 72, 20);
        lblWeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblWeight.setForeground(TEXT_COLOR);
        contentPane.add(lblWeight);

        // Weight field
        weightField = new JTextField();
        weightField.setBounds(20, 367, 347, 32);
        weightField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        weightField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(weightField);

        // Kayıt butonu (outline stil)
        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(20, 414, 169, 41);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegister.setForeground(PRIMARY_COLOR);
        btnRegister.setBackground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(8, 0, 8, 0)
        ));
        btnRegister.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String heightStr = heightField.getText().trim();
            String weightStr = weightField.getText().trim();
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
                feedbackLabel.setText("Please fill in all fields.");
                return;
            }
            if (!password.equals(confirmPassword)) {
                feedbackLabel.setText("Passwords do not match.");
                confirmPasswordField.setText("");
                return;
            }
            double height, weight;
            try {
                height = Double.parseDouble(heightStr);
                weight = Double.parseDouble(weightStr);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Height and weight must be numbers.");
                return;
            }
            if (height <= 0 || weight <= 0) {
                feedbackLabel.setText("Height and weight must be positive.");
                return;
            }
            try {
                UserDAO userDAO = new UserDAO();
                if (userDAO.usernameExists(username)) {
                    feedbackLabel.setText("Username already exists.");
                    return;
                }
                User user = new User(username, password, height, weight);
                userDAO.register(user);
                JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful!");
                dispose();
                new UserAuthFrame().setVisible(true);
            } catch (Exception ex) {
                feedbackLabel.setText("Registration failed: " + ex.getMessage());
            }
        });
        contentPane.add(btnRegister);

        // Giriş sayfasına dönüş butonu (outline stil)
        btnBackToLogin = new JButton("Back to Login");
        btnBackToLogin.setBounds(209, 414, 158, 41);
        btnBackToLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBackToLogin.setForeground(PRIMARY_COLOR);
        btnBackToLogin.setBackground(Color.WHITE);
        btnBackToLogin.setFocusPainted(false);
        btnBackToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBackToLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(8, 0, 8, 0)
        ));
        btnBackToLogin.addActionListener(e -> {
            dispose();
            new UserAuthFrame().setVisible(true);
        });
        contentPane.add(btnBackToLogin);

        // Feedback label - görünür konuma taşındı
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(20, 465, 347, 20);
        feedbackLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        feedbackLabel.setForeground(new Color(244, 67, 54));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(feedbackLabel);
    }
}