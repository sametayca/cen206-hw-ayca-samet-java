package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.ProductDAO;
import com.samet.erdem.tracker.AppConfig;

/**
 * @brief Frame for deleting products and recipes from the user's database.
 * 
 * This class provides a graphical user interface that allows users to:
 * - View a list of their existing products/recipes
 * - Select a product/recipe for deletion
 * - Confirm deletion through a dialog
 * - Receive feedback on the deletion operation
 * 
 * The frame maintains the application's consistent design theme and includes
 * error handling for database operations.
 * 
 * @author Samet Erdem
 * @version 1.0
 */
public class DeleteProductFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Main content panel of the frame */
	private JPanel contentPane;
	
	/** Combo box for selecting the product to delete */
	private JComboBox<Product> comboProducts;
	
	/** Button to confirm and execute product deletion */
	private JButton btnDelete;
	
	/** Button to return to previous screen */
	private JButton btnBack;
	
	/** Current user whose products are being managed */
	private User user;
	
	/** List of products available for deletion */
	private List<Product> products;

	/**
	 * @brief Constructs a new DeleteProductFrame for the specified user.
	 * 
	 * Initializes the frame and sets up the UI components for product deletion.
	 * The frame includes:
	 * - Product selection dropdown
	 * - Delete and back buttons
	 * - Confirmation dialog
	 * 
	 * @param user The User object whose products will be managed
	 */
	public DeleteProductFrame(User user) {
		this.user = user;

		setTitle("Delete Product / Recipe");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 220);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Delete Product / Recipe");
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

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDelete.setForeground(new Color(33, 150, 243));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnDelete.setBounds(60, 120, 120, 30);
		contentPane.add(btnDelete);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBack.setForeground(new Color(33, 150, 243));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBack.setBounds(220, 120, 120, 30);
		contentPane.add(btnBack);

		// Ürünleri yükle
		loadProducts();

		// Delete butonu işlevi
		btnDelete.addActionListener(e -> deleteProduct());

		// Back butonu işlevi
		btnBack.addActionListener(e -> dispose());
	}

	/**
	 * @brief Loads the user's products into the selection dropdown.
	 * 
	 * Retrieves all products associated with the user from the database
	 * and populates the combo box. Includes error handling for database
	 * operations and displays appropriate error messages.
	 */
	private void loadProducts() {
		try {
			ProductDAO productDAO = new ProductDAO();
			products = productDAO.getAllProducts(user.getId());
			comboProducts.removeAllItems();
			for (Product p : products) {
				comboProducts.addItem(p);
			}
		} catch (Exception e) {
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Error loading products!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * @brief Handles the product deletion process.
	 * 
	 * Performs the following operations:
	 * - Gets the selected product from the combo box
	 * - Shows a confirmation dialog
	 * - Deletes the product from the database if confirmed
	 * - Refreshes the product list
	 * - Shows success/error messages
	 * 
	 * Includes error handling for database operations and user feedback.
	 */
	private void deleteProduct() {
		Product selected = (Product) comboProducts.getSelectedItem();
		if (selected == null) return;
		int confirm = JOptionPane.YES_OPTION;
		if (!AppConfig.isTestMode) {
			confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		}
		if (confirm != JOptionPane.YES_OPTION) return;
		try {
			ProductDAO productDAO = new ProductDAO();
			boolean success = productDAO.deleteProduct(selected.getId(), user.getId());
			if (success) {
				if (!AppConfig.isTestMode) {
					JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				loadProducts();
			} else {
				if (!AppConfig.isTestMode) {
					JOptionPane.showMessageDialog(this, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			if (!AppConfig.isTestMode) {
				JOptionPane.showMessageDialog(this, "Error deleting product!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
