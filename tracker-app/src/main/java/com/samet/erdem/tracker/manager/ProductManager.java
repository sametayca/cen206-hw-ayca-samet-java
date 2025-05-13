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
    private static List<Product> productList = new ArrayList<>();

    public static void addProduct(Product product) {
        productList.add(product);
    }

    public static List<Product> getAllProducts() {
        return productList;
    }

    public static void clearProducts() {
        productList.clear();
    }
}
