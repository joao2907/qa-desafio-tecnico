package com.demoautomationtestingin.web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By email = By.xpath("//input[@placeholder='E mail']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By signInBtn = By.id("enterbtn");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setEmail(String value) {
        driver.findElement(email).sendKeys(value);
    }

    public void setPassword(String value) {
        driver.findElement(password).sendKeys(value);
    }

    public void clickSignIn() {
        driver.findElement(signInBtn).click();
    }
}