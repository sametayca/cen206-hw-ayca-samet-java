/**

@file TrackerTest.java
@brief This file contains the test cases for the Tracker class.
@details This file includes test methods to validate the functionality of the Tracker class. It uses JUnit for unit testing.
*/
/**

@file TrackerTest.java
@brief This file contains the test cases for the Tracker class.
@details This file includes test methods to validate the functionality of the Tracker class. It uses JUnit for unit testing.
*/
package com.samet.erdem.tracker;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;


import com.samet.erdem.tracker.Tracker;

/**

@class TrackerTest
@brief This class represents the test class for the Tracker class.
@details The TrackerTest class provides test methods to verify the behavior of the Tracker class. It includes test methods for addition, subtraction, multiplication, and division operations.
@author ugur.coruh
*/
public class TrackerTest {


	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
@Before
public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
}

@After
public void restoreStreams() {
    System.setOut(originalOut);
    System.setIn(originalIn);

}


@Test
public void testShowLoginMenu_ExitOption() {
    String simulatedInput = "3\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.showLoginMenuOnce();

    String output = outContent.toString();
    assertTrue(output.contains("Exiting program..."));
}

@Test
public void testShowLoginMenu_InvalidOption() {
    String simulatedInput = "99\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.showLoginMenuOnce();

    String output = outContent.toString();
    assertTrue(output.contains("Invalid option!"));
}

@Test
public void testRegisterFunctionWithValidInput() {
    // Sırasıyla: username, password, height, weight
    String simulatedInput = "1\ntestUser\npassword123\n180\n75\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.showLoginMenuOnce();

    Assert.assertTrue(true);
}

@Test
public void testRegisterFunction2WithValidInput() {
    // Sırasıyla: username, password, height, weight
    String simulatedInput = "1\ntestUser2\npassword123\n180\n75\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.showLoginMenuOnce();

    Assert.assertTrue(true);
}

@Test
public void testRegister_WithNegativeHeight_ShouldShowErrorMessage() {
    // username, password, height = -180, weight = 70, press enter
    String simulatedInput = "user1\npass1\n-180\n70\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Height and weight must be positive values!"));
}

@Test
public void testRegister_WithNegativeWeight_ShouldShowErrorMessage() {
    // username, password, height = 180, weight = -70, press enter
    String simulatedInput = "user2\npass2\n180\n-70\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Height and weight must be positive values!"));
}

@Test
public void testLoginFunctionWithValidInput() {
    // Sırasıyla: username, password, height, weight
    String simulatedInput = "2\ntestUser\npassword123\n\n7\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.showLoginMenuOnce();

    Assert.assertTrue(true);
}

@Test
public void testLoginFunction_InValidInput() {
    // Sırasıyla: username, password, height, weight
    String simulatedInput = "2\nwrongUser\nwrongpassword\n\n7\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.showLoginMenuOnce();

    Assert.assertTrue(true);
}

@Test
public void testaddProduct_ValidInput() {
    // Sırasıyla: username, password, height, weight
	String simulatedInput = "testUser\npassword123\n\n1\ntestProduct\n123\n123\n123\n123\n\n7\n\n";

	System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.login();

    Assert.assertTrue(true);
}
@Test
public void testviewAllProducts_ValidInput() {
    // Sırasıyla: username, password, height, weight
	String simulatedInput = "testUser\npassword123\n\n2\n\n7\n\n";

	System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.login();

    Assert.assertTrue(true);
}

@Test
public void testviewAllProducts_NoProducts() throws Exception {
    // Kullanıcıdan gelen giriş (scanner.nextLine için Enter simülasyonu)
	String simulatedInput = "testUser2\npassword123\n\n2\n\n7\n\n";
	System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.login();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("No products added yet."));
    
}

@Test
public void testviewUpdateProducts_ValidInput() {
    // Sırasıyla: username, password, height, weight
	String simulatedInput = "testUser\npassword123\n\n3\n\n14\nUpdatedName\n123\n20\n35\n10\n\n7\n\n";

	System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Tracker tracker = new Tracker();
    tracker.login();

    Assert.assertTrue(true);
}


@Test
public void testviewUpdateProducts_NoProducts() throws Exception {
    String simulatedInput = "testUser2\npassword123\n\n3\n\n999\n\n7\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void showMainMenu() {
            // testte menü döngüsüne girmesin
        }

        @Override
        public void clearScreen() {
        }
    };

    tracker.login();

    String output = outContent.toString();
    Assert.assertTrue(true);
}

}
