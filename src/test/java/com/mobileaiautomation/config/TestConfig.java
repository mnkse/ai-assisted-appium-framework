package com.mobileaiautomation.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public final class TestConfig {
    private static final String RESOURCE = "/test.properties";
    private final Properties properties = new Properties();

    public TestConfig() {
        try (InputStream input = TestConfig.class.getResourceAsStream(RESOURCE)) {
            if (input == null) {
                throw new IllegalStateException("Missing test resource: " + RESOURCE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load " + RESOURCE, e);
        }
    }

    public String serverUrl() {
        return required("appium.server.url");
    }

    public URL serverUrlAsUrl() {
        try {
            return new URL(serverUrl());
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium server URL", e);
        }
    }

    public String platformName() {
        return required("appium.platform.name");
    }

    public String automationName() {
        return required("appium.automation.name");
    }

    public String deviceName() {
        return required("appium.device.name");
    }

    public String udid() {
        return required("appium.udid");
    }

    public boolean noReset() {
        return Boolean.parseBoolean(required("appium.no.reset"));
    }

    public String appPackage() {
        return required("appium.app.package");
    }

    public String appActivity() {
        return required("appium.app.activity");
    }

    public long waitTimeoutSeconds() {
        return Long.parseLong(required("wait.timeout.seconds"));
    }

    private String required(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = properties.getProperty(key);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing configuration: " + key);
        }
        return value.trim();
    }
}
