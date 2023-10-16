package com.automation.utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverUtils {

    private static WebDriver driver;

    public static void createLocalDriver() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    // This method is used to run tests on AWS instances
    public static void createRemoteDriver(String browser, String browserVersion, String platform) {
        MutableCapabilities options = null;

        try {
            if(browser.equalsIgnoreCase("chrome")) {
                options = new ChromeOptions();
            } else if(browser.equalsIgnoreCase("firefox")) {
                options = new FirefoxOptions();
            } else if(browser.equalsIgnoreCase("MicrosoftEdge")) {
                options = new EdgeOptions();
            } else {
                System.out.println("Invalid browser");
            }

            options.setCapability("browserName", browser);
            options.setCapability("browserVersion", browserVersion);
            options.setCapability("platformName", platform);
            driver = new RemoteWebDriver(new URL("http://3.144.187.136:4444/wd/hub"), options);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
