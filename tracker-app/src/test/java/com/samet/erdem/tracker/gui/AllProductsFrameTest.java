package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;

public class AllProductsFrameTest {
    private AllProductsFrame allProductsFrame;
    private User testUser;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            testUser = new User();
            testUser.setUsername("TestUser");
            allProductsFrame = new AllProductsFrame(testUser);
            allProductsFrame.setVisible(true);
        });
    }

    @Test
    public void testAllProductsFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("AllProductsFrame should not be null", allProductsFrame);
            assertTrue("AllProductsFrame should be visible", allProductsFrame.isVisible());
            assertEquals("AllProductsFrame title should match", "All Products", allProductsFrame.getTitle());
        });
    }

    @Test
    public void testProductTable() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTable table = findTable(allProductsFrame);
            assertNotNull("Table should exist", table);
            
            // Test column names
            assertEquals("Column 0 should match", "Name", table.getColumnName(0));
            assertEquals("Column 1 should match", "Calories", table.getColumnName(1));
            assertEquals("Column 2 should match", "Protein (g)", table.getColumnName(2));
            assertEquals("Column 3 should match", "Carbs (g)", table.getColumnName(3));
            assertEquals("Column 4 should match", "Fat (g)", table.getColumnName(4));
        });
    }

    @Test
    public void testCloseButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton closeButton = findButton(allProductsFrame, "Close");
            assertNotNull("Close button should exist", closeButton);
            closeButton.doClick();
            assertFalse("Frame should be closed after clicking close", allProductsFrame.isVisible());
        });
    }

    // Helper method to find the table
    private JTable findTable(JFrame frame) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JTable) {
                    return (JTable) view;
                }
            }
        }
        return null;
    }

    // Helper method to find a button by its text
    private JButton findButton(JFrame frame, String text) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component panelComp : panel.getComponents()) {
                    if (panelComp instanceof JButton && ((JButton) panelComp).getText().equals(text)) {
                        return (JButton) panelComp;
                    }
                }
            }
        }
        return null;
    }
} 