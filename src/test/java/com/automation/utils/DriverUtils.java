package com.automation.utils;

import lombok.extern.java.Log;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

/*I have implemented a WebDriverManager class in order to use ThreadLocal class which maps (or manage) the driver and allows run parallel execution.
* It is very easy to convert that class to Singleton pattern! */

@Log
public final class DriverUtils {
    //It protects the class to be instantiated
    private DriverUtils() {}

    private static String env = ConfigFileReaderUtils.getProperty("test.level");
    private static String browserType = ConfigFileReaderUtils.getProperty("run.browser");
    private static String browserVersion = ConfigFileReaderUtils.getProperty("run.browserVersion");
    private static String platform = ConfigFileReaderUtils.getProperty("run.platform");
    private static WebDriver driver;

    public static void createDriver() {

        if(env.equalsIgnoreCase("Local")) {
            driver = new ChromeDriver();
            WebDriverManager.setDriver(driver);
            log.info("Local driver created");
        } else if (env.equalsIgnoreCase("Remote")) {
            MutableCapabilities options = null;
            String url = ConfigFileReaderUtils.getProperty("ec2.url");

            try {
                if(browserType.equalsIgnoreCase("Chrome")) {
                    options = new ChromeOptions();
                } else if(browserType.equalsIgnoreCase("Firefox")) {
                    options = new FirefoxOptions();
                } else if(browserType.equalsIgnoreCase("MicrosoftEdge")) {
                    options = new EdgeOptions();
                } else {
                    System.out.println("Invalid browser");
                }

                options.setCapability("browserName", browserType);
                options.setCapability("browserVersion", browserVersion);
                options.setCapability("platformName", platform);
                driver = new RemoteWebDriver(new URL(url), options);
                WebDriverManager.setDriver(driver);
                log.info("Remote driver created");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Invalid environment");
        }

        prepareBrowser();
    }

    private static  void prepareBrowser() {
        WebDriverManager.getDriver().manage().deleteAllCookies();
        WebDriverManager.getDriver().manage().window().maximize();
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }
}
