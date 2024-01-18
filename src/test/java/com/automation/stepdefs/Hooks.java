package com.automation.stepdefs;

import com.automation.utils.WebDriverManager;
import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import com.automation.utils.DriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.java.Log;

@Log
public class Hooks {

    @Before("@Regression")
    public void setUp() {
        ConfigFileReaderUtils.initConfig();
        DatabaseUtils.initDatabase();
        DriverUtils.createDriver();
    }

    @After("@Regression")
    public void cleanUp() {
        WebDriverManager.removeDriver();
//        DatabaseUtils.closeDatabase();
    }

}
