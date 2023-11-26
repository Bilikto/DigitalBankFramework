package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.ResultSet;
import java.util.List;

public class SavingsAccountPage extends BasePage {

    @FindBy(id = "page-title")
    WebElement pageTitle;

    @FindBy(xpath = "//input[@id='Savings']/..")
    WebElement savings;

    @FindBy(xpath = "//input[@id='Money Market']/..")
    WebElement moneyMarket;

    @FindBy(xpath = "//input[@id='Individual']/..")
    WebElement individual;

    @FindBy(xpath = "//input[@id='Joint']/..")
    WebElement joint;

    @FindBy(id = "name")
    WebElement accountNameInput;

    @FindBy(id = "openingBalance")
    WebElement initialDepositAmount;

    @FindBy(id = "newSavingsSubmit")
    WebElement submitBtn;

    @FindBy(id = "new-account-msg")
    WebElement confirmationSuccessMsg;

    @FindBy(xpath = "//div[@class='col-md-6 col-lg-3']//div[@class='card-body']/div/small")
    List<WebElement> listOfAccounts;

    @FindBy(xpath="//div[@class='card-body']/label/following-sibling::div[1]")
    List<WebElement> listOfAccountNames;

    /*************************************************************************************
     **************************************** METHODS ************************************
     ************************************************************************************/

    @Override
    public void verifyPage() {
        Assert.assertTrue("The saving account page title is not displayed", pageTitle.isDisplayed());
    }

    public void verifyNewAccountIsCreated() {
        Assert.assertTrue("A new account confirmation message is not displayed on the page", confirmationSuccessMsg.getText().contains("Successfully created"));
    }

    public void selectCheckingAccountType() {
        String accType = ConfigFileReaderUtils.getProperty("savAcc.type");

        if (accType.equalsIgnoreCase(savings.getText().trim())) {
            savings.click();
        } else {
            moneyMarket.click();
        }
    }

    public void selectSavingsAccountOwnership() {
        String ownershipType= ConfigFileReaderUtils.getProperty("savAcc.ownership");

        if (ownershipType.equals(individual.getText().trim())) {
            individual.click();
        } else {
            joint.click();
        }
    }

    public void provideSavingsAccountName() {
        String accName = ConfigFileReaderUtils.getProperty("savAcc.name");
        accountNameInput.sendKeys(accName);
    }

    public void provideSavingsInitialDepositAmount() {
        String depositAmount = ConfigFileReaderUtils.getProperty("savAcc.deposit");
        initialDepositAmount.sendKeys(depositAmount);
    }

    public void clickOnSubmitBtn() {
        submitBtn.click();
    }

    public void verifyNewSavingAccountSavedInDatabase() {
        try {
            String accountNameFromCode = ConfigFileReaderUtils.getProperty("savAcc.name");
            String expectedAccNumberFromUI = "";
            String actualAccNumberFromDB = "";

            for(WebElement el : listOfAccountNames) {
                if(el.getText().equalsIgnoreCase(accountNameFromCode)) {
                    for(WebElement ac : listOfAccounts) {
                        if(ac.getText().contains("Account Number:")) {
                            expectedAccNumberFromUI = ac.getText().replace("Account Number: ", "");
                        }
                    }
                    break;
                }
            }

            String query = ConfigFileReaderUtils.getProperty("db.account") + "\'" + expectedAccNumberFromUI + "\'";
            ResultSet res = DatabaseUtils.executeQuery(query);
            res.next();
            actualAccNumberFromDB = res.getString("account_number");

            System.out.println("Expected account number from UI " + expectedAccNumberFromUI);
            System.out.println("Actual account number from DB " + expectedAccNumberFromUI);

            Assert.assertEquals("The account numbers are not matching", expectedAccNumberFromUI, actualAccNumberFromDB);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
