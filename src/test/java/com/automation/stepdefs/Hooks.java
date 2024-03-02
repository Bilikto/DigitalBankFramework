package com.automation.stepdefs;

import com.automation.utils.WebDriverManager;
import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import com.automation.utils.DriverUtils;
import io.cucumber.java.*;
import lombok.extern.java.Log;

@Log
public class Hooks {

    @BeforeAll
    public static void setUpDB() {
        ConfigFileReaderUtils.initConfig();
        DatabaseUtils.initDatabase();
        log.info("Database is initialized");
    }

    @AfterAll
    public static void cleanUpDB() {
        DatabaseUtils.closeDatabase();
        log.info("Database is closed");
    }

    @Before("@Regression")
    public void setUp() {
        DriverUtils.createDriver();
        log.info("WebDriver is created");
    }

    @After("@Regression")
    public void cleanUp() {
        WebDriverManager.removeDriver();
        log.info("WebDriver is closed");
    }

}
