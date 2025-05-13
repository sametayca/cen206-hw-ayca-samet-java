package com.samet.erdem.tracker.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
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
	
	public MainMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Top bar (title)
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 60));
		topPanel.setLayout(null);
		topPanel.setBackground(new java.awt.Color(33, 150, 243));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Recipe & Nutrition Tracker");
		titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
		titleLabel.setForeground(java.awt.Color.WHITE);
		titleLabel.setBounds(20, 10, 350, 40);
		topPanel.add(titleLabel);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(180, 0));
		panel.setBackground(new java.awt.Color(245, 249, 255));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Samet\\Desktop\\cen206-hw-ayca-samet-java\\tracker-app\\src\\main\\resources\\logo (Custom) (2).png"));
		lblNewLabel.setBounds(45, 22, 106, 102);
		panel.add(lblNewLabel);

		// Buton stili
		java.awt.Color PRIMARY_COLOR = new java.awt.Color(33, 150, 243);
		java.awt.Font btnFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15);

		JButton btnAddProduct = new JButton("Add Product or Recipe");
		btnAddProduct.setBounds(10, 150, 160, 33);
		btnAddProduct.setFont(btnFont);
		btnAddProduct.setForeground(PRIMARY_COLOR);
		btnAddProduct.setBackground(java.awt.Color.WHITE);
		btnAddProduct.setFocusPainted(false);
		btnAddProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnAddProduct.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnAddProduct);

		JButton btnAllProducts = new JButton("All Products");
		btnAllProducts.setBounds(10, 190, 160, 33);
		btnAllProducts.setFont(btnFont);
		btnAllProducts.setForeground(PRIMARY_COLOR);
		btnAllProducts.setBackground(java.awt.Color.WHITE);
		btnAllProducts.setFocusPainted(false);
		btnAllProducts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnAllProducts.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnAllProducts);

		JButton btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.setBounds(10, 230, 160, 33);
		btnUpdateProduct.setFont(btnFont);
		btnUpdateProduct.setForeground(PRIMARY_COLOR);
		btnUpdateProduct.setBackground(java.awt.Color.WHITE);
		btnUpdateProduct.setFocusPainted(false);
		btnUpdateProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnUpdateProduct.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnUpdateProduct);

		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.setBounds(10, 270, 160, 33);
		btnDeleteProduct.setFont(btnFont);
		btnDeleteProduct.setForeground(PRIMARY_COLOR);
		btnDeleteProduct.setBackground(java.awt.Color.WHITE);
		btnDeleteProduct.setFocusPainted(false);
		btnDeleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnDeleteProduct.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnDeleteProduct);

		JButton btnCalculateCalories = new JButton("Calculate Calories");
		btnCalculateCalories.setBounds(10, 310, 160, 33);
		btnCalculateCalories.setFont(btnFont);
		btnCalculateCalories.setForeground(PRIMARY_COLOR);
		btnCalculateCalories.setBackground(java.awt.Color.WHITE);
		btnCalculateCalories.setFocusPainted(false);
		btnCalculateCalories.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnCalculateCalories.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnCalculateCalories);

		JButton btnAdjustDiet = new JButton("Adjust Diet");
		btnAdjustDiet.setBounds(10, 350, 160, 33);
		btnAdjustDiet.setFont(btnFont);
		btnAdjustDiet.setForeground(PRIMARY_COLOR);
		btnAdjustDiet.setBackground(java.awt.Color.WHITE);
		btnAdjustDiet.setFocusPainted(false);
		btnAdjustDiet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnAdjustDiet.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnAdjustDiet);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(10, 400, 160, 33);
		btnExit.setFont(btnFont);
		btnExit.setForeground(PRIMARY_COLOR);
		btnExit.setBackground(java.awt.Color.WHITE);
		btnExit.setFocusPainted(false);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnExit.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			javax.swing.BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
			javax.swing.BorderFactory.createEmptyBorder(8, 0, 8, 0)
		));
		panel.add(btnExit);
		
		JPanel profile_panel = new JPanel();
		profile_panel.setBackground(new Color(245, 249, 255));
		profile_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(profile_panel, BorderLayout.CENTER);
		profile_panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblUsername.setBounds(303, 50, 63, 26);
		profile_panel.add(lblUsername);
		
		JLabel lblHeight = new JLabel("Height :");
		lblHeight.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblHeight.setBounds(303, 88, 63, 26);
		profile_panel.add(lblHeight);
		
		JLabel lblWeight = new JLabel("Weight :");
		lblWeight.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblWeight.setBounds(303, 121, 63, 26);
		profile_panel.add(lblWeight);
		
		JLabel lblComment = new JLabel("Comment :");
		lblComment.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblComment.setBounds(303, 195, 63, 26);
		profile_panel.add(lblComment);
		
		JLabel lblBMI = new JLabel("Username :");
		lblBMI.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblBMI.setBounds(303, 158, 63, 26);
		profile_panel.add(lblBMI);
	}
}
