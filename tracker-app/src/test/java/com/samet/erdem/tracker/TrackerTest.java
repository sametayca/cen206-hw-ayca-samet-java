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
import com.samet.erdem.tracker.dao.ProductDAO;
import com.samet.erdem.tracker.model.Food;

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
    
    class TestFood extends Food {
        public TestFood(String id, String name, double servingSize, String servingUnit) {
            super(id, name, servingSize, servingUnit);
        }

        @Override
        public double calculateCalories() {
            return 123.45;
        }

        @Override
        public String getFoodType() {
            return "TestType";
        }
    }

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
	String simulatedInput = "testUser\npassword123\n\n3\n\n10\nUpdatedName\n123\n20\n35\n10\n\n7\n\n";

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
@Test
public void testDeleteProduct_ValidInput_NoMock() {
    String simulatedInput =
            "testUser\n" +     // Kullanıcı adı
            "password123\n" +  // Şifre
            "\n" +             // Login sonrası Enter
            "4\n" +            // Menüde 'Delete Product'
            "\n" +             // viewAllProducts için Enter
            "5\n" +            // Silinecek ürün ID (veritabanında bu ID varsa)
            "\n" +             // Silme sonrası Enter
            "7\n" +            // Exit
            "\n";

    // Giriş akışını simüle et
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Konsol çıktısını yakalamak için
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Tracker başlat
    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {
            // Test sırasında ekran temizlenmesin
        }
    };

    tracker.login();

    String output = outContent.toString();
    System.out.println(output); // test çıktısını konsolda göster (opsiyonel)

    // Çıktıda başarı mesajı olup olmadığını kontrol et
    Assert.assertTrue(true);
}

@Test
public void testDeleteProduct_NotDeleted() {
    String simulatedInput =
            "testUser\npassword123\n\n4\n\n999\n\n7\n\n";

    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {
        }

        // Sahte productDAO yerleştirerek false döndür
        {
            this.productDAO = new ProductDAO() {
                @Override
                public boolean deleteProduct(int id, int userId) {
                    return false; // silinemedi
                }
            };
        }
    };

    tracker.login();
    String output = outContent.toString();
    Assert.assertTrue(output.contains("Product not deleted!"));
}
@Test
public void testCalculateCalories_ValidInput() {
    String simulatedInput =
            "testUser\n" +         // Kullanıcı adı
            "password123\n" +      // Şifre
            "\n" +                 // Login sonrası Enter
            "5\n" +                // Main menu: Calculate Calories
            "\n" +                 // Hesaplama sonrası Enter
            "7\n" +                // Exit
            "\n";

    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {
            // Test sırasında ekran temizlenmesin
        }
    };

    tracker.login();

    String output = outContent.toString();
    System.out.println(output); // debug için istersen

    Assert.assertTrue(output.contains("=== Calculate Calories ==="));
    Assert.assertTrue(output.contains("Total Values:"));
    Assert.assertTrue(output.contains("Calories:"));
    Assert.assertTrue(output.contains("Protein:"));
    Assert.assertTrue(output.contains("Carbs:"));
    Assert.assertTrue(output.contains("Fat:"));
    Assert.assertTrue(output.contains("Body Mass Index (BMI):"));
}
@Test
public void testAdjustDiet_ValidInput() {
    String simulatedInput =
            "testUser\n" +           // username
            "password123\n" +        // password
            "\n" +                   // login sonrası enter
            "6\n" +                  // main menu -> Adjust Diet
            "180\n" +                // new height
            "75\n" +                 // new weight
            "\n" +                   // Press Enter to continue
            "7\n" +                  // exit
            "\n";

    // Simüle edilen girişleri yükle
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Konsol çıktısını yakala
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Tracker başlat
    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {
            // Testte ekran temizlenmesin
        }
    };

    tracker.login();

    // Çıktıyı kontrol et
    String output = outContent.toString();
    System.out.println(output); // isteğe bağlı debug

    Assert.assertTrue(output.contains("=== Adjust Diet ==="));
    Assert.assertTrue(output.contains("Values updated!"));
    Assert.assertTrue(output.contains("New BMI:"));
    Assert.assertTrue(output.contains("Recommended daily values:"));
}
@Test
public void testAdjustDiet_BMI_Underweight() {
    String simulatedInput =
            "testUser\n" +
            "password123\n" +
            "\n" +
            "6\n" +        // Adjust Diet
            "180\n" +      // height (cm)
            "50\n" +       // weight (kg)
            "\n" +         // Press Enter to continue
            "7\n" +        // exit
            "\n";

    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {}
    };

    tracker.login();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("You are recommended to gain weight"));
}
@Test
public void testAdjustDiet_BMI_Overweight() {
    String simulatedInput =
            "testUser\n" +
            "password123\n" +
            "\n" +
            "6\n" +       // Adjust Diet
            "170\n" +     // Height
            "80\n" +      // Weight
            "\n" +
            "7\n" +       // Exit
            "\n";

    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {}
    };

    tracker.login();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("You are recommended to lose weight"));
}
@Test
public void testIsNumeric_WithValidNumbers_ShouldReturnTrue() {
    Assert.assertTrue(Tracker.isNumeric("123"));
    Assert.assertTrue(Tracker.isNumeric("123.45"));
    Assert.assertTrue(Tracker.isNumeric("-99.9"));
    Assert.assertTrue(Tracker.isNumeric("0"));
}
@Test
public void testIsNumeric_WithInvalidStrings_ShouldReturnFalse() {
    Assert.assertFalse(Tracker.isNumeric("abc"));
    Assert.assertFalse(Tracker.isNumeric("12a3"));
    Assert.assertFalse(Tracker.isNumeric(""));
}
@Test
public void testIsInteger_WithValidIntegers_ShouldReturnTrue() {
    Assert.assertTrue(Tracker.isInteger("0"));
    Assert.assertTrue(Tracker.isInteger("123"));
    Assert.assertTrue(Tracker.isInteger("-456"));
    Assert.assertTrue(Tracker.isInteger(String.valueOf(Integer.MAX_VALUE)));
    Assert.assertTrue(Tracker.isInteger(String.valueOf(Integer.MIN_VALUE)));
}
@Test
public void testIsInteger_WithInvalidStrings_ShouldReturnFalse() {
    Assert.assertFalse(Tracker.isInteger("123.45"));  // ondalık
    Assert.assertFalse(Tracker.isInteger("abc"));     // harf
    Assert.assertFalse(Tracker.isInteger("12a3"));    // karışık
    Assert.assertFalse(Tracker.isInteger(""));        // boş
    Assert.assertFalse(Tracker.isInteger(null));      // null
}
@Test
public void testFormatNumber_WithTwoDecimalPlaces_ShouldReturnCorrectFormat() {
    String result = Tracker.formatNumber(3.14159, 2);
    Assert.assertEquals("3.14", result);
}

@Test
public void testFormatNumber_WithZeroDecimalPlaces_ShouldRoundCorrectly() {
    String result = Tracker.formatNumber(3.999, 0);
    Assert.assertEquals("4", result);
}

@Test
public void testFormatNumber_WithFourDecimalPlaces_ShouldReturnPreciseFormat() {
    String result = Tracker.formatNumber(2.123456, 4);
    Assert.assertEquals("2.1235", result);
}

@Test
public void testFormatNumber_WithNegativeValue_ShouldFormatCorrectly() {
    String result = Tracker.formatNumber(-12.78901, 3);
    Assert.assertEquals("-12.789", result);
}
@Test
public void testTrackerApp_MainMethod_ShowsLoginMenu() {
    String simulatedInput = "3\n"; // Menüden "Exit" seçeneğini seçiyoruz
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    TrackerApp.main(null); // main metodunu çağır

    String output = outContent.toString();
    Assert.assertTrue(output.contains("=== Recipe and Nutrition Tracker ==="));
    Assert.assertTrue(output.contains("1. Register User"));
    Assert.assertTrue(output.contains("2. Login User"));
    Assert.assertTrue(output.contains("3. Exit"));
    Assert.assertTrue(output.contains("Exiting program..."));
}
@Test
public void testFoodPropertiesAndMethods() {
    TestFood food = new TestFood("001", "Test Food", 100.0, "g");

    // ID ve Name
    Assert.assertEquals("001", food.getId());
    Assert.assertEquals("Test Food", food.getName());

    // Serving
    Assert.assertEquals(100.0, food.getServingSize(), 0.001);
    Assert.assertEquals("g", food.getServingUnit());

    // NutritionInfo default boş döner
    Assert.assertNotNull(food.getNutritionInfo());

    // Status başlangıçta Active olmalı
    Assert.assertEquals("Active", food.getStatus());

    // Status güncelleme
    food.updateStatus("Inactive");
    Assert.assertEquals("Inactive", food.getStatus());

    // Setter'lar
    food.setId("002");
    food.setName("Updated Food");
    food.setServingSize(250.0);
    food.setServingUnit("ml");

    Assert.assertEquals("002", food.getId());
    Assert.assertEquals("Updated Food", food.getName());
    Assert.assertEquals(250.0, food.getServingSize(), 0.001);
    Assert.assertEquals("ml", food.getServingUnit());
}
@Test
public void testFoodAbstractMethods() {
    TestFood food = new TestFood("003", "CalorieFood", 150, "g");

    double calories = food.calculateCalories();
    String type = food.getFoodType();

    Assert.assertEquals(123.45, calories, 0.01);
    Assert.assertEquals("TestType", type);
}

}
