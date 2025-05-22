package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.ProductDAO;

/**
 * @brief Frame for calculating and displaying total nutrition values and BMI.
 * 
 * This class provides a graphical user interface that calculates and displays:
 * - Total calories from all products/recipes
 * - Total protein content (in grams)
 * - Total carbohydrate content (in grams)
 * - Total fat content (in grams)
 * - User's BMI (Body Mass Index)
 * - BMI status category (Underweight, Normal, Overweight, Obese)
 * 
 * The frame maintains the application's consistent design theme and provides
 * real-time calculations based on the user's product database.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class CalculateCaloriesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Main content panel of the frame */
	private JPanel contentPane;
	
	/** Label displaying total calories */
	private JLabel lblTotalCalories;
	
	/** Label displaying total protein content */
	private JLabel lblTotalProtein;
	
	/** Label displaying total carbohydrate content */
	private JLabel lblTotalCarbs;
	
	/** Label displaying total fat content */
	private JLabel lblTotalFat;
	
	/** Label displaying calculated BMI value */
	private JLabel lblBMI;
	
	/** Label displaying BMI status category */
	private JLabel lblBMIStatus;
	
	/** Button to return to previous screen */
	private JButton btnBack;
	
	/** Current user whose nutrition values are being calculated */
	private User user;

	/**
	 * @brief Constructs a new CalculateCaloriesFrame for the specified user.
	 * 
	 * Initializes the frame and sets up the UI components for displaying
	 * nutrition calculations and BMI information. The frame includes:
	 * - Labels for all nutrition values
	 * - BMI calculation and status display
	 * - Back button for navigation
	 * 
	 * @param user The User object whose nutrition values will be calculated
	 */
	public CalculateCaloriesFrame(User user) {
		this.user = user;

		setTitle("Calculate Calories");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Total Nutrition & BMI");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(33, 150, 243));
		lblTitle.setBounds(90, 10, 300, 30);
		contentPane.add(lblTitle);

		lblTotalCalories = new JLabel("Calories: ");
		lblTotalCalories.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTotalCalories.setBounds(40, 60, 300, 25);
		contentPane.add(lblTotalCalories);

		lblTotalProtein = new JLabel("Protein: ");
		lblTotalProtein.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTotalProtein.setBounds(40, 95, 300, 25);
		contentPane.add(lblTotalProtein);

		lblTotalCarbs = new JLabel("Carbs: ");
		lblTotalCarbs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTotalCarbs.setBounds(40, 130, 300, 25);
		contentPane.add(lblTotalCarbs);

		lblTotalFat = new JLabel("Fat: ");
		lblTotalFat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTotalFat.setBounds(40, 165, 300, 25);
		contentPane.add(lblTotalFat);

		lblBMI = new JLabel("BMI: ");
		lblBMI.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblBMI.setBounds(40, 210, 300, 25);
		contentPane.add(lblBMI);

		lblBMIStatus = new JLabel("Status: ");
		lblBMIStatus.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblBMIStatus.setBounds(40, 245, 300, 25);
		contentPane.add(lblBMIStatus);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBack.setForeground(new Color(33, 150, 243));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBack.setBounds(150, 280, 100, 30);
		contentPane.add(btnBack);

		btnBack.addActionListener(e -> dispose());

		// Hesaplamaları yap ve label'lara yaz
		calculateAndDisplay();
	}

	/**
	 * @brief Calculates and displays nutrition values and BMI.
	 * 
	 * Performs the following calculations:
	 * - Sums up all nutrition values from user's products
	 * - Calculates BMI using user's height and weight
	 * - Determines BMI status category
	 * 
	 * Updates all display labels with calculated values and handles
	 * any potential calculation errors.
	 */
	private void calculateAndDisplay() {
		try {
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.getAllProducts(user.getId());
			double totalCalories = 0;
			double totalProtein = 0;
			double totalCarbs = 0;
			double totalFat = 0;

			for (Product p : products) {
				totalCalories += p.getCalories();
				totalProtein += p.getProtein();
				totalCarbs += p.getCarbs();
				totalFat += p.getFat();
			}

			lblTotalCalories.setText("Calories: " + String.format("%.2f kcal", totalCalories));
			lblTotalProtein.setText("Protein: " + String.format("%.2f g", totalProtein));
			lblTotalCarbs.setText("Carbs: " + String.format("%.2f g", totalCarbs));
			lblTotalFat.setText("Fat: " + String.format("%.2f g", totalFat));

			double bmi = user.calculateBMI();
			lblBMI.setText("BMI: " + String.format("%.2f", bmi));

			String status;
			if (bmi < 18.5) status = "Underweight";
			else if (bmi < 25) status = "Normal";
			else if (bmi < 30) status = "Overweight";
			else status = "Obese";
			lblBMIStatus.setText("Status: " + status);

		} catch (Exception e) {
			lblTotalCalories.setText("Calories: Error!");
			lblTotalProtein.setText("Protein: Error!");
			lblTotalCarbs.setText("Carbs: Error!");
			lblTotalFat.setText("Fat: Error!");
			lblBMI.setText("BMI: Error!");
			lblBMIStatus.setText("Status: Error!");
		}
	}
}
