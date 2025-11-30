package com.example.tests.steps;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;
import com.codeborne.selenide.SelenideElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class LoginSteps extends BaseSteps {

    private final LoginPage loginPage = new LoginPage();

    public void openLoginPage() {
        loginPage.openLoginPage(ConfigProperties.get().baseUrl() + "/login");
    }

    public void allowAllCoolies() {
        loginPage.allowAllCookies();
    }

    public void loginWithConfig() {
        loginPage.inputEmail(ConfigProperties.get().email());
        loginPage.inputPassword(ConfigProperties.get().password());
        loginPage.clickSignInButton();
    }

    public void verifyLoggedUserName() {
        SelenideElement userName = loginPage.getUserNameElement();
        userName.shouldBe(com.codeborne.selenide.Condition.visible);
        String actualName = userName.getText();
        log.info("Logged in user: {}", actualName);

        assertEquals(ConfigProperties.get().name(), actualName, "Logged user name is incorrect!");
    }

    public void verifyLabelIsVisible() {
        SelenideElement label = loginPage.getWelcomeLabelElement();
        label.shouldBe(com.codeborne.selenide.Condition.visible);
        log.info("'Servers.com' label is visible on HomePage");
    }
}
