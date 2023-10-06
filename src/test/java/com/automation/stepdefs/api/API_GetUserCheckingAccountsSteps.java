package com.automation.stepdefs.api;

import com.automation.pojos.AccountRequestPojo;
import com.automation.pojos.AccountResponsePojo;
import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.HelperUtils;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class API_GetUserCheckingAccountsSteps {

    private AccountRequestPojo expectedCheckingAccountPojo;
    private List<AccountResponsePojo> actualCheckingAccountList;

    @Then("verify the following should be in the standard checking accounts response payload")
    public void verify_the_following_should_be_in_the_standard_checking_account_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedCheckingAccList = dataTable.asMaps(String.class, String.class);
        actualCheckingAccountList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});

        for(int i = 0; i < expectedCheckingAccList.size(); i++) {
                Assert.assertEquals("An id is not matching ", expectedCheckingAccList.get(i).get("id"), String.valueOf(actualCheckingAccountList.get(i).getId()));
                Assert.assertEquals("The name is not matching ", expectedCheckingAccList.get(i).get("name"), actualCheckingAccountList.get(i).getName());
                Assert.assertEquals("An account number is not matching ", expectedCheckingAccList.get(i).get("accountNumber"), String.valueOf(actualCheckingAccountList.get(i).getAccountNumber()));
                Assert.assertEquals("A current balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("currentBalance")), actualCheckingAccountList.get(i).getCurrentBalance(), .1);
                Assert.assertEquals("An opening balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("openingBalance")), actualCheckingAccountList.get(i).getOpeningBalance(), .1);
                Assert.assertEquals("An interest rate is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("interestRate")), actualCheckingAccountList.get(i).getInterestRate(), .1);
                Assert.assertEquals("An opened date is not matching ", expectedCheckingAccList.get(i).get("dateOpened"), actualCheckingAccountList.get(i).getDateOpened());
        }
    }

    @Then("verify the following should be in the interest checking accounts response payload")
    public void verify_the_following_should_be_in_the_interest_checking_account_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedCheckingAccList = dataTable.asMaps(String.class, String.class);
        actualCheckingAccountList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        List<AccountResponsePojo> actualResponseListFiltered = new ArrayList<>();

        for (AccountResponsePojo accountResponsePojo : actualCheckingAccountList) {
            if (accountResponsePojo.getAccountType().getName().equals("Interest Checking")) {
                actualResponseListFiltered.add(accountResponsePojo);
            }
        }

        for (int i = 0; i < expectedCheckingAccList.size(); i++) {
            Assert.assertEquals("An id is not matching ", expectedCheckingAccList.get(i).get("id"), String.valueOf(actualResponseListFiltered.get(i).getId()));
            Assert.assertEquals("The name is not matching ", expectedCheckingAccList.get(i).get("name"), actualResponseListFiltered.get(i).getName());
            Assert.assertEquals("An account number is not matching ", expectedCheckingAccList.get(i).get("accountNumber"), String.valueOf(actualResponseListFiltered.get(i).getAccountNumber()));
            Assert.assertEquals("A current balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("currentBalance")), actualResponseListFiltered.get(i).getCurrentBalance(), .1);
            Assert.assertEquals("An opening balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("openingBalance")), actualResponseListFiltered.get(i).getOpeningBalance(), .1);
            Assert.assertEquals("An interest rate is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("interestRate")), actualResponseListFiltered.get(i).getInterestRate(), .1);
            Assert.assertEquals("An opened date is not matching ", expectedCheckingAccList.get(i).get("dateOpened"), actualResponseListFiltered.get(i).getDateOpened());
        }
    }

    @Then("verify an account type {string} in response payload")
    public void verify_an_account_type_in_response_payload(String fileName) {
        try {
            String jsonFolderPath = ConfigFileReaderUtils.getProperty("json.folder.path");
            String json = HelperUtils.readDataFromFile(jsonFolderPath+fileName);
            ObjectMapper om = new ObjectMapper();
            expectedCheckingAccountPojo = om.readValue(json, AccountRequestPojo.class);
            actualCheckingAccountList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});

            for (AccountResponsePojo accountResponsePojo : actualCheckingAccountList) {
                if (!accountResponsePojo.getAccountType().getName().equals("Standard Checking") || !accountResponsePojo.getAccountType().getName().equals("Interest Checking")) {
                    continue;
                }
                Assert.assertEquals("An account type id is not matching ", expectedCheckingAccountPojo.getAccountType().getId(), accountResponsePojo.getAccountType().getId());
                Assert.assertEquals("An account type code is not matching ", expectedCheckingAccountPojo.getAccountType().getCode(), accountResponsePojo.getAccountType().getCode());
                Assert.assertEquals("An account type category is not matching ", expectedCheckingAccountPojo.getAccountType().getCategory(), accountResponsePojo.getAccountType().getCategory());
                Assert.assertEquals("An account type name is not matching ", expectedCheckingAccountPojo.getAccountType().getName(), accountResponsePojo.getAccountType().getName());
                Assert.assertEquals("An account type interest rate is not matching ", expectedCheckingAccountPojo.getAccountType().getInterestRate(), accountResponsePojo.getAccountType().getInterestRate(), .1);
                Assert.assertEquals("An account type min deposit is not matching ", expectedCheckingAccountPojo.getAccountType().getMinDeposit(), accountResponsePojo.getAccountType().getMinDeposit(), .1);
                Assert.assertEquals("An account type overdraft limit is not matching ", expectedCheckingAccountPojo.getAccountType().getOverdraftLimit(), accountResponsePojo.getAccountType().getOverdraftLimit(), .1);
                Assert.assertEquals("An account type overdraft fee is not matching ", expectedCheckingAccountPojo.getAccountType().getOverdraftFee(), accountResponsePojo.getAccountType().getOverdraftFee(), .1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Then("verify ownership type in the response payload")
    public void verify_ownership_type_in_the_response_payload() {
        for (AccountResponsePojo accountResponsePojo : actualCheckingAccountList) {
            if (accountResponsePojo.getOwnershipType().getCode().equalsIgnoreCase("JNT")) {
                continue;
            } else {
                if (accountResponsePojo.getOwnershipType().getCode().equalsIgnoreCase("IND")) {
                    continue;
                }
            }
            Assert.assertEquals("An ownership type id is not matching ", expectedCheckingAccountPojo.getOwnershipType().getId(), accountResponsePojo.getOwnershipType().getId());
            Assert.assertEquals("An ownership type code is not matching ", expectedCheckingAccountPojo.getOwnershipType().getCode(), accountResponsePojo.getOwnershipType().getCode());
            Assert.assertEquals("An ownership type name not matching ", expectedCheckingAccountPojo.getOwnershipType().getName(), accountResponsePojo.getOwnershipType().getName());
        }
    }

    @Then("verify an account standing in response payload")
    public void verify_an_account_standing_in_response_payload() {
        for (AccountResponsePojo accountResponsePojo : actualCheckingAccountList) {
            Assert.assertEquals("An account standing id is not matching ", expectedCheckingAccountPojo.getAccountStanding().getId(), accountResponsePojo.getAccountStanding().getId());
            Assert.assertEquals("An account standing code is not matching ", expectedCheckingAccountPojo.getAccountStanding().getCode(), accountResponsePojo.getAccountStanding().getCode());
            Assert.assertEquals("An account standing name not matching ", expectedCheckingAccountPojo.getAccountStanding().getName(), accountResponsePojo.getAccountStanding().getName());
        }
    }
}
