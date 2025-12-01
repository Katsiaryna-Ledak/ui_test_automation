package com.example.tests.steps;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

    public void shouldBeOnLoginPage() {
        log.info("Verifying login page is displayed with email and password fields");
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed!");
    }
}
