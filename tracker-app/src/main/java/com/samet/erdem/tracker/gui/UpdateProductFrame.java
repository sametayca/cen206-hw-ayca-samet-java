package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.ProductDAO;
import com.samet.erdem.tracker.AppConfig;

/**
 * @brief Frame for updating product and recipe information in the Recipe & Nutrition Tracker application.
 * 
 * This class provides a graphical user interface for modifying existing products and recipes.
 * It includes functionality for:
 * - Selecting a product from a dropdown list
 * - Viewing and editing product details (name, calories, protein, carbs, fat)
 * - Updating product information in the database
 * - Input validation and error handling
 * - Navigation between screens
 * 
 * The frame maintains the application's consistent design theme and
 * provides a clean, user-friendly interface for the update process.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class UpdateProductFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JComboBox<Product> comboProducts;
	JTextField txtName;
	JTextField txtCalories;
	JTextField txtProtein;
	JTextField txtCarbs;
	JTextField txtFat;
	private JButton btnUpdate;
	private JButton btnBack;

	private User user;
	private List<Product> products;

	/**
	 * @brief Main method to launch the update product frame.
	 * 
	 * Creates a dummy user and initializes the update product frame for testing purposes.
	 * This method is primarily used for testing the GUI independently.
	 * 
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Test için sahte bir kullanıcı oluştur
					com.samet.erdem.tracker.model.User dummyUser = new com.samet.erdem.tracker.model.User();
					dummyUser.setId(1); // Varsa uygun bir id ver
					UpdateProductFrame frame = new UpdateProductFrame(dummyUser);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @brief Constructs a new UpdateProductFrame for the specified user.
	 * 
	 * Initializes the update product window with all necessary UI components including:
	 * - Product selection dropdown
	 * - Input fields for product details
	 * - Update and Back buttons
	 * - Action listeners for form submission and navigation
	 * 
	 * The frame includes validation for:
	 * - Numeric input for nutritional values
	 * - Product selection verification
	 * - Database update confirmation
	 * 
	 * @param user The User object representing the currently logged-in user
	 */
	public UpdateProductFrame(User user) {
		this.user = user;

		setTitle("Update Product / Recipe");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Update Product / Recipe");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(33, 150, 243));
		lblTitle.setBounds(70, 10, 300, 30);
		contentPane.add(lblTitle);

		JLabel lblSelect = new JLabel("Select Product:");
		lblSelect.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSelect.setBounds(30, 60, 120, 25);
		contentPane.add(lblSelect);

		comboProducts = new JComboBox<>();
		comboProducts.setBounds(150, 60, 200, 25);
		contentPane.add(comboProducts);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblName.setBounds(30, 100, 100, 25);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 100, 200, 25);
		contentPane.add(txtName);

		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCalories.setBounds(30, 140, 100, 25);
		contentPane.add(lblCalories);

		txtCalories = new JTextField();
		txtCalories.setBounds(150, 140, 200, 25);
		contentPane.add(txtCalories);

		JLabel lblProtein = new JLabel("Protein (g):");
		lblProtein.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProtein.setBounds(30, 180, 100, 25);
		contentPane.add(lblProtein);

		txtProtein = new JTextField();
		txtProtein.setBounds(150, 180, 200, 25);
		contentPane.add(txtProtein);

		JLabel lblCarbs = new JLabel("Carbs (g):");
		lblCarbs.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCarbs.setBounds(30, 220, 100, 25);
		contentPane.add(lblCarbs);

		txtCarbs = new JTextField();
		txtCarbs.setBounds(150, 220, 200, 25);
		contentPane.add(txtCarbs);

		JLabel lblFat = new JLabel("Fat (g):");
		lblFat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFat.setBounds(30, 260, 100, 25);
		contentPane.add(lblFat);

		txtFat = new JTextField();
		txtFat.setBounds(150, 260, 200, 25);
		contentPane.add(txtFat);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnUpdate.setForeground(new Color(33, 150, 243));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnUpdate.setBounds(60, 320, 120, 30);
		contentPane.add(btnUpdate);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBack.setForeground(new Color(33, 150, 243));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBack.setBounds(220, 320, 120, 30);
		contentPane.add(btnBack);

		// Ürünleri yükle
		loadProducts();

		// Seçili ürün değişince alanları doldur
		comboProducts.addActionListener(e -> fillFields());

		// Update butonu işlevi
		btnUpdate.addActionListener(e -> updateProduct());

		// Back butonu işlevi
		btnBack.addActionListener(e -> dispose());
	}

	/**
	 * @brief Loads all products for the current user into the combo box.
	 * 
	 * Retrieves products from the database and populates the combo box.
	 * Automatically selects the first product and fills the input fields.
	 * Displays an error message if loading fails.
	 */
	private void loadProducts() {
		try {
			ProductDAO productDAO = new ProductDAO();
			products = productDAO.getAllProducts(user.getId());
			comboProducts.removeAllItems();
			for (Product p : products) {
				comboProducts.addItem(p);
			}
			if (!products.isEmpty()) {
				comboProducts.setSelectedIndex(0);
				fillFields();
			}
		} catch (Exception e) {
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Error loading products!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * @brief Fills the input fields with the selected product's information.
	 * 
	 * Updates all text fields with the current values of the selected product.
	 * Called when a new product is selected from the combo box.
	 */
	private void fillFields() {
		Product selected = (Product) comboProducts.getSelectedItem();
		if (selected != null) {
			txtName.setText(selected.getName());
			txtCalories.setText(String.valueOf(selected.getCalories()));
			txtProtein.setText(String.valueOf(selected.getProtein()));
			txtCarbs.setText(String.valueOf(selected.getCarbs()));
			txtFat.setText(String.valueOf(selected.getFat()));
		}
	}

	/**
	 * @brief Updates the selected product with new information.
	 * 
	 * Validates and updates the product's information in the database.
	 * Shows success or error messages based on the operation result.
	 * Includes validation for:
	 * - Numeric input for nutritional values
	 * - Product selection verification
	 */
	private void updateProduct() {
		Product selected = (Product) comboProducts.getSelectedItem();
		if (selected == null) return;
		try {
			selected.setName(txtName.getText());
			selected.setCalories(Double.parseDouble(txtCalories.getText()));
			selected.setProtein(Double.parseDouble(txtProtein.getText()));
			selected.setCarbs(Double.parseDouble(txtCarbs.getText()));
			selected.setFat(Double.parseDouble(txtFat.getText()));

			ProductDAO productDAO = new ProductDAO();
			boolean success = productDAO.updateProduct(selected);
			if (success) {
				if (!AppConfig.isTestMode) {
					JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				loadProducts();
			} else {
				if (!AppConfig.isTestMode) {
					JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Please fill all fields correctly!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
