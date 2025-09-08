package com.demoautomationtestingin.web.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "https://demo.automationtesting.in";

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        // Gerencia o driver automaticamente
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new"); // ativar em CI se necessário
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Cria pasta de evidências para o teste atual
        try {
            Path p = Paths.get("target/evidence/ui/" + sanitize(testInfo.getDisplayName()));
            Files.createDirectories(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Navega para a home do site
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        try {
            // captura screenshot final (sempre)
            takeScreenshot("final-" + sanitize(testInfo.getDisplayName()) + ".png");
        } catch (Exception e) { e.printStackTrace(); }
        if (driver != null) {
            driver.quit();
        }
    }

    protected void takeScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            Path dest = Paths.get("target/evidence/ui/" + fileName);
            Files.copy(src.toPath(), dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String sanitize(String name) {
        return name.replaceAll("[^a-zA-Z0-9\\-_\\.]", "_");
    }
}