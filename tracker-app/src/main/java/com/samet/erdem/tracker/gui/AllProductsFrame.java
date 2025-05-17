package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.ProductDAO;

public class AllProductsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public AllProductsFrame(User user) {
		setTitle("All Products");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("All Products / Recipes");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(33, 150, 243));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);

		// Tabloyu oluştur
		String[] columnNames = {"Name", "Calories", "Protein (g)", "Carbs (g)", "Fat (g)"};
		Object[][] data = getProductData(user);

		table = new JTable(data, columnNames);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(24);
		table.setEnabled(false);

		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnClose.setForeground(new Color(33, 150, 243));
		btnClose.setBackground(Color.WHITE);
		btnClose.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 1, true));
		btnClose.addActionListener(e -> dispose());
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(new Color(245, 249, 255));
		btnPanel.add(btnClose);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
	}

	// Kullanıcının ürünlerini çekip tabloya uygun diziye çevirir
	private Object[][] getProductData(User user) {
		try {
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.getAllProducts(user.getId());
			Object[][] data = new Object[products.size()][5];
			for (int i = 0; i < products.size(); i++) {
				Product p = products.get(i);
				data[i][0] = p.getName();
				data[i][1] = p.getCalories();
				data[i][2] = p.getProtein();
				data[i][3] = p.getCarbs();
				data[i][4] = p.getFat();
			}
			return data;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error loading products!", "Error", JOptionPane.ERROR_MESSAGE);
			return new Object[0][5];
		}
	}
}
