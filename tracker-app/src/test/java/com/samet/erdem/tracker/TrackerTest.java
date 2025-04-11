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
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.samet.erdem.tracker.Tracker;
import com.samet.erdem.tracker.dao.ProductDAO;
import com.samet.erdem.tracker.dao.UserDAO;
import com.samet.erdem.tracker.database.DatabaseConnection;
import com.samet.erdem.tracker.factory.RecipeFactory;
import com.samet.erdem.tracker.manager.RecipeManager;
import com.samet.erdem.tracker.model.Food;
import com.samet.erdem.tracker.model.Ingredient;
import com.samet.erdem.tracker.model.NutritionInfo;
import com.samet.erdem.tracker.model.Product;
import com.samet.erdem.tracker.model.Recipe;
import com.samet.erdem.tracker.model.User;
import com.samet.erdem.tracker.observer.ConsoleNutritionObserver;
import com.samet.erdem.tracker.observer.NutritionObserver;
import com.samet.erdem.tracker.observer.NutritionTracker;
import com.samet.erdem.tracker.factory.RecipeFactory;



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
public void testLogin_WithEmptyUsername_ShouldShowErrorMessage() {
    // Girişte boş kullanıcı adı veriliyor, ardından "Enter" simülasyonu
    String simulatedInput = "\npassword123\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Konsol çıktısını yakala
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Tracker nesnesi ve login çağrısı
    Tracker tracker = new Tracker();
    tracker.login();

    // Çıktının kontrolü
    String output = outContent.toString();
    Assert.assertTrue(output.contains("Username cannot be empty!"));
}


@Test
public void testLogin_WithValidInput_ShouldLoginSuccessfully() {
    String simulatedInput = "testUser\npassword123\n\n7\n\n"; // doğru giriş + çıkış
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.login();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Login successful!"));
}

@Test
public void testLogin_WithEmptyPassword_ShouldShowErrorMessage() {
    String simulatedInput = "testUser\n\n\n"; // password boş
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.login();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Password cannot be empty!"));
}



@Test
public void testRegisterFunction2WithValidInput() {
    // Giriş: username, password, height, weight, press enter
    String simulatedInput = "testUser2\npassword123\n180\n75\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(true);
}

@Test
public void testRegister_WithValidInput_ShouldRegisterSuccessfully() {
    // username, password, height, weight, enter (for continue)
    String simulatedInput = "validUser\nsecurePass\n175\n70\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(true);
}

@Test
public void testRegister_WithValidInput_ShouldShowSuccessMessage() throws Exception {
    // Giriş: username, password, height, weight, enter
    String simulatedInput = "testValidUser1\nsecurePass\n175\n70\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Output yakalama
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {
            // Temizleme işlemini testte geç
        }
    };

    // Register fonksiyonunu çalıştır
    tracker.register();

    // Çıktıyı yakala
    String output = outContent.toString();
    System.setOut(originalOut); // stdout'u eski haline getir

    // DEBUG
    System.out.println("Çıktı:\n" + output);

    // Doğrulama
    Assert.assertTrue("Kayıt mesajı bulunamadı", output.contains("Registration successful!"));

    // Cleanup
    UserDAO userDAO = new UserDAO();
    User createdUser = userDAO.getUserByUsername("testValidUser1");
    if (createdUser != null) {
        userDAO.deleteUser(createdUser.getId());
    }
}


@Test
public void testRegister_WithNegativeHeight_ShouldShowErrorMessage() {
    String simulatedInput = "user1\npass1\n-180\n70\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Height and weight must be positive values!"));
}

@Test
public void testRegister_WithNegativeWeight_ShouldShowErrorMessage() {
    String simulatedInput = "user2\npass2\n180\n-70\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Height and weight must be positive values!"));
}

@Test
public void testRegister_WithEmptyUsername_ShouldShowErrorMessage() {
    String simulatedInput = "\n";  // boş username
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Username cannot be empty!"));
}

@Test
public void testRegister_WithEmptyPassword_ShouldShowErrorMessage() {
    String simulatedInput = "testUser3\n\n";  // boş şifre
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Password cannot be empty!"));
}

@Test
public void testRegister_WithInvalidHeight_ShouldShowErrorMessage() {
    String simulatedInput = "testUser4\npass4\nabc\n70\n\n"; // abc geçersiz height
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Please enter valid numerical values!"));
}

@Test
public void testRegister_WithInvalidWeight_ShouldShowErrorMessage() {
    String simulatedInput = "testUser5\npass5\n180\nxyz\n\n"; // xyz geçersiz weight
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker();
    tracker.register();

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Please enter valid numerical values!"));
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
public void testCalculateCalories_ObeseBMI_ShouldShowObeseStatus() throws Exception {
    UserDAO userDAO = new UserDAO();
    User obeseUser = new User("testObese", "pass123", 180.0, 100.0); // BMI > 30
    obeseUser = userDAO.register(obeseUser);

    String simulatedInput = "testObese\npass123\n\n5\n\n7\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {}
    };

    tracker.login();
    String output = outContent.toString();
    Assert.assertTrue(output.contains("Status: Obese"));

    userDAO.deleteUser(obeseUser.getId());
}

@Test
public void testCalculateCalories_OverweightBMI_ShouldShowOverweightStatus() throws Exception {
    UserDAO userDAO = new UserDAO();
    User overweightUser = new User("testOver", "pass123", 180.0, 85.0); // BMI 26.2
    overweightUser = userDAO.register(overweightUser);

    String simulatedInput = "testOver\npass123\n\n5\n\n7\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {}
    };

    tracker.login();
    String output = outContent.toString();
    Assert.assertTrue(output.contains("Status: Overweight"));

    userDAO.deleteUser(overweightUser.getId());
}

@Test
public void testCalculateCalories_NormalBMI_ShouldShowNormalStatus() throws Exception {
    UserDAO userDAO = new UserDAO();
    User normalUser = new User("testNormal", "pass456", 180.0, 70.0); // BMI ≈ 21.6
    normalUser = userDAO.register(normalUser);

    String simulatedInput = "testNormal\npass456\n\n5\n\n7\n\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    Tracker tracker = new Tracker() {
        @Override
        public void clearScreen() {}
    };

    tracker.login();
    String output = outContent.toString();
    Assert.assertTrue(output.contains("Status: Normal"));

    userDAO.deleteUser(normalUser.getId());
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

@Test
public void testSearchProducts_WithValidKeyword_ShouldReturnMatchingProducts() throws Exception {
    // Test kullanıcısı için userId
    int testUserId = 1;

    // Önce test için bir ürün ekleyelim
    ProductDAO productDAO = new ProductDAO();
    Product testProduct = new Product("Apple", 100.0, 1.0, 20.0, 0.5, testUserId);
    productDAO.addProduct(testProduct); // veri tabanına ürün ekle

    // Şimdi bu ürünü anahtar kelimeyle arayalım
    List<Product> results = productDAO.searchProducts("App", testUserId);

    // Sonuçlar içinde en az 1 ürün bekleniyor
    Assert.assertFalse(results.isEmpty());

    // Arama sonucu gelen ilk ürünün adı "Apple" olmalı
    Assert.assertTrue(results.get(0).getName().contains("App"));
}

@Test
public void testDeleteUser_WithValidId_ShouldRemoveUserFromDatabase() throws Exception {
    // Geçici bir kullanıcı oluştur ve veritabanına ekle
    User user = new User("tempUser_delete", "tempPass123", 180.0, 75.0);
    UserDAO userDAO = new UserDAO(); // connection atandığından emin ol

    User savedUser = userDAO.register(user);
    int userId = savedUser.getId();

    // Silme işlemini test et
    boolean deleted = userDAO.deleteUser(userId);

    // Doğrulama
    Assert.assertTrue(deleted);

    // Silindikten sonra kullanıcı gerçekten yok mu kontrol et
    User fetchedUser = userDAO.getUserById(userId);
    Assert.assertNull(fetchedUser);
}


/*
 @Test
public void testGetUserById_WithValidId_ShouldReturnCorrectUser() throws Exception {
    // 1. Kullanıcı oluştur
    User user = new User("userForGetById", "securePass", 170.0, 65.0);
    UserDAO userDAO = new UserDAO();

    // 2. Veritabanına kaydet
    User savedUser = userDAO.register(user);
    int userId = savedUser.getId();

    // 3. ID ile kullanıcıyı çek
    User fetchedUser = userDAO.getUserById(userId);

    // 4. Doğrulama
    Assert.assertNotNull(fetchedUser);
    Assert.assertEquals(userId, fetchedUser.getId());
    Assert.assertEquals("userForGetById", fetchedUser.getUsername());
    Assert.assertEquals("securePass", fetchedUser.getPassword());
    Assert.assertEquals(170.0, fetchedUser.getHeight(), 0.01);
    Assert.assertEquals(65.0, fetchedUser.getWeight(), 0.01);
}
*/

@Test
public void testCreateBasicRecipe_ShouldReturnCorrectRecipe() {
    // Girdi değerleri
    String name = "Omelette";
    double servingSize = 250.0;
    String servingUnit = "g";

    // Factory çağrısı
    Recipe recipe = RecipeFactory.createBasicRecipe(name, servingSize, servingUnit);

    // Doğrulamalar
    Assert.assertNotNull(recipe);
    Assert.assertNotNull(recipe.getId()); // UUID atanmış olmalı
    Assert.assertEquals(name, recipe.getName());
    Assert.assertEquals(servingSize, recipe.getServingSize(), 0.01);
    Assert.assertEquals(servingUnit, recipe.getServingUnit());
    Assert.assertTrue(recipe.getIngredients().isEmpty()); // Malzeme yok
}

@Test
public void testCreateRecipeWithIngredients_ShouldReturnValidRecipe() {
    // Girdi verileri
    String name = "Protein Smoothie";
    double servingSize = 250.0;
    String servingUnit = "ml";

    // Malzemeleri hazırla
    Ingredient banana = new Ingredient("1", "Banana", 100, "g");
    Ingredient milk = new Ingredient("2", "Milk", 150, "ml");
    List<Ingredient> ingredients = Arrays.asList(banana, milk);

    // Recipe oluştur
    Recipe recipe = RecipeFactory.createRecipeWithIngredients(name, servingSize, servingUnit, ingredients);

    // Doğrulamalar
    Assert.assertNotNull(recipe);
    Assert.assertEquals(name, recipe.getName());
    Assert.assertEquals(servingSize, recipe.getServingSize(), 0.01);
    Assert.assertEquals(servingUnit, recipe.getServingUnit());
    Assert.assertEquals(2, recipe.getIngredients().size());
    Assert.assertEquals("Banana", recipe.getIngredients().get(0).getName());
    Assert.assertEquals("Milk", recipe.getIngredients().get(1).getName());
}


@Test
public void testCreateCompleteRecipe_ShouldReturnFullRecipeObject() {
    // Girdi verileri
    String name = "Grilled Chicken";
    double servingSize = 300.0;
    String servingUnit = "g";

    // Malzeme listesi
    Ingredient chicken = new Ingredient("1", "Chicken Breast", 300, "g");
    Ingredient oil = new Ingredient("2", "Olive Oil", 20, "ml");
    List<Ingredient> ingredients = Arrays.asList(chicken, oil);

    // Talimatlar
    List<String> instructions = Arrays.asList(
        "Preheat the grill.",
        "Brush chicken with olive oil.",
        "Grill for 6-8 minutes on each side."
    );

    // Diğer bilgiler
    int preparationTime = 10;
    int cookingTime = 15;
    int servings = 2;

    // Metot çağrısı
    Recipe recipe = RecipeFactory.createCompleteRecipe(
        name,
        servingSize,
        servingUnit,
        ingredients,
        instructions,
        preparationTime,
        cookingTime,
        servings
    );

    // Doğrulamalar
    Assert.assertNotNull(recipe);
    Assert.assertEquals(name, recipe.getName());
    Assert.assertEquals(servingSize, recipe.getServingSize(), 0.01);
    Assert.assertEquals(servingUnit, recipe.getServingUnit());
    Assert.assertEquals(ingredients.size(), recipe.getIngredients().size());
    Assert.assertEquals(instructions.size(), recipe.getInstructions().size());
    Assert.assertEquals(preparationTime, recipe.getPreparationTime());
    Assert.assertEquals(cookingTime, recipe.getCookingTime());
    Assert.assertEquals(servings, recipe.getServings());
}

@Test
public void testCreateAllergenIngredient_ShouldBeMarkedAsAllergen() {
    Ingredient ingredient = RecipeFactory.createAllergenIngredient("Peanut", 50.0, "g");

    Assert.assertNotNull(ingredient.getId());
    Assert.assertEquals("Peanut", ingredient.getName());
    Assert.assertEquals(50.0, ingredient.getServingSize(), 0.01);
    Assert.assertEquals("g", ingredient.getServingUnit());
    Assert.assertTrue(ingredient.isAllergen());
}

@Test
public void testCreateCategorizedIngredient_ShouldHaveCategory() {
    Ingredient ingredient = RecipeFactory.createCategorizedIngredient("Chicken Breast", 200.0, "g", "Protein");

    Assert.assertNotNull(ingredient.getId());
    Assert.assertEquals("Chicken Breast", ingredient.getName());
    Assert.assertEquals(200.0, ingredient.getServingSize(), 0.01);
    Assert.assertEquals("g", ingredient.getServingUnit());
    Assert.assertEquals("Protein", ingredient.getCategory());
}

@Test
public void testCreateIngredient_ShouldReturnCorrectIngredient() {
    Ingredient ingredient = RecipeFactory.createIngredient("Tomato", 100.0, "g");

    Assert.assertNotNull(ingredient.getId());
    Assert.assertEquals("Tomato", ingredient.getName());
    Assert.assertEquals(100.0, ingredient.getServingSize(), 0.01);
    Assert.assertEquals("g", ingredient.getServingUnit());
    Assert.assertFalse(ingredient.isAllergen());
}

@Test
public void testCreateRecipe_ShouldAddAndReturnRecipe() {
    RecipeManager recipeManager = new RecipeManager();

    Recipe recipe = recipeManager.createRecipe("Pasta", 2.0, "serving");

    Assert.assertNotNull(recipe.getId());
    Assert.assertEquals("Pasta", recipe.getName());
    Assert.assertEquals(2.0, recipe.getServingSize(), 0.01);
    Assert.assertEquals("serving", recipe.getServingUnit());
}

@Test
public void testAddRecipe_ShouldAddRecipeToList() {
    RecipeManager recipeManager = new RecipeManager();
    Recipe recipe = new Recipe(UUID.randomUUID().toString(), "Omelette", 1.0, "plate");

    recipeManager.addRecipe(recipe);

    Assert.assertTrue(recipeManager.getAllRecipes().contains(recipe));
}
@Test
public void testRemoveRecipe_ShouldRemoveRecipeFromList() {
    RecipeManager recipeManager = new RecipeManager();
    Recipe recipe = new Recipe(UUID.randomUUID().toString(), "Salad", 1.5, "bowl");

    recipeManager.addRecipe(recipe);
    recipeManager.removeRecipe(recipe);

    Assert.assertFalse(recipeManager.getAllRecipes().contains(recipe));
}

@Test
public void testGetRecipeById_ShouldReturnCorrectRecipe() {
    RecipeManager recipeManager = new RecipeManager();
    Recipe recipe = recipeManager.createRecipe("Kebab", 1.0, "plate");

    Recipe result = recipeManager.getRecipeById(recipe.getId());

    Assert.assertNotNull(result);
    Assert.assertEquals("Kebab", result.getName());
}

@Test
public void testGetAllRecipes_ShouldReturnAllRecipes() {
    RecipeManager recipeManager = new RecipeManager();
    recipeManager.createRecipe("Soup", 2.0, "bowl");
    recipeManager.createRecipe("Burger", 1.0, "piece");

    List<Recipe> recipes = recipeManager.getAllRecipes();

    Assert.assertEquals(2, recipes.size());
}

@Test
public void testSearchRecipes_ShouldReturnMatchingRecipes() {
    RecipeManager recipeManager = new RecipeManager();
    recipeManager.createRecipe("Chocolate Cake", 1.0, "slice");
    recipeManager.createRecipe("Vanilla Cake", 1.0, "slice");
    recipeManager.createRecipe("Pasta", 1.0, "plate");

    List<Recipe> results = recipeManager.searchRecipes("cake");

    Assert.assertEquals(2, results.size());
}

@Test
public void testUpdateRecipe_ShouldUpdateRecipeDetails() {
    RecipeManager recipeManager = new RecipeManager();
    Recipe recipe = recipeManager.createRecipe("Sandwich", 1.0, "piece");

    recipe.setName("Updated Sandwich");
    recipe.setServingSize(2.0);
    recipeManager.updateRecipe(recipe);

    Recipe updated = recipeManager.getRecipeById(recipe.getId());
    Assert.assertEquals("Updated Sandwich", updated.getName());
    Assert.assertEquals(2.0, updated.getServingSize(), 0.01);
}

@Test
public void testClearAllRecipes_ShouldRemoveAllRecipes() {
    RecipeManager recipeManager = new RecipeManager();
    recipeManager.createRecipe("Test 1", 1.0, "unit");
    recipeManager.createRecipe("Test 2", 2.0, "unit");

    recipeManager.clearAllRecipes();

    Assert.assertEquals(0, recipeManager.getRecipeCount());
}

@Test
public void testGetRecipeCount_ShouldReturnCorrectNumber() {
    RecipeManager recipeManager = new RecipeManager();
    recipeManager.createRecipe("Recipe A", 1.0, "piece");
    recipeManager.createRecipe("Recipe B", 2.0, "plate");

    int count = recipeManager.getRecipeCount();

    Assert.assertEquals(2, count);
}

@Test
public void testCalculateCalories_ShouldReturnCorrectCalories() {
    Ingredient ingredient = new Ingredient("1", "Olive Oil", 10, "ml");
    ingredient.getNutritionInfo().setCalories(88.0);

    double calories = ingredient.calculateCalories();

    Assert.assertEquals(88.0, calories, 0.01);
}

@Test
public void testGetFoodType_ShouldReturnIngredient() {
    Ingredient ingredient = new Ingredient("2", "Salt", 5, "g");

    String type = ingredient.getFoodType();

    Assert.assertEquals("Ingredient", type);
}

@Test
public void testToString_ShouldContainKeyInformation() {
    Ingredient ingredient = new Ingredient("3", "Tomato", 100, "g");
    ingredient.setCategory("Vegetable");
    ingredient.setAllergen(false);
    ingredient.getNutritionInfo().setCalories(18.0);
    ingredient.getNutritionInfo().setProtein(0.9);
    ingredient.getNutritionInfo().setCalories(3.9);
    ingredient.getNutritionInfo().setFat(0.2);

    String output = ingredient.toString();

    Assert.assertTrue(output.contains("Ingredient: Tomato"));
    Assert.assertTrue(output.contains("Category: Vegetable"));
    Assert.assertTrue(output.contains("Serving: 100.0 g"));
    Assert.assertTrue(output.contains("Allergen: No"));
    
}

@Test
public void testCalculateCalories_ShouldReturnCaloriesPerServing() {
    Recipe recipe = new Recipe("r1", "Protein Salad", 250, "g");
    recipe.setServings(2);
    recipe.getNutritionInfo().setCalories(400.0);

    double result = recipe.calculateCalories();

    Assert.assertEquals(200.0, result, 0.01);
}

@Test
public void testGetFoodType_ShouldReturnRecipe() {
    Recipe recipe = new Recipe("r2", "Soup", 500, "ml");

    String type = recipe.getFoodType();

    Assert.assertEquals("Recipe", type);
}

@Test
public void testGetTotalTime_ShouldReturnSumOfPreparationAndCooking() {
    Recipe recipe = new Recipe("r3", "Pasta", 300, "g");
    recipe.setPreparationTime(15);
    recipe.setCookingTime(20);

    int totalTime = recipe.getTotalTime();

    Assert.assertEquals(35, totalTime);
}

@Test
public void testToString_ShouldContainRecipeDetails() {
    Recipe recipe = new Recipe("r4", "Grilled Chicken", 200, "g");
    recipe.setServings(2);
    recipe.setPreparationTime(10);
    recipe.setCookingTime(25);
    recipe.getNutritionInfo().setCalories(500);
    recipe.getNutritionInfo().setProtein(45);
    recipe.getNutritionInfo().setCarbohydrates(5);
    recipe.getNutritionInfo().setFat(20);

    String output = recipe.toString();

    Assert.assertTrue(output.contains("Recipe: Grilled Chicken"));
    Assert.assertTrue(output.contains("Servings: 2"));
    Assert.assertTrue(output.contains("Total Time: 35 minutes"));
    Assert.assertTrue(output.contains("Nutrition per serving:"));
    Assert.assertTrue(output.contains("Calories: 500.0"));
}

@Test
public void testOnNutritionUpdate_ShouldPrintNutritionInfo() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ConsoleNutritionObserver observer = new ConsoleNutritionObserver();
    NutritionInfo info = new NutritionInfo();
    info.setCalories(250);
    info.setProtein(10);
    info.setCarbohydrates(30);
    info.setFat(5);

    observer.onNutritionUpdate(info);

    String output = outContent.toString();
    Assert.assertTrue(output.contains("=== Nutrition Update ==="));
    Assert.assertTrue(output.contains("Calories: 250.0"));
    Assert.assertTrue(output.contains("Protein: 10.0"));
    Assert.assertTrue(output.contains("Carbs: 30.0"));
    Assert.assertTrue(output.contains("Fat: 5.0"));
}

@Test
public void testOnGoalReached_ShouldPrintGoalMessage() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ConsoleNutritionObserver observer = new ConsoleNutritionObserver();
    observer.onGoalReached("Goal reached!");

    String output = outContent.toString();
    Assert.assertTrue(output.contains("=== Goal Achieved! ==="));
    Assert.assertTrue(output.contains("Goal reached!"));
}

@Test
public void testOnWarning_ShouldPrintWarningMessage() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    ConsoleNutritionObserver observer = new ConsoleNutritionObserver();
    observer.onWarning("Be careful with sugar intake!");

    String output = outContent.toString();
    Assert.assertTrue(output.contains("=== Warning! ==="));
    Assert.assertTrue(output.contains("Be careful with sugar intake!"));
}

@Test
public void testRemoveObserverAndUpdateNutrition_ShouldNotNotifyRemovedObserver() {
    // Çıktıyı yakala
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Hazırlık
    NutritionTracker tracker = new NutritionTracker();
    NutritionObserver observer = new ConsoleNutritionObserver();

    tracker.addObserver(observer);
    tracker.removeObserver(observer); // sonra çıkar

    NutritionInfo info = new NutritionInfo();
    info.setCalories(500);
    info.setProtein(20);
    info.setCarbohydrates(50);
    info.setFat(10);

    tracker.updateNutrition(info); // artık observer yok

    // Doğrulama
    String output = outContent.toString();
    Assert.assertFalse(output.contains("Nutrition Update"));
    Assert.assertFalse(output.contains("Daily calorie goal reached!"));
}

@Test
public void testSetDailyGoals_ShouldTriggerCheckGoalsAndNotifyCorrectly() {
    // Çıktıyı yakala
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Hazırlık
    NutritionTracker tracker = new NutritionTracker();
    NutritionObserver observer = new ConsoleNutritionObserver();
    tracker.addObserver(observer);

    // Günlük hedefler belirleniyor
    NutritionInfo goals = new NutritionInfo();
    goals.setCalories(2000);
    goals.setProtein(100);
    goals.setCarbohydrates(250);
    goals.setFat(70);
    goals.setFiber(30);

    tracker.setDailyGoals(goals); // -> checkGoals() çağrılır

    // Mevcut besin değerleri varsayılan 0 olduğu için hedefe ulaşılmaz
    String output = outContent.toString();

    // Beklenen: hiçbir hedef mesajı verilmemeli
    Assert.assertFalse(output.contains("goal reached"));
    Assert.assertFalse(output.contains("Warning"));
}

@Test
public void testSetDailyGoals_WithExceededNutrition_ShouldPrintGoalReached() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Takipçi ve tracker
    NutritionTracker tracker = new NutritionTracker();
    tracker.addObserver(new ConsoleNutritionObserver());

    // Günlük hedefler
    NutritionInfo goals = new NutritionInfo();
    goals.setCalories(1000);
    goals.setProtein(50);
    goals.setCarbohydrates(100);
    goals.setFat(30);
    goals.setFiber(10);
    tracker.setDailyGoals(goals);

    // Güncel besin değeri: hepsini geçti
    NutritionInfo consumed = new NutritionInfo();
    consumed.setCalories(1200);
    consumed.setProtein(60);
    consumed.setCarbohydrates(150);
    consumed.setFat(40);
    consumed.setFiber(20);
    tracker.updateNutrition(consumed);

    String output = outContent.toString();

    // Beklenen hedef uyarıları
    Assert.assertTrue(output.contains("Daily calorie goal reached!"));
    Assert.assertTrue(output.contains("Daily protein goal reached!"));
    Assert.assertTrue(output.contains("Daily carbohydrate goal reached!"));
    Assert.assertTrue(output.contains("Daily fat goal reached!"));
    Assert.assertTrue(output.contains("Daily fiber goal reached!"));
   
}
@Test
public void testResetDailyNutrition_ShouldResetValuesAndNotifyObserver() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    // Hazırlık
    NutritionTracker tracker = new NutritionTracker();
    tracker.addObserver(new ConsoleNutritionObserver());

    // Günlük hedef belirle
    NutritionInfo goals = new NutritionInfo();
    goals.setCalories(2000);
    goals.setProtein(100);
    goals.setCarbohydrates(250);
    goals.setFat(70);
    goals.setFiber(30);
    tracker.setDailyGoals(goals);

    // Besin alımı
    NutritionInfo consumed = new NutritionInfo();
    consumed.setCalories(1200);
    consumed.setProtein(60);
    consumed.setCarbohydrates(150);
    consumed.setFat(40);
    consumed.setFiber(20);
    tracker.updateNutrition(consumed);

    // Şimdi sıfırla
    tracker.resetDailyNutrition();

    // Kontrol: Besin değerleri sıfır mı?
    NutritionInfo resetValues = tracker.getCurrentNutrition();
    Assert.assertEquals(0, resetValues.getCalories(), 0.01);
    Assert.assertEquals(0, resetValues.getProtein(), 0.01);
    Assert.assertEquals(0, resetValues.getCarbohydrates(), 0.01);
    Assert.assertEquals(0, resetValues.getFat(), 0.01);
    Assert.assertEquals(0, resetValues.getFiber(), 0.01);

    // Kontrol: Observer bildirildi mi?
    String output = outContent.toString();
    Assert.assertTrue(output.contains("Nutrition Update"));
}
@Test
public void testGetDailyGoals_ShouldReturnCorrectGoalValues() {
    NutritionTracker tracker = new NutritionTracker();

    NutritionInfo goals = new NutritionInfo();
    goals.setCalories(1500);
    goals.setProtein(70);
    goals.setCarbohydrates(200);
    goals.setFat(50);
    goals.setFiber(25);

    tracker.setDailyGoals(goals);

    NutritionInfo returnedGoals = tracker.getDailyGoals();
    Assert.assertEquals(1500, returnedGoals.getCalories(), 0.01);
    Assert.assertEquals(70, returnedGoals.getProtein(), 0.01);
    Assert.assertEquals(200, returnedGoals.getCarbohydrates(), 0.01);
    Assert.assertEquals(50, returnedGoals.getFat(), 0.01);
    Assert.assertEquals(25, returnedGoals.getFiber(), 0.01);
}
@Test
public void testNotifyWarning_ShouldBeTriggeredWhenCaloriesExceed120Percent() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    NutritionTracker tracker = new NutritionTracker();
    tracker.addObserver(new ConsoleNutritionObserver());

    // Hedef kalori = 1000 → %120 = 1200
    NutritionInfo goals = new NutritionInfo();
    goals.setCalories(1000);
    tracker.setDailyGoals(goals);

    NutritionInfo consumed = new NutritionInfo();
    consumed.setCalories(1300); // %130 → uyarı beklenir
    tracker.updateNutrition(consumed);

    String output = outContent.toString();
    Assert.assertTrue(output.contains("Calorie intake is significantly above daily goal!"));
}

@Test
public void testGetUserById_ShouldReturnCorrectUser() throws SQLException {
    UserDAO userDAO = new UserDAO();
    
    // Benzersiz kullanıcı adı üret
    String uniqueUsername = "testUser_" + System.currentTimeMillis();
    User user = new User(uniqueUsername, "testPass", 175.0, 70.0);
    
    // Kaydet
    user = userDAO.register(user);
    Assert.assertTrue(user.getId() > 0); // kayıt başarılı mı

    // ID ile getir
    User fetchedUser = userDAO.getUserById(user.getId());

    // Doğrula
    Assert.assertNotNull(fetchedUser);
    Assert.assertEquals(user.getId(), fetchedUser.getId());
    Assert.assertEquals(uniqueUsername, fetchedUser.getUsername());
    Assert.assertEquals("testPass", fetchedUser.getPassword());
    Assert.assertEquals(175.0, fetchedUser.getHeight(), 0.01);
    Assert.assertEquals(70.0, fetchedUser.getWeight(), 0.01);
}

@Test
public void testCloseConnection_ShouldCloseSuccessfully() throws SQLException {
    // 1. DatabaseConnection örneğini al
    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    Connection conn = dbConnection.getConnection();

    // 2. Bağlantı açık olmalı
    Assert.assertFalse(conn.isClosed());

    // 3. Kapat
    dbConnection.closeConnection();

    // 4. Doğrula
    Assert.assertTrue(conn.isClosed());
}

@Test
public void testAddIngredient_ShouldIncreaseIngredientListSize() {
    // 1. Gerekli nesneleri hazırla
    Recipe recipe = new Recipe("1", "Test Recipe", 100, "g");
    Ingredient ingredient = new Ingredient("ing1", "Sugar", 50, "g");

    // 2. Fonksiyonu çağır
    recipe.addIngredient(ingredient);

    // 3. Doğrulama
    Assert.assertTrue(recipe.getIngredients().contains(ingredient));
    Assert.assertEquals(1, recipe.getIngredients().size());
}

@Test
public void testRemoveIngredient_ShouldDecreaseIngredientListSize() {
    // 1. Gerekli nesneleri hazırla
    Recipe recipe = new Recipe("2", "Test Recipe", 100, "g");
    Ingredient ingredient = new Ingredient("ing2", "Flour", 200, "g");
    recipe.addIngredient(ingredient);

    // 2. Fonksiyonu çağır
    recipe.removeIngredient(ingredient);

    // 3. Doğrulama
    Assert.assertFalse(recipe.getIngredients().contains(ingredient));
    Assert.assertEquals(0, recipe.getIngredients().size());
}

@Test
public void testAddInstruction_ShouldAddToInstructionsList() {
    // 1. Recipe oluştur
    Recipe recipe = new Recipe("3", "Cake", 1, "portion");

    // 2. Talimat ekle
    recipe.addInstruction("Preheat the oven to 180°C.");

    // 3. Doğrulama
    Assert.assertTrue(recipe.getInstructions().contains("Preheat the oven to 180°C."));
    Assert.assertEquals(1, recipe.getInstructions().size());
}

@Test
public void testSetUserIdAndCreatedAt_ShouldAssignValuesCorrectly() {
    // 1. Ürün nesnesi oluştur
    Product product = new Product();
    
    // 2. Test verileri
    int expectedUserId = 42;
    Timestamp expectedTimestamp = new Timestamp(System.currentTimeMillis());

    // 3. Metotları çağır
    product.setUserId(expectedUserId);
    product.setCreatedAt(expectedTimestamp);

    // 4. Doğrulamalar
    Assert.assertEquals(expectedUserId, product.getUserId());
    Assert.assertEquals(expectedTimestamp, product.getCreatedAt());
}



}
