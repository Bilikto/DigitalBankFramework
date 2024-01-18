package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class WebDriverManager {

    private WebDriverManager() {}

    // Uncomment it if you need convert it to a Singleton pattern design!
//    private static BrowserFactory instance = null;
//
//    public static BrowserFactory getInstance() {
//        if(instance == null) {
//            instance = new BrowserFactory();
//        }
//        return instance;
//    }

   private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<>() {};

    public static void setDriver(WebDriver driver) {
        threadedDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return threadedDriver.get();
    }

    public static void removeDriver() {
        getDriver().quit();
        threadedDriver.remove();
    }

}
