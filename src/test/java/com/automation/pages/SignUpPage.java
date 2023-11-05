package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.Constant;
import com.automation.utils.HelperUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SignUpPage extends BasePage {

    @FindBy(id="title")
    WebElement titleDropdown;

    @FindBy(id="firstName")
    WebElement firstName;

    @FindBy(id="lastName")
    WebElement lastName;

    @FindBy(id="gender")
    List<WebElement> gender;

    @FindBy(id="dob")
    WebElement dob;

    @FindBy(id="ssn")
    WebElement ssn;

    @FindBy(id="emailAddress")
    WebElement email;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="confirmPassword")
    WebElement confirmPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement nextBtn;


    /*************************************************************************************
     **************************************** METHODS ************************************
     ************************************************************************************/

    @Override
    public void verifyPage() {
        Assert.assertTrue("The Sign Up page is not displayed", firstName.isDisplayed());
        Assert.assertTrue("The Sign Up page is not displayed", lastName.isDisplayed());
    }

    public void userFillsOutInformationForSignUp() {
        String titleTxt = ConfigFileReaderUtils.getProperty("signup.title");
        String firstNameTxt = ConfigFileReaderUtils.getProperty("signup.firstName");
        String lastNameTxt = ConfigFileReaderUtils.getProperty("signup.lastName");
        String genderTxt = ConfigFileReaderUtils.getProperty("signup.gender");
        String dobTxt = ConfigFileReaderUtils.getProperty("signup.dob");
        String ssnTxt = ConfigFileReaderUtils.getProperty("signup.ssn");
        String emailTxt = ConfigFileReaderUtils.getProperty("signup.email");
        String passwordTxt = ConfigFileReaderUtils.getProperty("signup.password");


        // Title
        Select title = new Select(titleDropdown);
        title.selectByVisibleText(titleTxt);

        // First and Last Name
        firstName.sendKeys(firstNameTxt);
        lastName.sendKeys(lastNameTxt);

        // Gender
        if(gender.get(0).getText().equals(genderTxt)) {
            gender.get(0).click();
        } else {
            gender.get(1).click();
        }

        // Dob
        dob.sendKeys(dobTxt);

        // Ssn
        int generatedSSN = HelperUtils.generateRandomSSN(Integer.parseInt(ssnTxt));
        String formattedSSN = String.format("%09d", generatedSSN).replaceFirst("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
        ssn.sendKeys(formattedSSN);
        System.out.println("SSN " + formattedSSN);

        // Email
        String generatedEmail = emailTxt + generatedSSN + "@gmail.com";
        email.sendKeys(generatedEmail);
        Constant.inputDataOnUI.put("email", generatedEmail);
        System.out.println("Email " + Constant.inputDataOnUI.get("email"));

        // Password
        password.sendKeys(passwordTxt);

        // Confirm Password
        confirmPassword.sendKeys(passwordTxt);
    }

    public void clickNextBtn() {
        nextBtn.click();
    }

}
