package com.demoautomationtestingin.web.tests;

import com.demoautomationtestingin.web.base.BaseTest;
import com.demoautomationtestingin.web.pages.FramesPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest extends BaseTest {

    @Test
    @DisplayName("UI-WEB-008 - Interagir com frame simples")
    public void ui_web_008_simpleFrame() {
        driver.get(BASE_URL + "/Frames.html");

        FramesPage frames = new FramesPage(driver, wait);
        frames.switchToSingleFrame();
        frames.fillInput("Teste Frame");

        Assertions.assertTrue(frames.getInputValue().contains("Teste Frame"),
                "Esperava que o campo dentro do frame fosse preenchido.");
    }
}
