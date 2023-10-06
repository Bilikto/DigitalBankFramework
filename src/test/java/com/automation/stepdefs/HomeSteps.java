package com.automation.stepdefs;

import com.automation.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSteps {

    HomePage homePage = new HomePage();

    @Then("verify user is on Home page")
    public void verify_user_is_on_home_page() {
        homePage.verifyPage();
    }

    @When("user click on Checking menu")
    public void user_click_on_checking_menu() {
        homePage.clickOnCheckingMenu();
    }

    @When("click on New Checking menu")
    public void click_on_new_checking_link() {
        homePage.clickOnNewCheckingLink();
    }

    @When("user click on Savings menu")
    public void user_click_on_savings_menu() {
        homePage.clickOnSavingsMenu();
    }

    @When("click on New Savings menu")
    public void click_on_new_savings_link() {
        homePage.clickOnNewSavingsLink();
    }

}
