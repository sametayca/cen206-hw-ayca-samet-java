package com.samet.erdem.tracker.gui;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.UserDAO;
import com.samet.erdem.tracker.AppConfig;

/**
 * @brief Frame for user authentication in the Recipe & Nutrition Tracker application.
 * 
 * This class provides a graphical user interface for user login and registration.
 * It includes functionality for:
 * - Username and password input
 * - User authentication against the database
 * - Navigation to registration screen
 * - Error handling and user feedback
 * - Transition to main menu upon successful login
 * 
 * The frame maintains the application's consistent design theme and
 * provides a clean, user-friendly interface for the authentication process.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class UserAuthFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Main content panel of the frame */
	private JPanel contentPane;
	
	/** Text field for username input */
	private JTextField textUsername;
	
	/** Text field for password input */
	private JTextField textPassword;
	
	/** Label displaying the welcome message */
	private JLabel lblNewLabel_1;
	
	/** Button to navigate to registration screen */
	private JButton btnRegisterButton;

	/**
	 * @brief Main method to launch the authentication frame.
	 * 
	 * Creates and displays the authentication frame for testing purposes.
	 * This method is primarily used for testing the GUI independently.
	 * 
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAuthFrame frame = new UserAuthFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @brief Constructs a new UserAuthFrame.
	 * 
	 * Initializes the authentication window with all necessary UI components including:
	 * - Application logo
	 * - Welcome message
	 * - Username and password input fields
	 * - Login and Register buttons
	 * - Action listeners for authentication and navigation
	 * 
	 * The frame includes functionality for:
	 * - User authentication against the database
	 * - Error handling and user feedback
	 * - Navigation to registration screen
	 * - Transition to main menu upon successful login
	 * 
	 * The frame is styled with a modern, clean design using a blue color scheme
	 * and includes proper spacing and layout management.
	 */
	public UserAuthFrame() {
		setTitle("Recipe & Nutrition Tracker");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/logo2.png")));
		lblNewLabel.setBounds(172, 36, 99, 84);
		contentPane.add(lblNewLabel);
		
		textUsername = new JTextField();
		textUsername.setBounds(133, 201, 226, 26);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(133, 252, 226, 26);
		contentPane.add(textPassword);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		lblUsername.setBounds(49, 203, 74, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		lblPassword.setBounds(49, 254, 74, 20);
		contentPane.add(lblPassword);
		
		lblNewLabel_1 = new JLabel("Welcome to the Nutrition Tracker");
		lblNewLabel_1.setForeground(new Color(33, 150, 243));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblNewLabel_1.setBounds(62, 145, 337, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton btnLoginButton = new JButton("Login");
		btnLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();  // field adını kendine göre düzenle
				String password = textPassword.getText();

				UserDAO userDAO = new UserDAO();

				try {
				    User user = userDAO.login(username, password);
				    
				    if (user != null) {
				        if (!AppConfig.isTestMode) {
				            JOptionPane.showMessageDialog(UserAuthFrame.this, "Login successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
				        }
				        MainMenuFrame mainMenu = new MainMenuFrame(user);
				        mainMenu.setVisible(true);
				        mainMenu.setLocationRelativeTo(null);
				        UserAuthFrame.this.dispose(); 
				    } else {
				        if (!AppConfig.isTestMode) {
				            JOptionPane.showMessageDialog(UserAuthFrame.this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				} catch (Exception ex) {
				    if (!AppConfig.isTestMode) {
				        JOptionPane.showMessageDialog(UserAuthFrame.this, "An error occurred during login.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				    ex.printStackTrace();
				}

			}
		});
		btnLoginButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnLoginButton.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnLoginButton.setBackground(new Color(255, 255, 255));
		btnLoginButton.setForeground(new Color(33, 150, 243));
		btnLoginButton.setBounds(133, 309, 99, 26);
		contentPane.add(btnLoginButton);
		
		btnRegisterButton = new JButton("Register");
		btnRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 RegisterFrame registerFrame = new RegisterFrame();
			        registerFrame.setVisible(true);
			        registerFrame.setLocationRelativeTo(null);
			        UserAuthFrame.this.dispose();
			}
		});
		btnRegisterButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnRegisterButton.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnRegisterButton.setBackground(new Color(255, 255, 255));
		btnRegisterButton.setForeground(new Color(33, 150, 243));
		btnRegisterButton.setBounds(260, 309, 99, 26);
		contentPane.add(btnRegisterButton);
	}
}
