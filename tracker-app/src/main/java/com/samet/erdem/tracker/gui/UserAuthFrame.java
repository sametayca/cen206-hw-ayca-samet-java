package com.samet.erdem.tracker.gui;

import javax.swing.*;
import java.awt.*;
import com.samet.erdem.tracker.dao.UserDAO;
import com.samet.erdem.tracker.model.User;

public class UserAuthFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel feedbackLabel;
    private static final Color PRIMARY_COLOR = new Color(33, 150, 243);
    private static final Color BACKGROUND_COLOR = new Color(245, 249, 255);
    private static final Color TEXT_COLOR = new Color(33, 33, 33);
    private JPanel contentPane;
    private JLabel titleLabel;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel logoLabel;

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
                UserAuthFrame frame = new UserAuthFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserAuthFrame() {
        // Frame ayarları
        setResizable(false);
        setTitle("Recipe & Nutrition Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 417, 423);
        setLocationRelativeTo(null);
        
        // İkon ayarı
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
        titleLabel = new JLabel("Welcome to the Nutrition Tracker");
        titleLabel.setBounds(23, 137, 367, 30);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);
        
        // Kullanıcı adı label
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(23, 198, 63, 20);
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setForeground(TEXT_COLOR);
        contentPane.add(lblUsername);
        
        // Kullanıcı adı alanı
        usernameField = new JTextField();
        usernameField.setBounds(96, 192, 233, 32);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(usernameField);
        
        // Şifre label
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(28, 255, 58, 20);
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setForeground(TEXT_COLOR);
        contentPane.add(lblPassword);
        
        // Şifre alanı
        passwordField = new JPasswordField();
        passwordField.setBounds(96, 249, 233, 32);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 189, 189)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        contentPane.add(passwordField);
        
        // Login butonu (outline stil)
        btnLogin = new JButton("Login");
        btnLogin.setBounds(86, 310, 99, 41);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setForeground(PRIMARY_COLOR);
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(8, 0, 8, 0)
        ));
        btnLogin.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                feedbackLabel.setText("Please fill in all fields.");
                return;
            }
            try {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.login(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(UserAuthFrame.this, "Login successful!");
                    dispose();
                    new MainMenuFrame().setVisible(true);
                } else {
                    feedbackLabel.setText("Invalid username or password.");
                    passwordField.setText("");
                }
            } catch (Exception ex) {
                feedbackLabel.setText("Login failed: " + ex.getMessage());
            }
        });
        contentPane.add(btnLogin);
        
        // Kayıt butonu (outline stil)
        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(195, 310, 134, 41);
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
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
            dispose();
        });
        contentPane.add(btnRegister);
        
        // Feedback
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(96, 360, 233, 20);
        feedbackLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        feedbackLabel.setForeground(new Color(244, 67, 54));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(feedbackLabel);
        
        // Logo
        logoLabel = new JLabel("");
        try {
            logoLabel.setIcon(new ImageIcon(getClass().getResource("/logo2.png")));
        } catch (Exception e) {
            System.err.println("Logo yüklenemedi: " + e.getMessage());
            // Alternatif kaynak yolu deneyin
            try {
                logoLabel.setIcon(new ImageIcon(UserAuthFrame.class.getResource("/resources/logo2.png")));
            } catch (Exception ex) {
                System.err.println("Alternatif yoldan da logo yüklenemedi: " + ex.getMessage());
            }
        }
        logoLabel.setBounds(154, 34, 99, 91);
        contentPane.add(logoLabel);
    }
}