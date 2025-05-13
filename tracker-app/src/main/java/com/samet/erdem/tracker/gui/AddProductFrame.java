package com.samet.erdem.tracker.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.samet.erdem.tracker.manager.ProductManager;
import com.samet.erdem.tracker.model.Product;

public class AddProductFrame extends JFrame {
	private static final Color PRIMARY_COLOR = new Color(33, 150, 243);
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtCalories;
	private JTextField txtPortion;

	public AddProductFrame() {
		setTitle("Add Product");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setLocationRelativeTo(null); // Ortala

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Title panel
		JPanel topPanel = new JPanel();
		topPanel.setBackground(PRIMARY_COLOR);
		topPanel.setBounds(0, 0, 484, 60);
		topPanel.setLayout(null);
		contentPane.add(topPanel);

		JLabel lblTitle = new JLabel("Add Product or Recipe");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setBounds(20, 10, 300, 40);
		topPanel.add(lblTitle);

		// Product Name
		JLabel lblName = new JLabel("Product Name:");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblName.setBounds(40, 80, 120, 25);
		contentPane.add(lblName);

		txtProductName = new JTextField();
		txtProductName.setBounds(180, 80, 220, 25);
		contentPane.add(txtProductName);

		// Type
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblType.setBounds(40, 120, 120, 25);
		contentPane.add(lblType);

		String[] types = { "Food", "Drink", "Other" };
		JComboBox<String> comboType = new JComboBox<>(types);
		comboType.setBounds(180, 120, 220, 25);
		contentPane.add(comboType);

		// Calories
		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCalories.setBounds(40, 160, 120, 25);
		contentPane.add(lblCalories);

		txtCalories = new JTextField();
		txtCalories.setBounds(180, 160, 220, 25);
		contentPane.add(txtCalories);

		// Portion
		JLabel lblPortion = new JLabel("Portion Size:");
		lblPortion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPortion.setBounds(40, 200, 120, 25);
		contentPane.add(lblPortion);

		txtPortion = new JTextField();
		txtPortion.setBounds(180, 200, 220, 25);
		contentPane.add(txtPortion);

		// Save Button
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(180, 260, 100, 41);
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSave.setForeground(PRIMARY_COLOR);
		btnSave.setBackground(Color.WHITE);
		btnSave.setFocusPainted(false);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		contentPane.add(btnSave);

		// Cancel Button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(300, 260, 100, 41);
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCancel.setForeground(PRIMARY_COLOR);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFocusPainted(false);
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		contentPane.add(btnCancel);

		// Save Action
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = txtProductName.getText().trim();
					String type = comboType.getSelectedItem().toString();
					String portion = txtPortion.getText().trim();
					double calories = Double.parseDouble(txtCalories.getText().trim());

					if (name.isEmpty() || portion.isEmpty()) {
						JOptionPane.showMessageDialog(AddProductFrame.this, "Please fill in all fields.",
								"Missing Information", JOptionPane.WARNING_MESSAGE);
						return;
					}

					// Geçici sabit değerler (geliştirilebilir)
					double protein = 0, carbs = 0, fat = 0;
					int userId = 1;

					Product newProduct = new Product(name, calories, protein, carbs, fat, userId);
					newProduct.setPortion(portion); // eğer Product sınıfında varsa
					ProductManager.addProduct(newProduct);

					JOptionPane.showMessageDialog(AddProductFrame.this, "Product added successfully!",
							"Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(AddProductFrame.this, "Calories must be a number.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Cancel Action
		btnCancel.addActionListener(e -> dispose());
	}
}
