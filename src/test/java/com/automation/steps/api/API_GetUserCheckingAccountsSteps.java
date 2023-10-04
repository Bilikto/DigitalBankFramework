package com.automation.steps.api;

import com.automation.pojo.AccountRequestPojo;
import com.automation.pojo.AccountResponsePojo;
import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.HelperUtils;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class API_GetUserCheckingAccountsSteps {

    private AccountRequestPojo expectedCheckingAccountPojo;
    private List<AccountResponsePojo> actualCheckingAccount;

    @Then("verify the following should be in the standard checking accounts response payload")
    public void verify_the_following_should_be_in_the_standard_checking_account_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedCheckingAccList = dataTable.asMaps(String.class, String.class);
        System.out.println("Expected value " + expectedCheckingAccList);

        List<AccountResponsePojo> actualResponseList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        System.out.println("Actual value " + actualResponseList.get(0).getId());

        for(int i = 0; i < expectedCheckingAccList.size(); i++) {
                Assert.assertEquals("An id is not matching ", expectedCheckingAccList.get(i).get("id"), String.valueOf(actualResponseList.get(i).getId()));
                Assert.assertEquals("The name is not matching ", expectedCheckingAccList.get(i).get("name"), actualResponseList.get(i).getName());
                Assert.assertEquals("An account number is not matching ", expectedCheckingAccList.get(i).get("accountNumber"), String.valueOf(actualResponseList.get(i).getAccountNumber()));
                Assert.assertEquals("A current balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("currentBalance")), actualResponseList.get(i).getCurrentBalance(), .1);
                Assert.assertEquals("An opening balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("openingBalance")), actualResponseList.get(i).getOpeningBalance(), .1);
                Assert.assertEquals("An interest rate is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("interestRate")), actualResponseList.get(i).getInterestRate(), .1);
                Assert.assertEquals("An opened date is not matching ", expectedCheckingAccList.get(i).get("dateOpened"), actualResponseList.get(i).getDateOpened());
        }
    }

    @Then("verify the following should be in the interest checking accounts response payload")
    public void verify_the_following_should_be_in_the_interest_checking_account_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedCheckingAccList = dataTable.asMaps(String.class, String.class);
        System.out.println("Expected value " + expectedCheckingAccList);

        List<AccountResponsePojo> actualResponseList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
        System.out.println("Actual value " + actualResponseList.get(0).getId());

        for(int i = 0; i < expectedCheckingAccList.size(); i++) {
            Assert.assertEquals("An id is not matching ", expectedCheckingAccList.get(i).get("id"), String.valueOf(actualResponseList.get(i).getId()));
            Assert.assertEquals("The name is not matching ", expectedCheckingAccList.get(i).get("name"), actualResponseList.get(i).getName());
            Assert.assertEquals("An account number is not matching ", expectedCheckingAccList.get(i).get("accountNumber"), String.valueOf(actualResponseList.get(i).getAccountNumber()));
            Assert.assertEquals("A current balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("currentBalance")), actualResponseList.get(i).getCurrentBalance(), .1);
            Assert.assertEquals("An opening balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("openingBalance")), actualResponseList.get(i).getOpeningBalance(), .1);
            Assert.assertEquals("An interest rate is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("interestRate")), actualResponseList.get(i).getInterestRate(), .1);
            Assert.assertEquals("An opened date is not matching ", expectedCheckingAccList.get(i).get("dateOpened"), actualResponseList.get(i).getDateOpened());
        }
    }

    @Then("verify an account type {string} in response payload")
    public void verify_an_account_type_in_response_payload(String fileName) {
        try {
            String jsonFolderPath = ConfigFileReaderUtils.getProperty("json.folder.path");
            String json = HelperUtils.readDataFromFile(jsonFolderPath+fileName);
            ObjectMapper om = new ObjectMapper();
            expectedCheckingAccountPojo = om.readValue(json, AccountRequestPojo.class);
            actualCheckingAccount = RestAssuredUtils.getResponse().as(new TypeRef<>() {});
            System.out.println("Actual --> " + actualCheckingAccount.get(0).getAccountType());
            System.out.println("Expected --> " + expectedCheckingAccountPojo.getAccountType());

            for(int i = 0; i < actualCheckingAccount.size(); i++) {
                if(!actualCheckingAccount.get(i).getAccountType().getName().equals("Standard Checking")) {
                    continue;
                }
                Assert.assertEquals("An account type id is not matching ", expectedCheckingAccountPojo.getAccountType().getId(), actualCheckingAccount.get(i).getAccountType().getId());
                Assert.assertEquals("An account type code is not matching ", expectedCheckingAccountPojo.getAccountType().getCode(), actualCheckingAccount.get(i).getAccountType().getCode());
                Assert.assertEquals("An account type category is not matching ", expectedCheckingAccountPojo.getAccountType().getCategory(), actualCheckingAccount.get(i).getAccountType().getCategory());
                Assert.assertEquals("An account type name is not matching ", expectedCheckingAccountPojo.getAccountType().getName(), actualCheckingAccount.get(i).getAccountType().getName());
                Assert.assertEquals("An account type interest rate is not matching ", expectedCheckingAccountPojo.getAccountType().getInterestRate(), actualCheckingAccount.get(i).getAccountType().getInterestRate(), .1);
                Assert.assertEquals("An account type min deposit is not matching ", expectedCheckingAccountPojo.getAccountType().getMinDeposit(), actualCheckingAccount.get(i).getAccountType().getMinDeposit(), .1);
                Assert.assertEquals("An account type overdraft limit is not matching ", expectedCheckingAccountPojo.getAccountType().getOverdraftLimit(), actualCheckingAccount.get(i).getAccountType().getOverdraftLimit(), .1);
                Assert.assertEquals("An account type overdraft fee is not matching ", expectedCheckingAccountPojo.getAccountType().getOverdraftFee(), actualCheckingAccount.get(i).getAccountType().getOverdraftFee(), .1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Then("verify an individual ownership type in the response payload")
    public void verify_an_individual_ownership_type_in_the_response_payload() {
        for(int i = 0; i < actualCheckingAccount.size(); i++) {
            if(actualCheckingAccount.get(i).getOwnershipType().getCode().equalsIgnoreCase("JNT")) {
                continue;
            }
            Assert.assertEquals("An ownership type id is not matching ", expectedCheckingAccountPojo.getOwnershipType().getId(), actualCheckingAccount.get(i).getOwnershipType().getId());
            Assert.assertEquals("An ownership type code is not matching ", expectedCheckingAccountPojo.getOwnershipType().getCode(), actualCheckingAccount.get(i).getOwnershipType().getCode());
            Assert.assertEquals("An ownership type name not matching ",expectedCheckingAccountPojo.getOwnershipType().getName(), actualCheckingAccount.get(i).getOwnershipType().getName());
        }
    }

    @Then("verify an joint ownership type in the response payload")
    public void verify_an_joint_ownership_type_in_the_response_payload() {
        for(int i = 0; i < actualCheckingAccount.size(); i++) {
            if(actualCheckingAccount.get(i).getOwnershipType().getCode().equalsIgnoreCase("IND")) {
                continue;
            }
            System.out.println("1" + expectedCheckingAccountPojo.getId());
            System.out.println("2" + actualCheckingAccount.get(i).getId());
            Assert.assertEquals("An ownership type id is not matching ", expectedCheckingAccountPojo.getOwnershipType().getId(), actualCheckingAccount.get(i).getOwnershipType().getId());
            Assert.assertEquals("An ownership type code is not matching ", expectedCheckingAccountPojo.getOwnershipType().getCode(), actualCheckingAccount.get(i).getOwnershipType().getCode());
            Assert.assertEquals("An ownership type name not matching ",expectedCheckingAccountPojo.getOwnershipType().getName(), actualCheckingAccount.get(i).getOwnershipType().getName());
        }
    }

    @Then("verify an account standing in response payload")
    public void verify_an_account_standing_in_response_payload() {
        for(int i = 0; i < actualCheckingAccount.size(); i++) {
            Assert.assertEquals("An account standing id is not matching ", expectedCheckingAccountPojo.getAccountStanding().getId(), actualCheckingAccount.get(i).getAccountStanding().getId());
            Assert.assertEquals("An account standing code is not matching ", expectedCheckingAccountPojo.getAccountStanding().getCode(), actualCheckingAccount.get(i).getAccountStanding().getCode());
            Assert.assertEquals("An account standing name not matching ", expectedCheckingAccountPojo.getAccountStanding().getName(), actualCheckingAccount.get(i).getAccountStanding().getName());
        }
    }
}
