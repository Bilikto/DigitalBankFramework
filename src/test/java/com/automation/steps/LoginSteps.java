package com.automation.steps;

import com.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("user open website")
    public void user_open_website() {
        loginPage.openWebsite();
    }

    @When("user enter username and password")
    public void user_enter_username_and_password() {
        loginPage.enterUserNameAndPassword();
    }

    @When("click on sign in button")
    public void click_on_sign_in_button() {
        loginPage.clickOnSignInButton();
    }

    @When("user click on Sign Up here link")
    public void user_click_on_sign_up_here_link() {
        loginPage.clickOnSignUpLink();
    }

}
