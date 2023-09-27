package com.automation.steps.api;

import com.automation.utils.ConfigFileReaderUtils;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class API_Hooks {

    @Before("@api")
    public void setUp() {
        ConfigFileReaderUtils.initConfig();
        RestAssured.baseURI = ConfigFileReaderUtils.getProperty("api.url");
    }

}
