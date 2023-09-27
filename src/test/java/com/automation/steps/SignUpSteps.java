package com.automation.steps;

import com.automation.pages.LoginPage;
import com.automation.pages.RegisterPage;
import com.automation.pages.SignUpPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpSteps {

    SignUpPage signUpPage = new SignUpPage();
    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage =  new LoginPage();

    @Then("validate the user is on Sign Up page")
    public void validate_the_user_is_on_sign_up_page() {
        signUpPage.verifyPage();
    }

    @When("user fill out personal information on Sign Up page")
    public void user_fill_out_personal_information_on_sign_up_page() {
        signUpPage.userFillsOutInformationForSignUp();
       
    }

    @When("click on next button")
    public void click_on_next_button() {
        signUpPage.clickNextBtn();
    }

    @Then("validate the user is on Register page")
    public void validate_the_user_is_on_register_page() {
        registerPage.verifyPage();
    }

    @When("user fill out personal information on Register page")
    public void user_fill_out_personal_information_on_register_page() {
        registerPage.userFillsOutInformationForRegistration();
       
    }

    @When("click on register button")
    public void click_on_register_button() {
        registerPage.clickRegisterBtn();
    }

    @Then("validate registration success message")
    public void validate_registration_success_message() {
        loginPage.verifySuccessMsg();
    }
}
