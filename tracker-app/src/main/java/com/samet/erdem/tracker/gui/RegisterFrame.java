package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
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

	public RegisterFrame() {
		setResizable(false);
		setTitle("Create Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 520);
		setLocationRelativeTo(null);

		// Sadece simge olarak logo
		try {
			Image icon = new ImageIcon(getClass().getResource("/logo.png")).getImage();
			setIconImage(icon);
		} catch (Exception e) {
			System.err.println("Simge olarak logo yüklenemedi: " + e.getMessage());
		}

		JPanel panel = new JPanel();
		panel.setBackground(BACKGROUND_COLOR);
		panel.setLayout(null);

		// Başlık
		JLabel titleLabel = new JLabel("Create Account");
		titleLabel.setBounds(10, 11, 367, 30);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		titleLabel.setForeground(PRIMARY_COLOR);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(titleLabel);

		// Kullanıcı adı label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 59, 63, 20);
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsername.setForeground(TEXT_COLOR);
		panel.add(lblUsername);

		// Kullanıcı adı alanı
		usernameField = new JTextField();
		usernameField.setBounds(20, 83, 347, 32);
		usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		usernameField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(189, 189, 189)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		panel.add(usernameField);

		// Şifre label
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 130, 58, 20);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPassword.setForeground(TEXT_COLOR);
		panel.add(lblPassword);

		// Şifre alanı
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 154, 347, 32);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(189, 189, 189)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		panel.add(passwordField);

		// Şifre onay label
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(20, 201, 112, 20);
		lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblConfirmPassword.setForeground(TEXT_COLOR);
		panel.add(lblConfirmPassword);

		// Şifre onay alanı
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(20, 225, 347, 32);
		confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(189, 189, 189)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		panel.add(confirmPasswordField);

		// Height label
		JLabel lblHeight = new JLabel("Height (cm)");
		lblHeight.setBounds(20, 272, 72, 20);
		lblHeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHeight.setForeground(TEXT_COLOR);
		panel.add(lblHeight);

		// Height field
		heightField = new JTextField();
		heightField.setBounds(20, 296, 347, 32);
		heightField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		heightField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(189, 189, 189)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		panel.add(heightField);

		// Weight label
		JLabel lblWeight = new JLabel("Weight (kg)");
		lblWeight.setBounds(20, 343, 72, 20);
		lblWeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblWeight.setForeground(TEXT_COLOR);
		panel.add(lblWeight);

		// Weight field
		weightField = new JTextField();
		weightField.setBounds(20, 367, 347, 32);
		weightField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		weightField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(189, 189, 189)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		panel.add(weightField);

		JButton btnRegister = new JButton("Create Account");
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
		panel.add(btnRegister);

		// Giriş sayfasına dönüş butonu (outline stil)
		JButton btnBackToLogin = new JButton("Back to Login");
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
		panel.add(btnBackToLogin);

		// Feedback
		feedbackLabel = new JLabel("");
		feedbackLabel.setBounds(0, 0, 0, 0);
		feedbackLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		feedbackLabel.setForeground(new Color(244, 67, 54));
		feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(feedbackLabel);

		getContentPane().add(panel);
	}
}
