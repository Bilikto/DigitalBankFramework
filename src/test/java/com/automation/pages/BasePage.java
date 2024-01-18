package com.automation.pages;

import com.automation.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    WebDriver driver;

    public BasePage() {
        driver = WebDriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public abstract void verifyPage();

}
