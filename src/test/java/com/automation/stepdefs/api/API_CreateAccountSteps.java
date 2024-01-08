package com.automation.stepdefs.api;

import com.automation.pages.BasePage;
import com.automation.pojos.AccountResponsePojo;
import com.automation.pojos.NewAccountRequestPojo;
import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.HelperUtils;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.Assert;

@Log
public class API_CreateAccountSteps {

    NewAccountRequestPojo requestPojo;
    AccountResponsePojo responsePojo;
    Response response;

    @Given("user setup request for {string}")
    public void user_setup_request_for(String endPoint) {
        RestAssuredUtils.setEndpoint(endPoint);
    }

    @When("user set body {string} as pojo")
    public void user_set_body_as_pojo(String fileName) {
        try {
            String jsonFolderPath = ConfigFileReaderUtils.getProperty("json.folder.path");
            String json = HelperUtils.readDataFromFile(jsonFolderPath + fileName);
            ObjectMapper om = new ObjectMapper();
            requestPojo = om.readValue(json, NewAccountRequestPojo.class);
            RestAssuredUtils.setBody(requestPojo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @When("user perform post call")
    public void user_perform_post_call() {
        RestAssuredUtils.post();
    }

    @Then("verify response has the same account name as in the request body")
    public void verify_response_has_the_same_account_name_as_in_the_request_body() {
        response = RestAssuredUtils.getResponse();
        responsePojo = response.as(AccountResponsePojo.class);
        String actualAccName = responsePojo.getName();
        String expectedAccName = requestPojo.getAccountName();
        Assert.assertEquals("The account names are not matching", expectedAccName, actualAccName);
        log.info("The account names are verified");
    }

    @Then("verify response has the same account type code as in the request body")
    public void verify_response_has_the_same_account_type_code_as_in_the_request_body() {
        response = RestAssuredUtils.getResponse();
        responsePojo = response.as(AccountResponsePojo.class);
        String actualAccTypeCode = responsePojo.getAccountType().getCode();
        String expectedAccTypeCode = requestPojo.getAccountTypeCode();
        Assert.assertEquals("The account names are not matching", actualAccTypeCode, expectedAccTypeCode);
        log.info("The account type code are verified");
        
    }

    @Then("verify response has the same opening deposit as in the request body")
    public void verify_response_has_the_same_opening_deposit_as_in_the_request_body() {
        response = RestAssuredUtils.getResponse();
        responsePojo = response.as(AccountResponsePojo.class);
        double actualOpenBalance = responsePojo.getOpeningBalance();
        double expectedOpenBalance = requestPojo.getOpeningDeposit();
        Assert.assertEquals(actualOpenBalance, expectedOpenBalance, 0.2);
        log.info("The account opening balances are verified");
        
    }

    @Then("verify response has the same owner type code as in the request body")
    public void verify_response_has_the_same_owner_type_code_as_in_the_request_body() {
        response = RestAssuredUtils.getResponse();
        responsePojo = response.as(AccountResponsePojo.class);
        String actualOwnerTypeCode = responsePojo.getOwnershipType().getCode();
        String expectedOwnerTypeCode = requestPojo.getOwnerTypeCode();
        Assert.assertEquals("The account names are not matching", actualOwnerTypeCode, expectedOwnerTypeCode);
        log.info("The account owner type codes are verified");
    }
}
