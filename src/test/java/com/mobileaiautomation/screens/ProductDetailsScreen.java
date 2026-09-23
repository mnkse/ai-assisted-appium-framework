package com.mobileaiautomation.screens;

import com.mobileaiautomation.config.TestConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public final class ProductDetailsScreen extends BaseScreen {
    private static final By PRODUCT_NAME = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By ADD_TO_CART = AppiumBy.accessibilityId("Tap to add product to cart");

    public ProductDetailsScreen(AndroidDriver driver, TestConfig config) {
        super(driver, config);
    }

    public String productName() {
        return waitForVisible(PRODUCT_NAME).getText();
    }

    public boolean isAddToCartVisible() {
        return waitForVisible(ADD_TO_CART).isDisplayed();
    }
}
