package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.samet.erdem.tracker.manager.ProductManager;
import com.samet.erdem.tracker.model.Product;

import java.awt.*;
import java.util.List;

public class AllProductsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private static final Color PRIMARY_COLOR = new Color(33, 150, 243);
	private static final Color BACKGROUND_COLOR = new Color(245, 249, 255);
	private static final Font TABLE_FONT = new Font("Segoe UI", Font.PLAIN, 13);

	public AllProductsFrame() {
		setTitle("All Products");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 450);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel titleLabel = new JLabel("All Products");
		titleLabel.setForeground(PRIMARY_COLOR);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel, BorderLayout.NORTH);

		// Tablo başlıkları (portion eklendi)
		String[] columnNames = {
			"Name", "Calories", "Protein", "Carbs", "Fat", "Portion", "User ID"
		};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setFont(TABLE_FONT);
		table.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Verileri yükle
		loadProductsIntoTable();
	}

	private void loadProductsIntoTable() {
		List<Product> productList = ProductManager.getAllProducts();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // eski verileri temizle

		for (Product p : productList) {
			Object[] rowData = {
				p.getName(),
				p.getCalories(),
				p.getProtein(),
				p.getCarbs(),
				p.getFat(),
				p.getPortion(), // portion alanı burada
				p.getUserId()
			};
			model.addRow(rowData);
		}
	}
}
