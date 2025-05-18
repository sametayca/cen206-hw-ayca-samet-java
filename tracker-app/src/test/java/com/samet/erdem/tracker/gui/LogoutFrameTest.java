package com.samet.erdem.tracker.gui;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class LogoutFrameTest {
    private LogoutFrame logoutFrame;
    private JFrame parentFrame;

    @Before
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            parentFrame = new JFrame();
            parentFrame.setVisible(true);
            logoutFrame = new LogoutFrame(parentFrame);
            logoutFrame.setVisible(true);
        });
    }

    @Test
    public void testLogoutFrameInitialization() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull("LogoutFrame should not be null", logoutFrame);
            assertTrue("LogoutFrame should be visible", logoutFrame.isVisible());
            assertEquals("LogoutFrame title should match", "Logout Confirmation", logoutFrame.getTitle());
        });
    }

    @Test
    public void testYesButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton yesButton = findButton(logoutFrame, "Yes");
            assertNotNull("Yes button should exist", yesButton);
            yesButton.doClick();
            assertFalse("Frame should be closed after clicking yes", logoutFrame.isVisible());
            assertFalse("Parent frame should be closed after logout", parentFrame.isVisible());
        });
    }

    @Test
    public void testNoButton() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton noButton = findButton(logoutFrame, "No");
            assertNotNull("No button should exist", noButton);
            noButton.doClick();
            assertFalse("Frame should be closed after clicking no", logoutFrame.isVisible());
            assertTrue("Parent frame should remain visible after clicking no", parentFrame.isVisible());
        });
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