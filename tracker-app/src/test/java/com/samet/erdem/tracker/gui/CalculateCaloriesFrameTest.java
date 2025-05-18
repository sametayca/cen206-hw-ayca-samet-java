package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import com.samet.erdem.tracker.model.User;

public class CalculateCaloriesFrameTest {
    private CalculateCaloriesFrame calculateCaloriesFrame;
    private User testUser;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            testUser = new User();
            testUser.setUsername("TestUser");
            testUser.setHeight(170); // Set height for BMI calculation
            testUser.setWeight(70);  // Set weight for BMI calculation
            calculateCaloriesFrame = new CalculateCaloriesFrame(testUser);
            calculateCaloriesFrame.setVisible(true);
        });
    }

    @Test
    public void testCalculateCaloriesFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("CalculateCaloriesFrame should not be null", calculateCaloriesFrame);
            assertTrue("CalculateCaloriesFrame should be visible", calculateCaloriesFrame.isVisible());
            assertEquals("CalculateCaloriesFrame title should match", "Calculate Calories", calculateCaloriesFrame.getTitle());
        });
    }

    @Test
    public void testLabels() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JLabel caloriesLabel = findLabel(calculateCaloriesFrame, "Calories: ");
            assertNotNull("Calories label should exist", caloriesLabel);
            assertTrue("Calories label should be visible", caloriesLabel.isVisible());

            JLabel proteinLabel = findLabel(calculateCaloriesFrame, "Protein: ");
            assertNotNull("Protein label should exist", proteinLabel);
            assertTrue("Protein label should be visible", proteinLabel.isVisible());

            JLabel carbsLabel = findLabel(calculateCaloriesFrame, "Carbs: ");
            assertNotNull("Carbs label should exist", carbsLabel);
            assertTrue("Carbs label should be visible", carbsLabel.isVisible());

            JLabel fatLabel = findLabel(calculateCaloriesFrame, "Fat: ");
            assertNotNull("Fat label should exist", fatLabel);
            assertTrue("Fat label should be visible", fatLabel.isVisible());

            JLabel bmiLabel = findLabel(calculateCaloriesFrame, "BMI: ");
            assertNotNull("BMI label should exist", bmiLabel);
            assertTrue("BMI label should be visible", bmiLabel.isVisible());

            JLabel statusLabel = findLabel(calculateCaloriesFrame, "Status: ");
            assertNotNull("Status label should exist", statusLabel);
            assertTrue("Status label should be visible", statusLabel.isVisible());
        });
    }

    @Test
    public void testBackButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton backButton = findButton(calculateCaloriesFrame, "Back");
            assertNotNull("Back button should exist", backButton);
            backButton.doClick();
            assertFalse("Frame should be closed after clicking back", calculateCaloriesFrame.isVisible());
        });
    }

    // Helper method to find a label by its text
    private JLabel findLabel(JFrame frame, String text) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().startsWith(text)) {
                return (JLabel) comp;
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