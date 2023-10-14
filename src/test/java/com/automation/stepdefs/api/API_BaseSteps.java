package com.automation.stepdefs.api;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class API_BaseSteps {

    @Given("user is authenticated")
    public void user_is_authenticated() {
        RestAssuredUtils.setAuthorizationToken();
    }

    @When("user set up request for {string}")
    public void user_set_up_request_for(String endPoint) {
        RestAssuredUtils.setEndpoint(endPoint);
    }

    @When("user set up {string} header token")
    public void user_set_up_header_token(String key) {
        String token = RestAssuredUtils.getAuthorizationToken();
        RestAssuredUtils.setHeader(key, token);
    }

    @And("user set up a path parameter {string} as a {int}")
    public void user_set_up_path_parameter_as_a(String key, Object value) {
        RestAssuredUtils.setPathParam(key, value);
    }

    @And("user perform GET call")
    public void user_perform_get_call() {
        RestAssuredUtils.get();
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals("The status code is not matching", statusCode, RestAssuredUtils.getStatusCode());
    }

}
