package com.samet.erdem.tracker.manager;

import com.samet.erdem.tracker.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * @class ProductManager
 * @brief Manages product (food) items in memory.
 * @details Temporary list of products used by AddProductFrame and AllProductsFrame.
 */
public class ProductManager {

    /**
     * @brief List that holds all added products in memory.
     */
    private static List<Product> productList = new ArrayList<>();

    /**
     * @brief Adds a product to the list.
     * @param product The product to add.
     */
    public static void addProduct(Product product) {
        productList.add(product);
    }

    /**
     * @brief Returns all products in the list.
     * @return List of all products.
     */
    public static List<Product> getAllProducts() {
        return productList;
    }

    /**
     * @brief Clears all products from the list.
     */
    public static void clearProducts() {
        productList.clear();
    }
}
