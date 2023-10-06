package com.automation.stepdefs;

import com.automation.pages.CheckingAccountPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckingAccountCreationSteps {

    CheckingAccountPage checkingAccountPage = new CheckingAccountPage();

    @Then("verify user is on Create Checking page")
    public void verify_user_is_on_create_checking_page() {
        checkingAccountPage.verifyPage();
    }

    @When("user select checking account type")
    public void user_select_checking_account_type() {
        checkingAccountPage.selectCheckingAccountType();
        
    }

    @When("select account ownership type")
    public void select_account_ownership_type() {
        checkingAccountPage.selectAccountOwnership();
    }

    @When("provide checking account name")
    public void provide_checking_account_name() {
        checkingAccountPage.provideAccountName();
    }

    @When("provide initial deposit amount")
    public void provide_initial_deposit_amount() {
        checkingAccountPage.provideInitialDepositAmount();
    }

    @When("click on submit button")
    public void click_on_submit_button() {
        checkingAccountPage.clickOnSubmitBtn();
    }

    @Then("verify a new checking account created")
    public void verify_a_new_checking_account_created() {
        checkingAccountPage.verifyNewAccountIsCreated();
    }

    @Then("verify a new checking account is saved in database")
    public void verify_a_new_checking_account_is_saved_in_database() {
        checkingAccountPage.verifyNewCheckingAccountSavedInDatabase();
    }

}
