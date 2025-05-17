package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.ProductDAO;

public class DeleteProductFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Product> comboProducts;
	private JButton btnDelete;
	private JButton btnBack;

	private User user;
	private List<Product> products;

	public DeleteProductFrame(User user) {
		this.user = user;

		setTitle("Delete Product / Recipe");
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

	private void loadProducts() {
		try {
			ProductDAO productDAO = new ProductDAO();
			products = productDAO.getAllProducts(user.getId());
			comboProducts.removeAllItems();
			for (Product p : products) {
				comboProducts.addItem(p);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error loading products!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteProduct() {
		Product selected = (Product) comboProducts.getSelectedItem();
		if (selected == null) return;
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return;
		try {
			ProductDAO productDAO = new ProductDAO();
			boolean success = productDAO.deleteProduct(selected.getId(), user.getId());
			if (success) {
				JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				loadProducts();
			} else {
				JOptionPane.showMessageDialog(this, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error deleting product!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
