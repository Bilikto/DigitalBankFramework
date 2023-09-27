package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        driver.get(ConfigFileReaderUtils.getProperty("app.url"));
    }

    public void enterUserNameAndPassword() {
        userNameInput.sendKeys(ConfigFileReaderUtils.getProperty("app.username"));
        passwordInput.sendKeys(ConfigFileReaderUtils.getProperty("app.password"));
    }

    public void clickOnSignInButton() {
        signInBtn.click();
    }

    @Override
    public void verifyPage() {
        Assert.assertTrue("The LoginPage is not displayed", userNameInput.isDisplayed());
        Assert.assertTrue("The LoginPage is not displayed", passwordInput.isDisplayed());
    }

    public void clickOnSignUpLink() {
        signUpLink.click();
    }

    public void verifySuccessMsg() {
        Assert.assertEquals("Registration success message is missing from Login page", successMsg.getText(), "Registration Successful. Please Login.");
    }

}
