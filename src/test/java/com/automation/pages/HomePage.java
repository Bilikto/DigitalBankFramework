package com.automation.pages;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log
public class HomePage extends BasePage {

    @FindBy(id="page-title")
    private WebElement dashboardTitle;

    @FindBy(id = "checking-menu")
    WebElement checkingMenu;

    @FindBy(id = "savings-menu")
    WebElement savingsMenu;

    @FindBy(id = "new-checking-menu-item")
    WebElement newCheckingLink;

    @FindBy(id = "new-savings-menu-item")
    WebElement newSavingsLink;

    /*************************************************************************************
     **************************************** METHODS ************************************
     ************************************************************************************/

    @Override
    public void verifyPage() {
        log.info("Verifying the user is on Home page");
        log.info("The Homepage title is " + dashboardTitle.getText());
        Assert.assertTrue("The Home page is not displayed", dashboardTitle.isDisplayed());
    }

    public void clickOnCheckingMenu() {
        log.info("User is clicking on Checking menu tab");
        checkingMenu.click();
    }

    public void clickOnSavingsMenu() {
        log.info("User is clicking on Saving menu tab");
        savingsMenu.click();
    }

    public void clickOnNewCheckingLink() {
        log.info("User is clicking on New Checking link");
        newCheckingLink.click();
    }

    public void clickOnNewSavingsLink() {
        log.info("User is clicking on New Saving link");
        newSavingsLink.click();
    }

}
