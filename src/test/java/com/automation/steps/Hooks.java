package com.automation.steps;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import com.automation.utils.DriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@Regression")
    public void setUp() {
        DriverUtils.createDriver();
        ConfigFileReaderUtils.initConfig();
        DatabaseUtils.initDatabase();
    }

    @After("@Regression")
    public void cleanUp() {
        DriverUtils.getDriver().quit();
        DatabaseUtils.closeDatabase();
    }

}
