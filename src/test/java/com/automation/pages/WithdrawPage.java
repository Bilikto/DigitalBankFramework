package com.automation.pages;

import com.automation.utils.ConfigFileReaderUtils;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Log
public class WithdrawPage extends BasePage {

    @FindBy(id="page-title")
    WebElement withdrawPageTitle;

    @FindBy(id="selectedAccount")
    WebElement selectWithdrawAccount;

    @FindBy(id="amount")
    WebElement withdrawAmountInput;

    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    WebElement submitBtn;

    @Override
    public void verifyPage() {
        log.info("Verifying withdraw page is displayed");
        Assert.assertTrue("Withdraw page title is not displayed", withdrawPageTitle.isDisplayed());
    }

    public void selectAccountToWithdraw() {
        log.info("Selecting account to withdraw");
        Select select = new Select(selectWithdrawAccount);
        select.selectByValue("7039");
    }

    public void enterWithdrawAmount() {
        log.info("Entering withdraw amount");
        String amount = ConfigFileReaderUtils.getProperty("withdraw.amount");
        withdrawAmountInput.sendKeys(amount);
    }

    public void clickSubmitBtn() {
        log.info("Clicking Submit button");
        submitBtn.click();
    }

}
