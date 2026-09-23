package com.mobileaiautomation.driver;

import com.mobileaiautomation.config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static AndroidDriver create(TestConfig config) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(config.platformName())
                .setAutomationName(config.automationName())
                .setDeviceName(config.deviceName())
                .setUdid(config.udid())
                .setNoReset(config.noReset())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity());
        return new AndroidDriver(config.serverUrlAsUrl(), options);
    }
}
