package com.automation.stepdefs.api;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import lombok.extern.java.Log;

@Log
public class API_Hooks {

    @Before("@api")
    public void setUp() {
        ConfigFileReaderUtils.initConfig();
        RestAssured.baseURI = ConfigFileReaderUtils.getProperty("api.url");
        RestAssuredUtils.initializeRestAssured();
        RestAssuredUtils.setAuthorizationToken();
        log.info("RequestSpecification is initialized");
    }

    @After("@api")
    public void cleanUp() {
        RestAssuredUtils.resetRestAssured();
        log.info("RequestSpecification is set to null");
    }

}
