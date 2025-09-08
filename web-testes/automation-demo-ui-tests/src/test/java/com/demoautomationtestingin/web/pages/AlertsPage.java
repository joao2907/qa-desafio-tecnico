package com.demoautomationtestingin.web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By alertWithOkBtn = By.xpath("//button[@onclick='alertbox()']");
    private By alertWithOkCancelBtn = By.xpath("//button[@onclick='confirmbox()']");
    private By alertWithTextboxBtn = By.xpath("//button[@onclick='promptbox()']");

    public AlertsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickAlertWithOk() {
        driver.findElement(alertWithOkBtn).click();
    }

    public void clickAlertWithOkCancel() {
        driver.findElement(alertWithOkCancelBtn).click();
    }

    public void clickAlertWithTextbox() {
        driver.findElement(alertWithTextboxBtn).click();
    }

    public String acceptAlert() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String dismissAlert() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    public void fillPrompt(String input) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(input);
        alert.accept();
    }
}
