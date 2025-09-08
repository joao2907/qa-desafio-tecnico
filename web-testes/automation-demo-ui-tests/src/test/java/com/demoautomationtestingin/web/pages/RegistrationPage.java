package com.demoautomationtestingin.web.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // LOCATORS (exemplares — confirme no inspector)
    private By firstName = By.xpath("//input[@placeholder='First Name']");
    private By lastName = By.xpath("//input[@placeholder='Last Name']");
    private By address = By.tagName("textarea");
    private By email = By.xpath("//input[@type='email']");
    private By phone = By.xpath("//input[@type='tel']");
    private By genderMale = By.xpath("//input[@value='Male']");
    private By hobbiesCricket = By.xpath("//input[@id='checkbox1']");
    private By skillsSelect = By.id("Skills");
    private By countrySelect = By.id("countries");
    private By yearSelect = By.id("yearbox");
    private By monthSelect = By.xpath("//select[@placeholder='Month']");
    private By daySelect = By.id("daybox");
    private By password = By.id("firstpassword");
    private By confirmPassword = By.id("secondpassword");
    private By submitButton = By.id("submitbtn"); // este id é ilustrativo

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setFirstName(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(value);
    }

    public void setLastName(String value) {
        driver.findElement(lastName).sendKeys(value);
    }

    public void setAddress(String value) {
        driver.findElement(address).sendKeys(value);
    }

    public void setEmail(String value) {
        driver.findElement(email).sendKeys(value);
    }

    public void setPhone(String value) {
        driver.findElement(phone).sendKeys(value);
    }

    public void selectGenderMale() {
        driver.findElement(genderMale).click();
    }

    public void selectHobbyCricket() {
        driver.findElement(hobbiesCricket).click();
    }

    public void selectSkill(String skill) {
        Select s = new Select(driver.findElement(skillsSelect));
        s.selectByVisibleText(skill);
    }

    public void selectCountry(String country) {
        Select s = new Select(driver.findElement(countrySelect));
        s.selectByVisibleText(country);
    }

    public void selectDOB(String year, String month, String day) {
        new Select(driver.findElement(yearSelect)).selectByVisibleText(year);
        new Select(driver.findElement(monthSelect)).selectByVisibleText(month);
        new Select(driver.findElement(daySelect)).selectByVisibleText(day);
    }

    public void setPassword(String p) {
        driver.findElement(password).sendKeys(p);
        driver.findElement(confirmPassword).sendKeys(p);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }
}