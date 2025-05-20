package com.samet.erdem.tracker.manager;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.samet.erdem.tracker.model.Product;
import java.util.List;

public class ProductManagerTest {
    @Before
    public void setUp() {
        ProductManager.clearProducts();
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Apple", 52, 0.3, 14, 0.2, 1);
        ProductManager.addProduct(product);
        List<Product> products = ProductManager.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("Apple", products.get(0).getName());
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product("Apple", 52, 0.3, 14, 0.2, 1);
        Product product2 = new Product("Banana", 89, 1.1, 23, 0.3, 1);
        ProductManager.addProduct(product1);
        ProductManager.addProduct(product2);
        List<Product> products = ProductManager.getAllProducts();
        assertEquals(2, products.size());
        assertEquals("Apple", products.get(0).getName());
        assertEquals("Banana", products.get(1).getName());
    }

    @Test
    public void testClearProducts() {
        Product product = new Product("Apple", 52, 0.3, 14, 0.2, 1);
        ProductManager.addProduct(product);
        ProductManager.clearProducts();
        List<Product> products = ProductManager.getAllProducts();
        assertTrue(products.isEmpty());
    }
} 