package com.example.tests.ui.auth;

import com.example.framework.config.ConfigProperties;
import com.example.tests.ui.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LogoutTest extends BaseTest {

    @BeforeAll
    static void login() {
        open(ConfigProperties.get().baseUrl() + "/login");
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();
    }

    @Test
    @DisplayName("Verify that user is able to logout")
    void logoutTest() {
        homeSteps.verifyLoggedUserName();
        homeSteps.clickOnLoggedUserName();
        homeSteps.logout();
        loginSteps.shouldBeOnLoginPage();
    }
}
