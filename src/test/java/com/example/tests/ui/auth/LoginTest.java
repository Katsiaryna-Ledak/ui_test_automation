package com.example.tests.ui.auth;

import com.example.tests.ui.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Verify that user can login and HomePage elements are visible")
    void loginTest() {
        loginSteps.openLoginPage();
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();

        homeSteps.verifyLabelIsVisible();
        homeSteps.verifyLoggedUserName();
    }
}