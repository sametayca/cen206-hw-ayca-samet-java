package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;

public class MainMenuFrameTest {
    private MainMenuFrame mainMenuFrame;
    private User testUser;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            testUser = new User();
            testUser.setUsername("TestUser");
            mainMenuFrame = new MainMenuFrame(testUser);
            mainMenuFrame.setVisible(true);
        });
    }

    @Test
    public void testMainMenuFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("MainMenuFrame should not be null", mainMenuFrame);
            assertTrue("MainMenuFrame should be visible", mainMenuFrame.isVisible());
            assertEquals("Frame title should match", 
                "Recipe & Nutrition Tracker - Welcome TestUser", 
                mainMenuFrame.getTitle());
        });
    }

    @Test
    public void testAddProductButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton addProductButton = findButton(mainMenuFrame, "Add Product/Recipe");
            assertNotNull("Add Product button should exist", addProductButton);
            addProductButton.doClick();
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            assertTrue("AddProductFrame should be visible", isFrameVisibleWithTitleAndType("Add Product / Recipe", AddProductFrame.class));
        });
    }

    @Test
    public void testViewProductsButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton viewProductsButton = findButton(mainMenuFrame, "View All Products");
            assertNotNull("View All Products button should exist", viewProductsButton);
            viewProductsButton.doClick();
            
            // Wait for the frame to open
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Verify that AllProductsFrame is opened
            assertTrue("AllProductsFrame should be visible", 
                isFrameVisible("All Products"));
        });
    }

    @Test
    public void testUpdateProductButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton updateProductButton = findButton(mainMenuFrame, "Update Product");
            assertNotNull("Update Product button should exist", updateProductButton);
            updateProductButton.doClick();
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            assertTrue("UpdateProductFrame should be visible", isFrameVisibleWithTitleAndType("Update Product / Recipe", UpdateProductFrame.class));
        });
    }

    @Test
    public void testDeleteProductButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton deleteProductButton = findButton(mainMenuFrame, "Delete Product");
            assertNotNull("Delete Product button should exist", deleteProductButton);
            deleteProductButton.doClick();
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            assertTrue("DeleteProductFrame should be visible", isFrameVisibleWithTitleAndType("Delete Product / Recipe", DeleteProductFrame.class));
        });
    }

    @Test
    public void testCalculateCaloriesButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton calculateCaloriesButton = findButton(mainMenuFrame, "Calculate Calories");
            assertNotNull("Calculate Calories button should exist", calculateCaloriesButton);
            calculateCaloriesButton.doClick();
            
            // Wait for the frame to open
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Verify that CalculateCaloriesFrame is opened
            assertTrue("CalculateCaloriesFrame should be visible", 
                isFrameVisible("Calculate Calories"));
        });
    }

    @Test
    public void testAdjustDietButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton adjustDietButton = findButton(mainMenuFrame, "Adjust Diet");
            assertNotNull("Adjust Diet button should exist", adjustDietButton);
            adjustDietButton.doClick();
            
            // Wait for the frame to open
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Verify that AdjustDietFrame is opened
            assertTrue("AdjustDietFrame should be visible", 
                isFrameVisible("Adjust Diet"));
        });
    }

    @Test
    public void testLogoutButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton logoutButton = findButton(mainMenuFrame, "Logout");
            assertNotNull("Logout button should exist", logoutButton);
            logoutButton.doClick();
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            assertTrue("LogoutFrame should be visible", isFrameVisibleWithTitleAndType("Logout Confirmation", LogoutFrame.class));
        });
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

    // Helper method to check if a frame with given title is visible
    private boolean isFrameVisible(String title) {
        for (Frame frame : Frame.getFrames()) {
            if (frame.getTitle().equals(title) && frame.isVisible()) {
                return true;
            }
        }
        return false;
    }

    // Frame başlığı ve tipiyle kontrol eden yardımcı fonksiyon
    private boolean isFrameVisibleWithTitleAndType(String title, Class<?> frameClass) {
        for (Frame frame : Frame.getFrames()) {
            if (frameClass.isInstance(frame) && frame.getTitle().equals(title) && frame.isVisible()) {
                return true;
            }
        }
        return false;
    }
} 