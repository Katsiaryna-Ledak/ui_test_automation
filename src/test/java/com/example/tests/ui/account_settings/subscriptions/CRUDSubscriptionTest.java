package com.example.tests.ui.account_settings.subscriptions;

import com.example.framework.config.ConfigProperties;
import com.example.tests.steps.LoginSteps;
import com.example.tests.steps.SideBarSteps;
import com.example.tests.steps.SubscriptionsSteps;
import com.example.tests.ui.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class CRUDSubscriptionTest extends BaseTest {

    private static LoginSteps loginSteps;

    @BeforeAll
    static void setUp() {
        loginSteps = new LoginSteps();

        open(ConfigProperties.get().baseUrl() + "/login");
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();
    }

    @Test
    @DisplayName("Verify that user can login and HomePage elements are visible")
    void createSubscriptionTest() {

    }
}
