package com.demoautomationtestingin.web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inputField = By.xpath("//input[@type='text']");

    public FramesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void switchToSingleFrame() {
        driver.switchTo().frame(driver.findElement(By.id("singleframe")));
    }

    public void switchToNestedFrame() {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']")));
    }

    public void fillInput(String text) {
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(text);
    }

    public String getInputValue() {
        return driver.findElement(inputField).getAttribute("value");
    }
}
