package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.ProductDAO;
import com.samet.erdem.tracker.AppConfig;

/**
 * @brief Frame for adding new products or recipes to the nutrition tracker.
 * 
 * This class provides a graphical user interface for users to add new products or recipes
 * to their nutrition tracking system. It includes input fields for:
 * - Product/Recipe name
 * - Calorie content
 * - Protein content (in grams)
 * - Carbohydrate content (in grams)
 * - Fat content (in grams)
 * 
 * The frame implements data validation and error handling for user inputs,
 * and provides feedback through dialog messages. It uses a clean, modern design
 * with a consistent color scheme matching the main application.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class AddProductFrame extends JFrame {

	/**
 * @brief Serialization version UID for this class.
 * @details Ensures class compatibility during the deserialization process.
 */
	private static final long serialVersionUID = 1L;
	
	/** Main content panel of the frame */
	private JPanel contentPane;
	
	/** Text field for entering the product/recipe name */
	private JTextField txtName;
	
	/** Text field for entering the calorie content */
	private JTextField txtCalories;
	
	/** Text field for entering the protein content in grams */
	private JTextField txtProtein;
	
	/** Text field for entering the carbohydrate content in grams */
	private JTextField txtCarbs;
	
	/** Text field for entering the fat content in grams */
	private JTextField txtFat;
	
	/** Current user who is adding the product/recipe */
	private User user;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AddProductFrame(User user) {
		this.user = user;
		setTitle("Add Product / Recipe");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add Product / Recipe");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(33, 150, 243));
		lblTitle.setBounds(80, 10, 250, 30);
		contentPane.add(lblTitle);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblName.setBounds(30, 60, 100, 25);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(140, 60, 200, 25);
		contentPane.add(txtName);

		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCalories.setBounds(30, 100, 100, 25);
		contentPane.add(lblCalories);

		txtCalories = new JTextField();
		txtCalories.setBounds(140, 100, 200, 25);
		contentPane.add(txtCalories);

		JLabel lblProtein = new JLabel("Protein (g):");
		lblProtein.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProtein.setBounds(30, 140, 100, 25);
		contentPane.add(lblProtein);

		txtProtein = new JTextField();
		txtProtein.setBounds(140, 140, 200, 25);
		contentPane.add(txtProtein);

		JLabel lblCarbs = new JLabel("Carbs (g):");
		lblCarbs.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCarbs.setBounds(30, 180, 100, 25);
		contentPane.add(lblCarbs);

		txtCarbs = new JTextField();
		txtCarbs.setBounds(140, 180, 200, 25);
		contentPane.add(txtCarbs);

		JLabel lblFat = new JLabel("Fat (g):");
		lblFat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFat.setBounds(30, 220, 100, 25);
		contentPane.add(lblFat);

		txtFat = new JTextField();
		txtFat.setBounds(140, 220, 200, 25);
		contentPane.add(txtFat);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSave.setForeground(new Color(33, 150, 243));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnSave.setBounds(60, 280, 100, 30);
		contentPane.add(btnSave);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBack.setForeground(new Color(33, 150, 243));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBack.setBounds(220, 280, 100, 30);
		contentPane.add(btnBack);

		// Kaydet butonu işlevi
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = txtName.getText();
					double calories = Double.parseDouble(txtCalories.getText());
					double protein = Double.parseDouble(txtProtein.getText());
					double carbs = Double.parseDouble(txtCarbs.getText());
					double fat = Double.parseDouble(txtFat.getText());

					Product product = new Product(name, calories, protein, carbs, fat, user.getId());
					ProductDAO productDAO = new ProductDAO();
					productDAO.addProduct(product);

					if (!AppConfig.isTestMode) {
						JOptionPane.showMessageDialog(AddProductFrame.this, "Product/Recipe added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					dispose();
				} catch (Exception ex) {
					if (!AppConfig.isTestMode) {
						JOptionPane.showMessageDialog(AddProductFrame.this, "Please fill all fields correctly!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Geri dön butonu işlevi
		btnBack.addActionListener(e -> dispose());
	}

}
