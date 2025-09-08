package com.demoautomationtestingin.web.tests;

import com.demoautomationtestingin.web.base.BaseTest;
import com.demoautomationtestingin.web.pages.AlertsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlertsTest extends BaseTest {

    @Test
    @DisplayName("UI-WEB-005 - Aceitar alerta simples")
    public void ui_web_005_acceptSimpleAlert() {
        driver.get(BASE_URL + "/Alerts.html");

        AlertsPage alerts = new AlertsPage(driver, wait);
        alerts.clickAlertWithOk();
        String alertText = alerts.acceptAlert();

        Assertions.assertTrue(alertText.toLowerCase().contains("alert"), "I am an alert box!");
    }
}
