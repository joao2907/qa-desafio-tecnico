package com.demoautomationtestingin.web.tests;
import com.demoautomationtestingin.web.base.BaseTest;
import com.demoautomationtestingin.web.pages.RegistrationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("UI-WEB-001 - Cadastro de Novo Usuário (fluxo feliz)")
    public void ui_web_001_registerNewUser() {
        // Navegar diretamente para a página de registro (caso o BaseTest abra o home)
        driver.get(BASE_URL + "/Register.html");

        RegistrationPage reg = new RegistrationPage(driver, wait);
        reg.setFirstName("João Víctor");
        reg.setLastName("Santos Campos");
        reg.setAddress("Rua Exemplo, 123");
        reg.setEmail("joao.victor@example.com");
        reg.setPhone("1234567890");
        reg.selectGenderMale();
        reg.selectHobbyCricket();
        reg.selectSkill("Java");

        // Validação extra para o bug do Country
        try {
            reg.selectCountry("Australia");
        } catch (Exception e) {
            Assertions.fail("BUG UI-WEB-BUG-001: O campo 'Country' não exibe opções e impede o cadastro de usuário.");
        }

        reg.selectDOB("2001", "July", "29");
        reg.setPassword("SenhaForte123");
        reg.clickSubmit();

        // Como o bug bloqueia o fluxo, esta asserção não será atingida, mas mantemos para quando o bug for corrigido
        boolean success = driver.getCurrentUrl().contains("WebTable") || driver.getTitle().toLowerCase().contains("success");
        Assertions.assertTrue(success, "Esperava redirecionamento/indicador de sucesso após cadastro.");
    }

    @Test
    @DisplayName("UI-WEB-002 - Cadastro com campos obrigatórios vazios (fluxo negativo)")
    public void ui_web_002_registerMissingMandatoryFields() {
        driver.get(BASE_URL + "/Register.html");

        RegistrationPage reg = new RegistrationPage(driver, wait);
        // Intencionalmente não preenche campos obrigatórios:
        reg.clickSubmit();

        // Aqui você deve validar a existência de mensagens de erro; exemplo genérico:
        // procure por algum elemento com classe 'error' ou similar
        boolean hasError = driver.getPageSource().contains("required") || driver.getPageSource().toLowerCase().contains("required");
        Assertions.assertTrue(hasError, "Esperava mensagens de erro para campos obrigatórios.");
    }
}