package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;

public class DeleteProductFrameTest {
    private DeleteProductFrame deleteProductFrame;
    private User testUser;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            testUser = new User();
            testUser.setUsername("TestUser");
            deleteProductFrame = new DeleteProductFrame(testUser);
            deleteProductFrame.setVisible(true);
        });
    }

    @Test
    public void testDeleteProductFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("DeleteProductFrame should not be null", deleteProductFrame);
            assertTrue("DeleteProductFrame should be visible", deleteProductFrame.isVisible());
            assertEquals("DeleteProductFrame title should match", "Delete Product / Recipe", deleteProductFrame.getTitle());
        });
    }

    @Test
    public void testProductComboBox() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JComboBox<?> productComboBox = findComboBoxByBounds(deleteProductFrame, 150, 60);
            assertNotNull("Product combo box should exist", productComboBox);
            // Test that combo box is present (populated olması için gerçek ürün gerekebilir)
        });
    }

    @Test
    public void testDeleteButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton deleteButton = findButton(deleteProductFrame, "Delete");
            assertNotNull("Delete button should exist", deleteButton);
            deleteButton.doClick();
            // Silme işlemi için ek assertion eklenebilir.
        });
    }

    @Test
    public void testBackButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton backButton = findButton(deleteProductFrame, "Back");
            assertNotNull("Back button should exist", backButton);
            backButton.doClick();
            assertFalse("Frame should be closed after clicking back", deleteProductFrame.isVisible());
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