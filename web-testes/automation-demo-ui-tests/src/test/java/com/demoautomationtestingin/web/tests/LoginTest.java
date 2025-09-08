package com.demoautomationtestingin.web.tests;

import com.demoautomationtestingin.web.base.BaseTest;
import com.demoautomationtestingin.web.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    @Disabled("Bloqueado devido ao BUG UI-WEB-BUG-001 no cadastro de usuário")
    @DisplayName("UI-WEB-003 - Login válido (fluxo feliz)")
    public void ui_web_003_validLogin() {
        driver.get(BASE_URL + "/SignIn.html");

        LoginPage login = new LoginPage(driver, wait);
        login.setEmail("testuser@example.com");
        login.setPassword("123456");
        login.clickSignIn();

        boolean redirected = driver.getCurrentUrl().contains("Index")
                || driver.getTitle().toLowerCase().contains("welcome");
        Assertions.assertTrue(redirected, "Esperava redirecionamento após login válido.");
    }

    @Test
    @DisplayName("UI-WEB-004 — Login com Credenciais Inválidas")
    public void ui_web_004_invalidLogin() {
        driver.get(BASE_URL + "/SignIn.html");

        LoginPage login = new LoginPage(driver, wait);
        login.setEmail("fake@example.com");
        login.setPassword("wrongpass");
        login.clickSignIn();

        boolean hasError = driver.getPageSource().toLowerCase().contains("invalid")
                || driver.getPageSource().toLowerCase().contains("error");
        Assertions.assertTrue(hasError, "Esperava mensagem de erro ao tentar login inválido.");
    }
}