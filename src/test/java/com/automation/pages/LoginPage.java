package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    WebElement userNameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "submit")
    WebElement signInBtn;

    @FindBy(xpath = "//div[contains(@class, 'register-link')]//a")
    WebElement signUpLink;

    @FindBy(xpath = "//div[@class='login-form']//span[contains(text(), 'Registration Successful')]")
    WebElement successMsg;

    /*************************************************************************************
     **************************************** METHODS ************************************
     ************************************************************************************/

    public void openWebsite() {
        log.info("User is opening website");
        driver.get(ConfigFileReaderUtils.getProperty("app.url"));
    }

    public void enterUserNameAndPassword() {
        log.info("User is entering username and password");
        userNameInput.sendKeys(ConfigFileReaderUtils.getProperty("app.username"));
        passwordInput.sendKeys(ConfigFileReaderUtils.getProperty("app.password"));
    }

    public void clickOnSignInButton() {
        log.info("User is clicking Sign in button");
        signInBtn.click();
    }

    @Override
    public void verifyPage() {
        Assert.assertTrue("The LoginPage is not displayed", userNameInput.isDisplayed());
        Assert.assertTrue("The LoginPage is not displayed", passwordInput.isDisplayed());
    }

    public void clickOnSignUpLink() {
        log.info("User is clicking Sign up link");
        signUpLink.click();
    }

    public void verifySuccessMsg() {
        log.info("Verifying Registration Success message");
        Assert.assertEquals("Registration success message is missing from Login page", successMsg.getText(), "Registration Successful. Please Login.");
    }

}
