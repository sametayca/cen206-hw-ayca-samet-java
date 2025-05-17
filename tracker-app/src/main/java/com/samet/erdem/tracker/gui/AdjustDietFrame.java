package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.dao.UserDAO;

public class AdjustDietFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCurrentHeight;
	private JLabel lblCurrentWeight;
	private JTextField txtNewHeight;
	private JTextField txtNewWeight;
	private JButton btnUpdate;
	private JButton btnBack;

	private User user;

	public AdjustDietFrame(User user) {
		this.user = user;

		setTitle("Adjust Diet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Adjust Diet (Height & Weight)");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setForeground(new Color(33, 150, 243));
		lblTitle.setBounds(50, 10, 350, 30);
		contentPane.add(lblTitle);

		lblCurrentHeight = new JLabel("Current Height: " + user.getHeight() + " cm");
		lblCurrentHeight.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCurrentHeight.setBounds(40, 60, 300, 25);
		contentPane.add(lblCurrentHeight);

		lblCurrentWeight = new JLabel("Current Weight: " + user.getWeight() + " kg");
		lblCurrentWeight.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCurrentWeight.setBounds(40, 95, 300, 25);
		contentPane.add(lblCurrentWeight);

		JLabel lblNewHeight = new JLabel("New Height (cm):");
		lblNewHeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewHeight.setBounds(40, 140, 120, 25);
		contentPane.add(lblNewHeight);

		txtNewHeight = new JTextField();
		txtNewHeight.setBounds(180, 140, 150, 25);
		contentPane.add(txtNewHeight);

		JLabel lblNewWeight = new JLabel("New Weight (kg):");
		lblNewWeight.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewWeight.setBounds(40, 180, 120, 25);
		contentPane.add(lblNewWeight);

		txtNewWeight = new JTextField();
		txtNewWeight.setBounds(180, 180, 150, 25);
		contentPane.add(txtNewWeight);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnUpdate.setForeground(new Color(33, 150, 243));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnUpdate.setBounds(60, 230, 120, 30);
		contentPane.add(btnUpdate);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBack.setForeground(new Color(33, 150, 243));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnBack.setBounds(220, 230, 120, 30);
		contentPane.add(btnBack);

		// Update butonu işlevi
		btnUpdate.addActionListener(e -> updateDiet());

		// Back butonu işlevi
		btnBack.addActionListener(e -> dispose());
	}

	private void updateDiet() {
		try {
			String newHeightStr = txtNewHeight.getText().trim();
			String newWeightStr = txtNewWeight.getText().trim();

			if (!newHeightStr.isEmpty()) {
				user.setHeight(Double.parseDouble(newHeightStr));
			}
			if (!newWeightStr.isEmpty()) {
				user.setWeight(Double.parseDouble(newWeightStr));
			}

			UserDAO userDAO = new UserDAO();
			boolean success = userDAO.updateUser(user);
			if (success) {
				JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				lblCurrentHeight.setText("Current Height: " + user.getHeight() + " cm");
				lblCurrentWeight.setText("Current Weight: " + user.getWeight() + " kg");
			} else {
				JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Test için sahte bir kullanıcı oluştur
					com.samet.erdem.tracker.model.User dummyUser = new com.samet.erdem.tracker.model.User();
					dummyUser.setId(1); // Varsa uygun bir id ver
					AdjustDietFrame frame = new AdjustDietFrame(dummyUser);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
