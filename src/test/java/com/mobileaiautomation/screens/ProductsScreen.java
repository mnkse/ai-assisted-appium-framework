package com.mobileaiautomation.screens;

import com.mobileaiautomation.config.TestConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public final class ProductsScreen extends BaseScreen {
    private static final By PRODUCTS_TITLE = AppiumBy.accessibilityId("title");
    private static final By PRODUCTS_LIST = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productRV");
    private static final By MENU = AppiumBy.accessibilityId("View menu");
    private static final By PRODUCTS_MENU_ITEM = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"Catalog\")");
    private static final String PRODUCT_TITLE_ID = "com.saucelabs.mydemoapp.android:id/titleTV";
    private static final String PRODUCT_IMAGE_DESCRIPTION = "Product Image";

    public ProductsScreen(AndroidDriver driver, TestConfig config) {
        super(driver, config);
    }

    public void open() {
        wait.until(currentDriver -> isProductsVisible() || !currentDriver.findElements(MENU).isEmpty());
        if (isProductsVisible()) {
            return;
        }
        clickWhenClickable(MENU);
        clickWhenClickable(PRODUCTS_MENU_ITEM);
        wait.until(currentDriver -> isProductsVisible());
    }

    public void selectProduct(String exactProductName) {
        By titleLocator = AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + exactProductName + "\")");
        if (driver.findElements(titleLocator).isEmpty()) {
            By scrollToTitle = AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().text(\"" + exactProductName + "\"))");
            driver.findElement(scrollToTitle);
        }

        By productImage = AppiumBy.xpath(
                "//android.widget.TextView[@text=\"" + exactProductName + "\" and @resource-id=\"" +
                        PRODUCT_TITLE_ID + "\"]/preceding-sibling::android.widget.ImageView[" +
                        "@content-desc=\"" + PRODUCT_IMAGE_DESCRIPTION + "\"]");
        clickWhenClickable(productImage);
    }

    private boolean isProductsVisible() {
        var titleElements = driver.findElements(PRODUCTS_TITLE);
        var listElements = driver.findElements(PRODUCTS_LIST);
        return titleElements.stream().anyMatch(element -> element.isDisplayed()
                && "Products".equals(element.getText()))
            && listElements.stream().anyMatch(WebElement::isDisplayed);
    }
}
