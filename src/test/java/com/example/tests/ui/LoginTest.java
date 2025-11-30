package com.example.tests.ui;

import com.example.tests.steps.LoginSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    private final LoginSteps loginSteps = new LoginSteps();

    @Test
    @DisplayName("Verify that user can login and HomePage elements are visible")
    void loginTest() {
        loginSteps.openLoginPage();
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();

        loginSteps.verifyLabelIsVisible();
        loginSteps.verifyLoggedUserName();
    }
}