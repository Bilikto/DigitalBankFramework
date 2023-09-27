package com.automation.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id="page-title")
    WebElement dashboardTitle;

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
        Assert.assertTrue("The Home page is not displayed", dashboardTitle.isDisplayed());
    }

    public void clickOnCheckingMenu() {
        checkingMenu.click();
    }

    public void clickOnSavingsMenu() {
        savingsMenu.click();
    }

    public void clickOnNewCheckingLink() {
        newCheckingLink.click();
    }

    public void clickOnNewSavingsLink() {
        newSavingsLink.click();
    }

}
