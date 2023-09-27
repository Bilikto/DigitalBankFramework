package com.automation.steps;

import com.automation.pages.SavingsAccountPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SavingAccountCreationSteps {

    SavingsAccountPage savingsAccountPage = new SavingsAccountPage();

    @Then("verify user is on Create Savings page")
    public void verify_user_is_on_create_savings_page() {
        savingsAccountPage.verifyPage();
    }

    @When("user select savings account type")
    public void user_select_savings_account_type() {
        savingsAccountPage.selectCheckingAccountType();
    }

    @When("select savings account ownership type")
    public void select_savings_account_ownership_type() {
        savingsAccountPage.selectSavingsAccountOwnership();
    }

    @When("provide savings account name")
    public void provide_savings_account_name() {
        savingsAccountPage.provideSavingsAccountName();
    }

    @When("provide savings initial deposit amount")
    public void provide_savings_initial_deposit_amount() {
        savingsAccountPage.provideSavingsInitialDepositAmount();
    }

    @When("click on submit button on savings account")
    public void click_on_submit_button() {
        savingsAccountPage.clickOnSubmitBtn();
    }

    @Then("verify a new saving account created")
    public void verify_a_new_saving_account_created() {
        savingsAccountPage.verifyNewAccountIsCreated();
    }

    @Then("verify a new saving account is saved in database")
    public void verify_a_new_saving_account_is_saved_in_database() {
        savingsAccountPage.verifyNewSavingAccountSavedInDatabase();
    }

}
