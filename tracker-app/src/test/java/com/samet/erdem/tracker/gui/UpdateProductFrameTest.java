package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;

public class UpdateProductFrameTest {
    private UpdateProductFrame updateProductFrame;
    private User testUser;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            testUser = new User();
            testUser.setUsername("TestUser");
            updateProductFrame = new UpdateProductFrame(testUser);
            updateProductFrame.setVisible(true);
        });
    }

    @Test
    public void testUpdateProductFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("UpdateProductFrame should not be null", updateProductFrame);
            assertTrue("UpdateProductFrame should be visible", updateProductFrame.isVisible());
            assertEquals("UpdateProductFrame title should match", "Update Product / Recipe", updateProductFrame.getTitle());
        });
    }

    @Test
    public void testProductComboBox() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JComboBox<?> productComboBox = findComboBoxByBounds(updateProductFrame, 150, 60);
            assertNotNull("Product combo box should exist", productComboBox);
            // Test that combo box is present (populated olması için gerçek ürün gerekebilir)
        });
    }

    @Test
    public void testInputFields() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField nameField = findTextFieldByBounds(updateProductFrame, 150, 100);
            assertNotNull("Name field should exist", nameField);
            nameField.setText("Updated Product");
            assertEquals("Name field should contain entered text", "Updated Product", nameField.getText());

            JTextField caloriesField = findTextFieldByBounds(updateProductFrame, 150, 140);
            assertNotNull("Calories field should exist", caloriesField);
            caloriesField.setText("200");
            assertEquals("Calories field should contain entered text", "200", caloriesField.getText());

            JTextField proteinField = findTextFieldByBounds(updateProductFrame, 150, 180);
            assertNotNull("Protein field should exist", proteinField);
            proteinField.setText("20");
            assertEquals("Protein field should contain entered text", "20", proteinField.getText());

            JTextField carbsField = findTextFieldByBounds(updateProductFrame, 150, 220);
            assertNotNull("Carbs field should exist", carbsField);
            carbsField.setText("30");
            assertEquals("Carbs field should contain entered text", "30", carbsField.getText());

            JTextField fatField = findTextFieldByBounds(updateProductFrame, 150, 260);
            assertNotNull("Fat field should exist", fatField);
            fatField.setText("10");
            assertEquals("Fat field should contain entered text", "10", fatField.getText());
        });
    }

    @Test
    public void testUpdateButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton updateButton = findButton(updateProductFrame, "Update");
            assertNotNull("Update button should exist", updateButton);
            updateButton.doClick();
            // Güncelleme işlemi için ek assertion eklenebilir.
        });
    }

    @Test
    public void testBackButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton backButton = findButton(updateProductFrame, "Back");
            assertNotNull("Back button should exist", backButton);
            backButton.doClick();
            assertFalse("Frame should be closed after clicking back", updateProductFrame.isVisible());
        });
    }

    // Helper method to find the product combo box by its bounds
    private JComboBox<?> findComboBoxByBounds(JFrame frame, int x, int y) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JComboBox) {
                Rectangle bounds = comp.getBounds();
                if (bounds.x == x && bounds.y == y) {
                    return (JComboBox<?>) comp;
                }
            }
        }
        return null;
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
} 