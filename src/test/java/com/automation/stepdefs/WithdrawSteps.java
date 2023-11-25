package com.automation.stepdefs;

import com.automation.pages.TransactionsPage;
import com.automation.pages.WithdrawPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WithdrawSteps {

    WithdrawPage withdrawPage = new WithdrawPage();
    TransactionsPage transactionsPage = new TransactionsPage();

    @Then("verify a Withdraw page is displayed")
    public void verify_a_withdraw_page_is_displayed() {
        withdrawPage.verifyPage();
    }

    @When("user select account for withdraw")
    public void user_select_account_for_withdraw() {
        withdrawPage.selectAccountToWithdraw();
    }

    @When("user enter withdraw amount")
    public void user_enter_withdraw_amount() {
        withdrawPage.enterWithdrawAmount();
    }

    @When("user click submit button")
    public void user_click_submit_button() {
        withdrawPage.clickSubmitBtn();
    }

    @Then("verify an account transaction page is displayed")
    public void verify_an_account_transaction_page_is_displayed() {
        transactionsPage.verifyPage();
    }

    @Then("verify a new transaction is presented in transaction history")
    public void verify_a_new_transaction_is_presented_in_transaction_history() {
        transactionsPage.verifyTransaction();
    }

    @Then("verify transaction is getting updated in database")
    public void verify_transaction_is_getting_updated_in_database() {
        
        
    }

}
