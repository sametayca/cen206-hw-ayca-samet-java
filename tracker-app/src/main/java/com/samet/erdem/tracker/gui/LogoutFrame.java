/**
 * @package com.samet.erdem.tracker.gui
 * @brief Contains graphical user interface components for the Recipe & Nutrition Tracker application.
 * @details This package includes all Swing-based frames and UI elements that allow users to interact with the system.
 */
package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @brief Frame for handling user logout confirmation.
 * 
 * This class provides a graphical user interface that:
 * - Displays a logout confirmation dialog
 * - Allows users to confirm or cancel the logout action
 * - Handles the transition between main application and login screen
 * 
 * The frame maintains the application's consistent design theme and
 * provides a clean, user-friendly interface for the logout process.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class LogoutFrame extends JFrame {

	/** 
 * @brief Serialization ID for version control. 
 * @details Ensures compatibility during the deserialization process.
 */
	private static final long serialVersionUID = 1L;
	
	/** Main content panel of the frame */
	private JPanel contentPane;
	
	/** Button to confirm logout action */
	private JButton btnYes;
	
	/** Button to cancel logout action */
	private JButton btnNo;

	/**
	 * @brief Constructs a new LogoutFrame with a reference to the parent frame.
	 * 
	 * Initializes the frame and sets up the UI components for logout confirmation.
	 * The frame includes:
	 * - Confirmation message
	 * - Yes/No buttons for user choice
	 * - Action listeners for handling user decisions
	 * 
	 * When confirmed, closes the parent frame and opens the login screen.
	 * When cancelled, simply closes the confirmation dialog.
	 * 
	 * @param parentFrame The main application frame that will be closed on logout
	 */
	public LogoutFrame(JFrame parentFrame) {
		setTitle("Logout Confirmation");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 180);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConfirm = new JLabel("Are you sure you want to logout?");
		lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblConfirm.setForeground(new Color(33, 150, 243));
		lblConfirm.setBounds(30, 20, 300, 30);
		contentPane.add(lblConfirm);

		btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnYes.setForeground(new Color(33, 150, 243));
		btnYes.setBackground(Color.WHITE);
		btnYes.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnYes.setBounds(40, 80, 100, 30);
		contentPane.add(btnYes);

		btnNo = new JButton("No");
		btnNo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNo.setForeground(new Color(33, 150, 243));
		btnNo.setBackground(Color.WHITE);
		btnNo.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnNo.setBounds(180, 80, 100, 30);
		contentPane.add(btnNo);

		// Evet'e basılırsa ana pencereyi kapat ve giriş ekranını aç
		btnYes.addActionListener(e -> {
			if (parentFrame != null) {
				parentFrame.dispose();
			}
			UserAuthFrame loginFrame = new UserAuthFrame();
			loginFrame.setVisible(true);
			loginFrame.setLocationRelativeTo(null);
			dispose();
		});

		// Hayır'a basılırsa sadece bu pencereyi kapat
		btnNo.addActionListener(e -> dispose());
	}
}
