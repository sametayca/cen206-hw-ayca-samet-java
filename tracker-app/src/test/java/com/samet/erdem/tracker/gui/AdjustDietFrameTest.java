package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.AppConfig;

public class AdjustDietFrameTest {
    private AdjustDietFrame adjustDietFrame;
    private User testUser;

    @BeforeClass
    public static void setUpClass() {
        AppConfig.isTestMode = true;
    }

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            testUser = new User();
            testUser.setUsername("TestUser");
            testUser.setHeight(170);
            testUser.setWeight(70);
            adjustDietFrame = new AdjustDietFrame(testUser);
            adjustDietFrame.setVisible(true);
        });
    }

    @Test
    public void testAdjustDietFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("AdjustDietFrame should not be null", adjustDietFrame);
            assertTrue("AdjustDietFrame should be visible", adjustDietFrame.isVisible());
            assertEquals("Adjust Diet", adjustDietFrame.getTitle());
        });
    }

    @Test
    public void testInputFieldsAndUpdate() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField heightField = findTextFieldByBounds(adjustDietFrame, 180, 140);
            JTextField weightField = findTextFieldByBounds(adjustDietFrame, 180, 180);
            assertNotNull("Height field should exist", heightField);
            assertNotNull("Weight field should exist", weightField);
            heightField.setText("180");
            weightField.setText("80");

            JButton updateButton = findButton(adjustDietFrame, "Update");
            assertNotNull("Update button should exist", updateButton);
            updateButton.doClick();

            // Frame kapanmıyor, ama değerler güncellenmiş olmalı
            assertEquals(180.0, testUser.getHeight(), 0.01);
            assertEquals(80.0, testUser.getWeight(), 0.01);
        });
    }

    // Helper method to find a text field by its bounds
    private JTextField findTextFieldByBounds(JFrame frame, int x, int y) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JTextField) {
                Rectangle bounds = comp.getBounds();
                if (bounds.x == x && bounds.y == y) {
                    return (JTextField) comp;
                }
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
    
    
    @Test
    public void testUpdateDietWithValidInput() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField heightField = findTextFieldByBounds(adjustDietFrame, 180, 140);
            JTextField weightField = findTextFieldByBounds(adjustDietFrame, 180, 180);
            heightField.setText("185");
            weightField.setText("85");

            JButton updateButton = findButton(adjustDietFrame, "Update");
            updateButton.doClick();

            // Kullanıcı güncellenmiş mi?
            assertEquals(185.0, testUser.getHeight(), 0.01);
            assertEquals(85.0, testUser.getWeight(), 0.01);

            // Label'lar güncellenmiş mi?
            JLabel lblCurrentHeight = findLabelByText(adjustDietFrame, "Current Height: 185.0 cm");
            JLabel lblCurrentWeight = findLabelByText(adjustDietFrame, "Current Weight: 85.0 kg");
           
        });
    }

    @Test
    public void testUpdateDietWithInvalidInput() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField heightField = findTextFieldByBounds(adjustDietFrame, 180, 140);
            JTextField weightField = findTextFieldByBounds(adjustDietFrame, 180, 180);
            heightField.setText("abc"); // Geçersiz
            weightField.setText("xyz"); // Geçersiz

            JButton updateButton = findButton(adjustDietFrame, "Update");
            updateButton.doClick();

            // Kullanıcı güncellenmemeli
            assertEquals(170.0, testUser.getHeight(), 0.01);
            assertEquals(70.0, testUser.getWeight(), 0.01);

            // Label'lar değişmemeli
            JLabel lblCurrentHeight = findLabelByText(adjustDietFrame, "Current Height: 170.0 cm");
            JLabel lblCurrentWeight = findLabelByText(adjustDietFrame, "Current Weight: 70.0 kg");
            assertNotNull(lblCurrentHeight);
            assertNotNull(lblCurrentWeight);
        });
    }

    // Helper method to find a label by its text
    private JLabel findLabelByText(JFrame frame, String text) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().equals(text)) {
                return (JLabel) comp;
            }
        }
        return null;
    }
    
    @Test
    public void testUpdateDietWithOnlyHeight() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField heightField = findTextFieldByBounds(adjustDietFrame, 180, 140);
            JTextField weightField = findTextFieldByBounds(adjustDietFrame, 180, 180);
            heightField.setText("190");
            weightField.setText(""); // Boş bırakıldı

            JButton updateButton = findButton(adjustDietFrame, "Update");
            updateButton.doClick();

            // Sadece height değişmeli, weight aynı kalmalı
            assertEquals(190.0, testUser.getHeight(), 0.01);
            assertEquals(70.0, testUser.getWeight(), 0.01);
        });
    }
    
    
} 