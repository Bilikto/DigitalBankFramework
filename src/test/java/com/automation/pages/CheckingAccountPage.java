package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import com.automation.utils.DatabaseUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CheckingAccountPage extends BasePage {

    @FindBy(id = "page-title")
    WebElement title;

    @FindBy(xpath = "//input[@id='Standard Checking']/..")
    WebElement standardChecking;

    @FindBy(xpath = "//input[@id='Interest Checking']/..")
    WebElement interestChecking;

    @FindBy(xpath = "//input[@id='Individual']/..")
    WebElement individual;

    @FindBy(xpath = "//input[@id='Joint']/..")
    WebElement joint;

    @FindBy(id = "name")
    WebElement accountNameInput;

    @FindBy(id = "openingBalance")
    WebElement initialDepositAmount;

    @FindBy(id = "newCheckingSubmit")
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
        Assert.assertTrue("The checking account page title is not displayed", title.isDisplayed());
    }

    public void verifyNewAccountIsCreated() {
        Assert.assertTrue("A new account confirmation message is not displayed on the page", confirmationSuccessMsg.getText().contains("Successfully created"));
    }

    public void selectCheckingAccountType() {
        String accType = ConfigFileReaderUtils.getProperty("checkAcc.type");

        if (accType.equalsIgnoreCase(standardChecking.getText().trim())) {
            standardChecking.click();
        } else {
            interestChecking.click();
        }
    }

    public void selectAccountOwnership() {
        String ownershipType= ConfigFileReaderUtils.getProperty("checkAcc.ownership");

        if (ownershipType.equals(individual.getText().trim())) {
            individual.click();
        } else {
            joint.click();
        }
    }

    public void provideAccountName() {
        String accName = ConfigFileReaderUtils.getProperty("checkAcc.name");
        accountNameInput.sendKeys(accName);
    }

    public void provideInitialDepositAmount() {
        String depositAmount = ConfigFileReaderUtils.getProperty("checkAcc.deposit");
        initialDepositAmount.sendKeys(depositAmount);
    }

    public void clickOnSubmitBtn() {
        submitBtn.click();
    }

    public void verifyNewCheckingAccountSavedInDatabase() {
        try {
            String accountNameFromCode = ConfigFileReaderUtils.getProperty("checkAcc.name");
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
