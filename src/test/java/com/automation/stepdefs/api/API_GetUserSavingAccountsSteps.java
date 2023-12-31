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

import java.util.List;
import java.util.Map;

public class API_GetUserSavingAccountsSteps {
    List<AccountResponsePojo> actualSavingAccountList;
    AccountRequestPojo expectedSavingAccountPojo;

    @Then("verify the following should be in the saving accounts response payload")
    public void verify_the_following_should_be_in_the_saving_accounts_response_payload(DataTable dataTable) {
        List<Map<String, String>> expectedCheckingAccList = dataTable.asMaps(String.class, String.class);
        actualSavingAccountList = RestAssuredUtils.getResponse().as(new TypeRef<>() {});

        for(int i = 0; i < expectedCheckingAccList.size(); i++) {
            Assert.assertEquals("An id is not matching ", expectedCheckingAccList.get(i).get("id"), String.valueOf(actualSavingAccountList.get(i).getId()));
            Assert.assertEquals("The name is not matching ", expectedCheckingAccList.get(i).get("name"), actualSavingAccountList.get(i).getName());
            Assert.assertEquals("An account number is not matching ", expectedCheckingAccList.get(i).get("accountNumber"), String.valueOf(actualSavingAccountList.get(i).getAccountNumber()));
            Assert.assertEquals("A current balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("currentBalance")), actualSavingAccountList.get(i).getCurrentBalance(), .1);
            Assert.assertEquals("An opening balance is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("openingBalance")), actualSavingAccountList.get(i).getOpeningBalance(), .1);
            Assert.assertEquals("An interest rate is not matching ", Double.parseDouble(expectedCheckingAccList.get(i).get("interestRate")), actualSavingAccountList.get(i).getInterestRate(), .1);
            Assert.assertEquals("An opened date is not matching ", expectedCheckingAccList.get(i).get("dateOpened"), actualSavingAccountList.get(i).getDateOpened());
        }
    }

    @Then("verify saving account type {string} in response payload")
    public void verify_saving_account_type_in_response_payload(String fileName) {
        try {
            String jsonFolderPath = ConfigFileReaderUtils.getProperty("json.folder.path");
            String json = HelperUtils.readDataFromFile(jsonFolderPath+fileName);
            ObjectMapper om = new ObjectMapper();
            expectedSavingAccountPojo = om.readValue(json, AccountRequestPojo.class);

            for (AccountResponsePojo accountResponsePojo : actualSavingAccountList) {
                if (!accountResponsePojo.getAccountType().getName().equals("Standard Checking") || !accountResponsePojo.getAccountType().getName().equals("Interest Checking")) {
                    continue;
                }
                Assert.assertEquals("An account type id is not matching ", expectedSavingAccountPojo.getAccountType().getId(), accountResponsePojo.getAccountType().getId());
                Assert.assertEquals("An account type code is not matching ", expectedSavingAccountPojo.getAccountType().getCode(), accountResponsePojo.getAccountType().getCode());
                Assert.assertEquals("An account type category is not matching ", expectedSavingAccountPojo.getAccountType().getCategory(), accountResponsePojo.getAccountType().getCategory());
                Assert.assertEquals("An account type name is not matching ", expectedSavingAccountPojo.getAccountType().getName(), accountResponsePojo.getAccountType().getName());
                Assert.assertEquals("An account type interest rate is not matching ", expectedSavingAccountPojo.getAccountType().getInterestRate(), accountResponsePojo.getAccountType().getInterestRate(), .1);
                Assert.assertEquals("An account type min deposit is not matching ", expectedSavingAccountPojo.getAccountType().getMinDeposit(), accountResponsePojo.getAccountType().getMinDeposit(), .1);
                Assert.assertEquals("An account type overdraft limit is not matching ", expectedSavingAccountPojo.getAccountType().getOverdraftLimit(), accountResponsePojo.getAccountType().getOverdraftLimit(), .1);
                Assert.assertEquals("An account type overdraft fee is not matching ", expectedSavingAccountPojo.getAccountType().getOverdraftFee(), accountResponsePojo.getAccountType().getOverdraftFee(), .1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Then("verify saving account ownership type in the response payload")
    public void verify_saving_account_ownership_type_in_the_response_payload() {
        for (AccountResponsePojo accountResponsePojo : actualSavingAccountList) {
            if (!accountResponsePojo.getOwnershipType().getCode().equalsIgnoreCase("SAV")) {
                continue;
            }

            Assert.assertEquals("An ownership type id is not matching ", expectedSavingAccountPojo.getOwnershipType().getId(), accountResponsePojo.getOwnershipType().getId());
            Assert.assertEquals("An ownership type code is not matching ", expectedSavingAccountPojo.getOwnershipType().getCode(), accountResponsePojo.getOwnershipType().getCode());
            Assert.assertEquals("An ownership type name not matching ", expectedSavingAccountPojo.getOwnershipType().getName(), accountResponsePojo.getOwnershipType().getName());
        }
    }

    @Then("verify saving account standing in response payload")
    public void verify_saving_account_standing_in_response_payload() {
        for (AccountResponsePojo accountResponsePojo : actualSavingAccountList) {
            Assert.assertEquals("An account standing id is not matching ", expectedSavingAccountPojo.getAccountStanding().getId(), accountResponsePojo.getAccountStanding().getId());
            Assert.assertEquals("An account standing code is not matching ", expectedSavingAccountPojo.getAccountStanding().getCode(), accountResponsePojo.getAccountStanding().getCode());
            Assert.assertEquals("An account standing name not matching ", expectedSavingAccountPojo.getAccountStanding().getName(), accountResponsePojo.getAccountStanding().getName());
        }
    }



}
