package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log
public class TransactionsPage extends BasePage {

    @FindBy(id = "transactionTable_wrapper")
    WebElement transactionTableWrapper;

    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr[1]/td[4]")
    WebElement transactionTable;

    @Override
    public void verifyPage() {
        log.info("Verifying transactions page is displayed");
        Assert.assertTrue("Transaction table is not displayed", transactionTableWrapper.isDisplayed());
    }

    public void verifyTransaction() {
        log.info("Verifying the transaction is present in transaction history");
        String amountFromUI = transactionTable.getText().replace("$-", "").replace(".00", "");
        String amountFromCode = ConfigFileReaderUtils.getProperty("withdraw.amount");
        System.out.println("Amount from UI " + amountFromUI);
        System.out.println("Amount from code " + amountFromCode);

        Assert.assertEquals("The amount from code doesn`t match with the amount from UI", amountFromCode, amountFromUI);
    }

}
