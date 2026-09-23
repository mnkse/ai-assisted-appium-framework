# Mobile AI Automation

A minimal Java 21, Maven, Appium, and TestNG framework for Sauce Labs My Demo App.

## Pinned versions

- Java: 21
- Maven: 3.9.16
- Appium Java Client: 10.1.1
- Selenium Java: 4.43.0
- TestNG: 7.11.0
- Maven Compiler Plugin: 3.14.0
- Maven Surefire Plugin: 3.5.3

Appium Java Client 10.1.1 lists Selenium 4.42.0 and 4.43.0 in its official compatibility matrix. The project pins Selenium 4.43.0 explicitly.

## Runtime prerequisites

- Appium server at `http://127.0.0.1:4723/`
- Android device `emulator-5554`
- UiAutomator2 driver installed in the Appium server
- Sauce Labs My Demo App installed

The app package and launcher activity were verified on the device:

- Package: `com.saucelabs.mydemoapp.android`
- Activity: `com.saucelabs.mydemoapp.android.view.activities.SplashActivity`

## Run

Keep the Appium server and emulator running. End any existing Appium session for `emulator-5554` before starting Maven.

```powershell
& "$env:LOCALAPPDATA\Programs\ApacheMaven\apache-maven-3.9.16\bin\mvn.cmd" test-compile
& "$env:LOCALAPPDATA\Programs\ApacheMaven\apache-maven-3.9.16\bin\mvn.cmd" -Dtest=ProductDetailsTest test
```

Settings in `src/test/resources/test.properties` can be overridden with system properties, for example:

```powershell
mvn -Dappium.udid=emulator-5554 -Dwait.timeout.seconds=20 test
```

Screenshots from failed tests are written to `target/screenshots`. Maven output is written under `target`.

## Scope

`MOB-002` verifies that the exact product `Sauce Labs Backpack` opens from Products, that the detail name matches, and that Add to cart is visible. It does not add a product to the cart and does not enter checkout.
