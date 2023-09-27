package com.automation.steps.api;

import com.automation.pojo.AccountResponsePojo;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class API_GetUserCheckingAccountsSteps {

    @Then("verify the following should be the checking response payload")
    public void verify_the_following_should_be_the_checking_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedCheckingAccList = dataTable.asMaps(String.class, String.class);
        System.out.println("Expected value " + expectedCheckingAccList);

        List<AccountResponsePojo> actualResponseList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        System.out.println("Actual value " + actualResponseList.get(0).getId());

        Assert.assertEquals("An id is not matching ", expectedCheckingAccList.get(0).get("id"), String.valueOf(actualResponseList.get(0).getId()));
        Assert.assertEquals("The name is not matching ", expectedCheckingAccList.get(0).get("name"), actualResponseList.get(0).getName());
        Assert.assertEquals("An account number is not matching ", expectedCheckingAccList.get(0).get("accountNumber"), String.valueOf(actualResponseList.get(0).getAccountNumber()));
        Assert.assertEquals("A current balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(0).get("currentBalance")), actualResponseList.get(0).getCurrentBalance(), .1);
        Assert.assertEquals("An opening balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(0).get("openingBalance")), actualResponseList.get(0).getOpeningBalance(), .1);
        Assert.assertEquals("An interest rate is not matching ", Double.parseDouble(expectedCheckingAccList.get(0).get("interestRate")), actualResponseList.get(0).getInterestRate(), .1);
        Assert.assertEquals("An opened date is not matching ", expectedCheckingAccList.get(0).get("dateOpened"), actualResponseList.get(0).getDateOpened());
    }

    @Then("verify the following should be the account type response payload")
    public void verify_the_following_should_be_the_account_type_response_payload(DataTable dataTable) {
        List<List<String>> expectedResult = dataTable.asLists(String.class);
        System.out.println("Expected " + expectedResult);

        List<AccountResponsePojo> actualResult = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        System.out.println("Actual " + actualResult.get(0).getAccountType());

        Assert.assertEquals("An account type id is not matching ", Integer.parseInt(expectedResult.get(1).get(0)), actualResult.get(0).getAccountType().getId());
        Assert.assertEquals("An account type code is not matching ", expectedResult.get(1).get(1), actualResult.get(0).getAccountType().getCode());
        Assert.assertEquals("An account type category is not matching ", expectedResult.get(1).get(2), actualResult.get(0).getAccountType().getCategory());
        Assert.assertEquals("An account type category is not matching ", expectedResult.get(1).get(3), actualResult.get(0).getAccountType().getName());
        Assert.assertEquals("An account type interest rate is not matching ", Double.parseDouble(expectedResult.get(1).get(4)), actualResult.get(0).getAccountType().getInterestRate(), .1);
        Assert.assertEquals("An account type min deposit is not matching ", Double.parseDouble(expectedResult.get(1).get(5)), actualResult.get(0).getAccountType().getMinDeposit(), .1);
        Assert.assertEquals("An account type overdraft limit is not matching ", Double.parseDouble(expectedResult.get(1).get(6)), actualResult.get(0).getAccountType().getOverdraftLimit(), .1);
        Assert.assertEquals("An account type overdraft fee is not matching ", Double.parseDouble(expectedResult.get(1).get(7)), actualResult.get(0).getAccountType().getOverdraftFee(), .1);
    }

    @Then("verify the following should be the ownership type response payload")
    public void verify_the_following_should_be_the_ownership_type_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedResult = dataTable.asMaps(String.class, String.class);
        System.out.println("Expected " +  expectedResult);

        List<AccountResponsePojo> actualResult = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        System.out.println("Actual " + actualResult.get(0).getOwnershipType());

        Assert.assertEquals("An ownership type id is not matching ", Integer.parseInt(expectedResult.get(0).get("id")), actualResult.get(0).getOwnershipType().getId());
        Assert.assertEquals("An ownership type code is not matching ", expectedResult.get(0).get("code"), actualResult.get(0).getOwnershipType().getCode());
        Assert.assertEquals("An ownership type name not matching ", expectedResult.get(0).get("name"), actualResult.get(0).getOwnershipType().getName());
    }

    @Then("verify the following should be the account standing response payload")
    public void verify_the_following_should_be_the_account_standing_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedResult = dataTable.asMaps(String.class, String.class);
        System.out.println("Expected " + expectedResult);

        List<AccountResponsePojo> actualResult = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        System.out.println("Actual " + actualResult.get(0).getAccountStanding());

        Assert.assertEquals("An account standing id is not matching ", Integer.parseInt(expectedResult.get(0).get("id")), actualResult.get(0).getAccountStanding().getId());
        Assert.assertEquals("An account standing code is not matching ", expectedResult.get(0).get("code"), actualResult.get(0).getAccountStanding().getCode());
        Assert.assertEquals("An account standing name not matching ", expectedResult.get(0).get("name"), actualResult.get(0).getAccountStanding().getName());
    }
}
