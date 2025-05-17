package com.samet.erdem.tracker.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LogoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnYes;
	private JButton btnNo;

	public LogoutFrame(JFrame parentFrame) {
		setTitle("Logout Confirmation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 180);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 249, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConfirm = new JLabel("Are you sure you want to logout?");
		lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblConfirm.setForeground(new Color(33, 150, 243));
		lblConfirm.setBounds(30, 20, 300, 30);
		contentPane.add(lblConfirm);

		btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnYes.setForeground(new Color(33, 150, 243));
		btnYes.setBackground(Color.WHITE);
		btnYes.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnYes.setBounds(40, 80, 100, 30);
		contentPane.add(btnYes);

		btnNo = new JButton("No");
		btnNo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNo.setForeground(new Color(33, 150, 243));
		btnNo.setBackground(Color.WHITE);
		btnNo.setBorder(new LineBorder(new Color(33, 150, 243), 1, true));
		btnNo.setBounds(180, 80, 100, 30);
		contentPane.add(btnNo);

		// Evet'e basılırsa ana pencereyi kapat ve giriş ekranını aç
		btnYes.addActionListener(e -> {
			if (parentFrame != null) {
				parentFrame.dispose();
			}
			UserAuthFrame loginFrame = new UserAuthFrame();
			loginFrame.setVisible(true);
			loginFrame.setLocationRelativeTo(null);
			dispose();
		});

		// Hayır'a basılırsa sadece bu pencereyi kapat
		btnNo.addActionListener(e -> dispose());
	}
}
