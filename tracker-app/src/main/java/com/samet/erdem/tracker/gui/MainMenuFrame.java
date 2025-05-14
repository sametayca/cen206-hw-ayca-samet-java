package com.samet.erdem.tracker.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;

import com.samet.erdem.tracker.model.User;

public class MainMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User currentUser; // giriş yapan kullanıcı

	/**
	 * Launch the application (test amaçlı).
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// test için boş kullanıcı
					User dummyUser = new User();
					dummyUser.setUsername("Guest");
					MainMenuFrame frame = new MainMenuFrame(dummyUser);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame with user.
	 */
	public MainMenuFrame(User user) {
		this.currentUser = user;

		setTitle("Recipe & Nutrition Tracker - Welcome " + user.getUsername());
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome, " + user.getUsername() + "!");
		lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblWelcome.setBounds(30, 30, 400, 40);
		contentPane.add(lblWelcome);

		// Buraya butonlar, menüler vs. eklemeye devam edebilirsin
	}
}
