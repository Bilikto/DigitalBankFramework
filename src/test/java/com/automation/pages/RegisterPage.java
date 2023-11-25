package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log
public class RegisterPage extends BasePage {

    @FindBy(id = "address")
    WebElement address;

    @FindBy(id = "locality")
    WebElement locality;

    @FindBy(id = "region")
    WebElement region;

    @FindBy(id = "postalCode")
    WebElement postalCode;

    @FindBy(id = "country")
    WebElement country;

    @FindBy(id = "homePhone")
    WebElement homePhone;

    @FindBy(id = "mobilePhone")
    WebElement mobilePhone;

    @FindBy(id = "workPhone")
    WebElement workPhone;

    @FindBy(id = "agree-terms")
    WebElement agreeTerms;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement registerBtn;

    /*************************************************************************************
     **************************************** METHODS ************************************
     ************************************************************************************/

    @Override
    public void verifyPage() {
        log.info("Verifying the user is on Register page");
        Assert.assertTrue("The register page is not displayed", address.isDisplayed());
        Assert.assertTrue("The register page is not displayed", locality.isDisplayed());
    }

    public void userFillsOutInformationForRegistration() {
        log.info("User is entering personal information for sign up");
        String addressTxt = ConfigFileReaderUtils.getProperty("signup.address");
        String localityTxt = ConfigFileReaderUtils.getProperty("signup.locality");
        String regionTxt = ConfigFileReaderUtils.getProperty("signup.region");
        String postalCodeTxt = ConfigFileReaderUtils.getProperty("signup.postalCode");
        String countryTxt = ConfigFileReaderUtils.getProperty("signup.country");
        String homePhoneTxt = ConfigFileReaderUtils.getProperty("signup.homePhone");
        String mobilePhoneTxt = ConfigFileReaderUtils.getProperty("signup.mobilePhone");
        String workPhoneTxt = ConfigFileReaderUtils.getProperty("signup.workPhone");


        // Address
        address.sendKeys(addressTxt);

        // Locality
        locality.sendKeys(localityTxt);

        // Region
        region.sendKeys(regionTxt);

        // Postal Code
        postalCode.sendKeys(postalCodeTxt);

        // Country
        country.sendKeys(countryTxt);

        // Home Phone
        homePhone.sendKeys(homePhoneTxt);

        // Mobile Phone
        mobilePhone.sendKeys(mobilePhoneTxt);

        // Work Phone
        workPhone.sendKeys(workPhoneTxt);

        // Agree Terms
        agreeTerms.click();
    }

    public void clickRegisterBtn() {
        log.info("User is clicking on the Register page");
        registerBtn.click();
    }

}
