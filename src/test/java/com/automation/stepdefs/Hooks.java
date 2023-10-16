package com.automation.stepdefs;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import com.automation.utils.DriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@Regression")
    public void setUp() {
        ConfigFileReaderUtils.initConfig();
        DatabaseUtils.initDatabase();
        String env = ConfigFileReaderUtils.getProperty("run.env");

        if(env.equalsIgnoreCase("local")) {
            DriverUtils.createLocalDriver();
        } else if(env.equalsIgnoreCase("remote")) {
            String browser = ConfigFileReaderUtils.getProperty("run.browser");
            String browserVersion = ConfigFileReaderUtils.getProperty("run.browserVersion");
            String browserPlatform = ConfigFileReaderUtils.getProperty("run.platform");

            DriverUtils.createRemoteDriver(browser, browserVersion, browserPlatform);
        } else {
            System.out.println("Provide run environment: local or remote");
        }
    }

    @After("@Regression")
    public void cleanUp() {
        DriverUtils.getDriver().quit();
        DatabaseUtils.closeDatabase();
    }

}
