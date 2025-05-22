package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.UserDAO;
import com.samet.erdem.tracker.AppConfig;

/**
 * @brief Frame for adjusting user's diet-related measurements.
 * 
 * This class provides a graphical user interface for users to update their
 * height and weight measurements. It displays current measurements and allows
 * users to input new values. The frame includes:
 * - Current height and weight display
 * - Input fields for new measurements
 * - Update and back buttons
 * - Input validation and error handling
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class AdjustDietFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Main content panel of the frame */
	private JPanel contentPane;
	
	/** Label displaying the user's current height */
	private JLabel lblCurrentHeight;
	
	/** Label displaying the user's current weight */
	private JLabel lblCurrentWeight;
	
	/** Text field for entering new height value */
	private JTextField txtNewHeight;
	
	/** Text field for entering new weight value */
	private JTextField txtNewWeight;
	
	/** Button to update the measurements */
	private JButton btnUpdate;
	
	/** Button to return to previous screen */
	private JButton btnBack;
	
	/** Current user whose measurements are being adjusted */
	private User user;

	/**
	 * @brief Constructs a new AdjustDietFrame for the specified user.
	 * 
	 * Initializes the frame with current user measurements and input fields.
	 * Sets up the UI components with a modern design matching the application theme.
	 * 
	 * @param user The User object whose measurements will be adjusted
	 */
	public AdjustDietFrame(User user) {
		this.user = user;

		setTitle("Adjust Diet");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Adjust Diet (Height & Weight)");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(33, 150, 243));
		lblTitle.setBounds(50, 10, 350, 30);
		contentPane.add(lblTitle);

		lblCurrentHeight = new JLabel("Current Height: " + user.getHeight() + " cm");
		lblCurrentHeight.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCurrentHeight.setBounds(40, 60, 300, 25);
		contentPane.add(lblCurrentHeight);

		lblCurrentWeight = new JLabel("Current Weight: " + user.getWeight() + " kg");
		lblCurrentWeight.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCurrentWeight.setBounds(40, 95, 300, 25);
		contentPane.add(lblCurrentWeight);

		JLabel lblNewHeight = new JLabel("New Height (cm):");
		lblNewHeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewHeight.setBounds(40, 140, 120, 25);
		contentPane.add(lblNewHeight);

		txtNewHeight = new JTextField();
		txtNewHeight.setBounds(180, 140, 150, 25);
		contentPane.add(txtNewHeight);

		JLabel lblNewWeight = new JLabel("New Weight (kg):");
		lblNewWeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewWeight.setBounds(40, 180, 120, 25);
		contentPane.add(lblNewWeight);

		txtNewWeight = new JTextField();
		txtNewWeight.setBounds(180, 180, 150, 25);
		contentPane.add(txtNewWeight);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnUpdate.setForeground(new Color(33, 150, 243));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnUpdate.setBounds(60, 230, 120, 30);
		contentPane.add(btnUpdate);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBack.setForeground(new Color(33, 150, 243));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBack.setBounds(220, 230, 120, 30);
		contentPane.add(btnBack);

		// Update butonu işlevi
		btnUpdate.addActionListener(e -> updateDiet());

		// Back butonu işlevi
		btnBack.addActionListener(e -> dispose());
	}

	/**
	 * @brief Updates the user's height and weight measurements.
	 * 
	 * Validates and processes the new measurements entered by the user.
	 * Updates the database and refreshes the display if successful.
	 * Shows appropriate success/error messages to the user.
	 */
	private void updateDiet() {
		try {
			String newHeightStr = txtNewHeight.getText().trim();
			String newWeightStr = txtNewWeight.getText().trim();

			if (!newHeightStr.isEmpty()) {
				user.setHeight(Double.parseDouble(newHeightStr));
			}
			if (!newWeightStr.isEmpty()) {
				user.setWeight(Double.parseDouble(newWeightStr));
			}

			UserDAO userDAO = new UserDAO();
			boolean success = userDAO.updateUser(user);
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				lblCurrentHeight.setText("Current Height: " + user.getHeight() + " cm");
				lblCurrentWeight.setText("Current Weight: " + user.getWeight() + " kg");
			}
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Please enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	
}
