package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.ResultSet;

@Log
public class TransactionsPage extends BasePage {

    @FindBy(id = "transactionTable_wrapper")
    WebElement transactionTable;

    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr[1]/td[4]")
    WebElement transactionAmount;

    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr[1]/td[3]")
    WebElement transactionDescription;

    @Override
    public void verifyPage() {
        log.info("Verifying transactions page is displayed");
        Assert.assertTrue("Transaction table is not displayed", transactionTable.isDisplayed());
    }

    public void verifyTransaction() {
        log.info("Verifying the transaction is present in transaction history");
        String amountFromUI = transactionAmount.getText().replace("$-", "").replace(".00", "");
        String amountFromCode = ConfigFileReaderUtils.getProperty("withdraw.amount");
        System.out.println("Amount from UI " + amountFromUI);
        System.out.println("Amount from code " + amountFromCode);

        Assert.assertEquals("The amount from code doesn`t match with the amount from UI", amountFromCode, amountFromUI);
    }

    public void verifyTransactionIsUpdatedInDatabase() {
        try {
            log.info("Verifying transaction withdraw in database");
            String transactionAmountFromUI = transactionAmount.getText().replace("$-", "").replace(".00", "");
            String transactionNumberFormUI = transactionDescription.getText().replace(" (WTH) - Online Withdrawl", "");

            String query = ConfigFileReaderUtils.getProperty("db.transaction") + "'" + transactionNumberFormUI+ "'";
            ResultSet rs = DatabaseUtils.executeQuery(query);
            rs.next();
            String transactionNumberFormDB = rs.getString("transaction_number").trim();
            String transactionAmountFromDB = rs.getString("amount").replace("-", "").replace(".00", "");

            System.out.println("transactionAmountFromUI " + transactionAmountFromUI);
            System.out.println("transactionNumberFormUI " + transactionNumberFormUI);
            System.out.println("transactionAmountFromDB " + transactionAmountFromDB);
            System.out.println("transactionNumberFormDB " + transactionNumberFormDB);

            Assert.assertEquals("Transaction amount is not matching with a database data", transactionAmountFromUI, transactionAmountFromDB);
            Assert.assertEquals("Transaction number is not matching with a database data", transactionNumberFormUI, transactionNumberFormDB);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
