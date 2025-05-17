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

public class UpdateProductFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Product> comboProducts;
	private JTextField txtName;
	private JTextField txtCalories;
	private JTextField txtProtein;
	private JTextField txtCarbs;
	private JTextField txtFat;
	private JButton btnUpdate;
	private JButton btnBack;

	private User user;
	private List<Product> products;

	/**
	 * Launch the application.
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
	 * Create the frame.
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
			JOptionPane.showMessageDialog(this, "Error loading products!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

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
				JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				loadProducts();
			} else {
				JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please fill all fields correctly!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
