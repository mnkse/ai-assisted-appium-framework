package com.mobileaiautomation.tests;

import com.mobileaiautomation.config.TestConfig;
import com.mobileaiautomation.driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class BaseTest {
    protected TestConfig config;
    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        config = new TestConfig();
        driver = DriverFactory.create(config);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        boolean testFailed = !result.isSuccess();
        RuntimeException cleanupFailure = null;
        try {
            if (!result.isSuccess() && driver != null) {
                captureFailureScreenshot(result.getName());
            }
        } catch (IOException | RuntimeException e) {
            logFailure("Unable to capture failure screenshot for " + result.getName(), e);
        } finally {
            try {
                if (driver != null) {
                    driver.quit();
                }
            } catch (RuntimeException e) {
                cleanupFailure = new RuntimeException("Unable to close Appium driver", e);
                logFailure(cleanupFailure.getMessage(), e);
            } finally {
                driver = null;
            }
        }
        if (!testFailed && cleanupFailure != null) {
            throw cleanupFailure;
        }
    }

    private void logFailure(String message, Throwable failure) {
        StringWriter details = new StringWriter();
        failure.printStackTrace(new PrintWriter(details));
        Reporter.log(message + System.lineSeparator() + details, true);
    }

    private void captureFailureScreenshot(String testName) throws IOException {
        Path screenshotDirectory = Path.of("target", "screenshots");
        Files.createDirectories(screenshotDirectory);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS"));
        Path destination = screenshotDirectory.resolve(testName + "-" + timestamp + ".png");
        Path source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        Reporter.log("Failure screenshot: " + destination.toAbsolutePath(), true);
    }
}
