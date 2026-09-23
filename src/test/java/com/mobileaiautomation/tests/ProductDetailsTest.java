package com.mobileaiautomation.tests;

import com.mobileaiautomation.screens.ProductDetailsScreen;
import com.mobileaiautomation.screens.ProductsScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class ProductDetailsTest extends BaseTest {
    private static final String PRODUCT_NAME = "Sauce Labs Backpack";

    @Test(groups = "smoke", description = "MOB-002 - open the exact product and verify its details")
    public void exactProductDetailsAreVisible() {
        ProductsScreen products = new ProductsScreen(driver, config);
        products.open();
        products.selectProduct(PRODUCT_NAME);

        ProductDetailsScreen details = new ProductDetailsScreen(driver, config);
        Assert.assertEquals(details.productName(), PRODUCT_NAME,
                "The detail screen must match the selected product name");
        Assert.assertTrue(details.isAddToCartVisible(),
                "The Add to cart control must be visible on product details");
    }
}
