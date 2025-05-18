package com.samet.erdem.tracker.gui;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RegisterFrameTest {
    private RegisterFrame frame;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            frame = new RegisterFrame();
            frame.setVisible(true);
        });
    }

    @Test
    public void testRegisterFrameInitialization() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("Frame should not be null", frame);
            assertTrue("Frame should be visible", frame.isVisible());
            assertEquals("Frame title should match", "Recipe & Nutrition Tracker", frame.getTitle());
        });
    }

    @Test
    public void testInputFields() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            List<JTextField> textFields = findTextFields(frame);
            assertEquals("Should have 5 text fields", 5, textFields.size());

            // Test setting values
            textFields.get(0).setText("testuser"); // Username
            textFields.get(1).setText("testpass"); // Password
            textFields.get(2).setText("testpass"); // Confirm Password
            textFields.get(3).setText("170");      // Height
            textFields.get(4).setText("70");       // Weight

            assertEquals("Username should match", "testuser", textFields.get(0).getText());
            assertEquals("Password should match", "testpass", textFields.get(1).getText());
            assertEquals("Confirm password should match", "testpass", textFields.get(2).getText());
            assertEquals("Height should match", "170", textFields.get(3).getText());
            assertEquals("Weight should match", "70", textFields.get(4).getText());
        });
    }

    @Test
    public void testCreateAccountButton() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            JButton createAccountButton = findButton(frame, "Create Account");
            assertNotNull("Create Account button should exist", createAccountButton);
            assertTrue("Create Account button should be visible", createAccountButton.isVisible());
        });
    }

    @Test
    public void testBackToLoginButton() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            JButton backButton = findButton(frame, "Back to Login");
            assertNotNull("Back to Login button should exist", backButton);
            assertTrue("Back to Login button should be visible", backButton.isVisible());

            // Test button click
            backButton.doClick();
            assertFalse("Frame should be disposed after clicking back button", frame.isVisible());
        });
    }

    private List<JTextField> findTextFields(Container container) {
        List<JTextField> textFields = new ArrayList<>();
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                textFields.add((JTextField) component);
            }
            if (component instanceof Container) {
                textFields.addAll(findTextFields((Container) component));
            }
        }
        return textFields;
    }

    private JButton findButton(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(text)) {
                return (JButton) component;
            }
            if (component instanceof Container) {
                JButton found = findButton((Container) component, text);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
} 