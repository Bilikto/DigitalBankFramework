package com.automation.pages;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log
public class TransactionsPage extends BasePage {

    @FindBy(id = "transactionTable_wrapper")
    WebElement transactionTable;

    @Override
    public void verifyPage() {
        log.info("Verifying transactions page is displayed");
        Assert.assertTrue("Transaction table is not displayed", transactionTable.isDisplayed());
    }

}
