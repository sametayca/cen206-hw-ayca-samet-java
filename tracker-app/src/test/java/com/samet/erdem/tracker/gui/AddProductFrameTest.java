package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.AppConfig;
import org.junit.BeforeClass;

public class AddProductFrameTest {
    private AddProductFrame addProductFrame;
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
            addProductFrame = new AddProductFrame(testUser);
            addProductFrame.setVisible(true);
        });
    }

    @Test
    public void testAddProductFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("AddProductFrame should not be null", addProductFrame);
            assertTrue("AddProductFrame should be visible", addProductFrame.isVisible());
            assertEquals("AddProductFrame title should match", "Add Product / Recipe", addProductFrame.getTitle());
        });
    }

    @Test
    public void testInputFields() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JTextField nameField = findTextFieldByBounds(addProductFrame, 140, 60);
            assertNotNull("Name field should exist", nameField);
            nameField.setText("Test Product");
            assertEquals("Name field should contain entered text", "Test Product", nameField.getText());

            JTextField caloriesField = findTextFieldByBounds(addProductFrame, 140, 100);
            assertNotNull("Calories field should exist", caloriesField);
            caloriesField.setText("100");
            assertEquals("Calories field should contain entered text", "100", caloriesField.getText());

            JTextField proteinField = findTextFieldByBounds(addProductFrame, 140, 140);
            assertNotNull("Protein field should exist", proteinField);
            proteinField.setText("10");
            assertEquals("Protein field should contain entered text", "10", proteinField.getText());

            JTextField carbsField = findTextFieldByBounds(addProductFrame, 140, 180);
            assertNotNull("Carbs field should exist", carbsField);
            carbsField.setText("20");
            assertEquals("Carbs field should contain entered text", "20", carbsField.getText());

            JTextField fatField = findTextFieldByBounds(addProductFrame, 140, 220);
            assertNotNull("Fat field should exist", fatField);
            fatField.setText("5");
            assertEquals("Fat field should contain entered text", "5", fatField.getText());
        });
    }

    @Test
    public void testSaveButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Fill in the fields with valid data
            JTextField nameField = findTextFieldByBounds(addProductFrame, 140, 60);
            nameField.setText("Test Product");
            JTextField caloriesField = findTextFieldByBounds(addProductFrame, 140, 100);
            caloriesField.setText("100");
            JTextField proteinField = findTextFieldByBounds(addProductFrame, 140, 140);
            proteinField.setText("10");
            JTextField carbsField = findTextFieldByBounds(addProductFrame, 140, 180);
            carbsField.setText("20");
            JTextField fatField = findTextFieldByBounds(addProductFrame, 140, 220);
            fatField.setText("5");

            JButton saveButton = findButton(addProductFrame, "Save");
            assertNotNull("Save button should exist", saveButton);
            saveButton.doClick();

            // Assert that the frame is closed after saving
            assertFalse("Frame should be closed after saving", addProductFrame.isVisible());
        });
    }

    @Test
    public void testBackButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton backButton = findButton(addProductFrame, "Back");
            assertNotNull("Back button should exist", backButton);
            backButton.doClick();
            assertFalse("Frame should be closed after back", addProductFrame.isVisible());
        });
    }

    // Helper method to find a text field by its bounds (since setName yok)
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