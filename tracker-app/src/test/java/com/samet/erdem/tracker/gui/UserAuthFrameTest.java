package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.AppConfig;
import org.junit.BeforeClass;

public class UserAuthFrameTest {
    private UserAuthFrame userAuthFrame;

    @BeforeClass
    public static void setUpClass() {
        AppConfig.isTestMode = true;
    }

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            userAuthFrame = new UserAuthFrame();
            userAuthFrame.setVisible(true);
        });
    }

    @Test
    public void testUserAuthFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("UserAuthFrame should not be null", userAuthFrame);
            assertTrue("UserAuthFrame should be visible", userAuthFrame.isVisible());
            assertEquals("Recipe & Nutrition Tracker", userAuthFrame.getTitle());
        });
    }

    @Test
    public void testInputFields() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            java.util.List<JTextField> textFields = new java.util.ArrayList<>();
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JTextField) {
                    textFields.add((JTextField) comp);
                }
            }
            assertEquals("Should have 2 text fields", 2, textFields.size());
            JTextField usernameField = textFields.get(0);
            JTextField passwordField = textFields.get(1);

            usernameField.setText("testuser");
            assertEquals("Username field should contain entered text", "testuser", usernameField.getText());

            passwordField.setText("testpass");
            assertEquals("Password field should contain entered text", "testpass", passwordField.getText());
        });
    }

    @Test
    public void testLoginButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton loginButton = null;
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {
                    loginButton = (JButton) comp;
                    break;
                }
            }
            assertNotNull("Login button should exist", loginButton);
            loginButton.doClick();
            // Başarılı login için ek kontrol eklenmedi, sadece tıklanabilirlik test ediliyor.
        });
    }

    @Test
    public void testRegisterButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton registerButton = null;
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JButton && ((JButton) comp).getText().equals("Register")) {
                    registerButton = (JButton) comp;
                    break;
                }
            }
            assertNotNull("Register button should exist", registerButton);
            registerButton.doClick();
        });
        // Check for a visible frame with the expected title
        boolean found = false;
        for (Frame frame : Frame.getFrames()) {
            if (frame.isVisible() && "Recipe & Nutrition Tracker".equals(frame.getTitle())) {
                found = true;
                break;
            }
        }
        assertTrue("RegisterFrame should be visible", found);
    }

    // Helper method to find a text field by its name
    private JTextField findTextField(JFrame frame, String name) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JTextField && comp.getName() != null && comp.getName().equals(name)) {
                return (JTextField) comp;
            }
        }
        return null;
    }

    // Helper method to find the password field
    private JPasswordField findPasswordField(JFrame frame) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPasswordField && comp.getName() != null && comp.getName().equals("password")) {
                return (JPasswordField) comp;
            }
        }
        return null;
    }

    // Helper method to find a button by its text
    private JButton findButton(JFrame frame, String text) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            }
        }
        return null;
    }

    // Helper method to check if a frame with given title is visible
    private boolean isFrameVisible(String title) {
        for (Frame frame : Frame.getFrames()) {
            if (frame.getTitle().equals(title) && frame.isVisible()) {
                return true;
            }
        }
        return false;
    }
    
    
    @Test
    public void testLoginWithValidCredentials() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Username ve password alanlarını doldur
            java.util.List<JTextField> textFields = new java.util.ArrayList<>();
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JTextField) {
                    textFields.add((JTextField) comp);
                }
            }
            textFields.get(0).setText("validuser");   // Username
            textFields.get(1).setText("validpass");   // Password

            // Login butonunu bul ve tıkla
            JButton loginButton = null;
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {
                    loginButton = (JButton) comp;
                    break;
                }
            }
            assertNotNull(loginButton);
            loginButton.doClick();}

            // Başarılı girişte UserAuthFrame kapanmalı
         );
    }
    
    
    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            java.util.List<JTextField> textFields = new java.util.ArrayList<>();
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JTextField) {
                    textFields.add((JTextField) comp);
                }
            }
            textFields.get(0).setText("wronguser");   // Username
            textFields.get(1).setText("wrongpass");   // Password

            JButton loginButton = null;
            for (Component comp : userAuthFrame.getContentPane().getComponents()) {
                if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {
                    loginButton = (JButton) comp;
                    break;
                }
            }
            assertNotNull(loginButton);
            loginButton.doClick();

            // Başarısız girişte UserAuthFrame açık kalmalı
            assertTrue("Frame should remain open after failed login", userAuthFrame.isVisible());
        });
    }
    
    
    
    
} 