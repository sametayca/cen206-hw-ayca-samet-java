package com.samet.erdem.tracker.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.UserDAO;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textField_2; // Confirm Password
	private JTextField textField_3; // Height
	private JTextField textField;   // Weight

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setTitle("Recipe & Nutrition Tracker");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CREATE ACCOUNT");
		lblNewLabel.setForeground(new Color(33, 150, 243));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblNewLabel.setBounds(117, 27, 190, 23);
		contentPane.add(lblNewLabel);

		textUsername = new JTextField();
		textUsername.setBounds(20, 91, 323, 32);
		contentPane.add(textUsername);
		textUsername.setColumns(10);

		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(20, 159, 323, 32);
		contentPane.add(textPassword);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(20, 222, 323, 32);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(20, 292, 323, 32);
		contentPane.add(textField_3);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(20, 73, 73, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblNewLabel_1_1.setBounds(20, 134, 73, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblNewLabel_1_1_1.setBounds(20, 202, 94, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Height");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblNewLabel_1_1_2.setBounds(20, 265, 73, 14);
		contentPane.add(lblNewLabel_1_1_2);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(20, 359, 323, 32);
		contentPane.add(textField);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Weight");
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblNewLabel_1_1_1_1.setBounds(20, 334, 94, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = textPassword.getText();
				String confirmPassword = textField_2.getText();
				double height;
				double weight;

				try {
					height = Double.parseDouble(textField_3.getText());
					weight = Double.parseDouble(textField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "Height and Weight must be numbers!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				UserDAO userDAO = new UserDAO();

				try {
					if (userDAO.usernameExists(username)) {
						JOptionPane.showMessageDialog(RegisterFrame.this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					User user = new User(); // make sure User class has a no-arg constructor
					user.setUsername(username);
					user.setPassword(password);
					user.setHeight(height);
					user.setWeight(weight);

					userDAO.register(user);

					JOptionPane.showMessageDialog(RegisterFrame.this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new UserAuthFrame().setVisible(true);

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(RegisterFrame.this, "An error occurred while creating the account.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCreateAccount.setForeground(new Color(33, 150, 243));
		btnCreateAccount.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnCreateAccount.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnCreateAccount.setBackground(Color.WHITE);
		btnCreateAccount.setBounds(30, 408, 145, 32);
		contentPane.add(btnCreateAccount);

		JButton btnBacktoLogin = new JButton("Back to Login");
		btnBacktoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAuthFrame loginFrame = new UserAuthFrame();
				loginFrame.setVisible(true);
				loginFrame.setLocationRelativeTo(null);
				RegisterFrame.this.dispose();
			}
		});
		btnBacktoLogin.setForeground(new Color(33, 150, 243));
		btnBacktoLogin.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBacktoLogin.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBacktoLogin.setBackground(Color.WHITE);
		btnBacktoLogin.setBounds(199, 408, 131, 32);
		contentPane.add(btnBacktoLogin);
	}
}
