package com.example.tests.ui.account_settings.subscriptions;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.NewContactPage;
import com.example.framework.pages.SideBarPage;
import com.example.tests.ui.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

public class CreateSubscriptionTest extends BaseTest {

    @BeforeAll
    static void login() {
        open(ConfigProperties.get().baseUrl() + "/login");
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();
    }

    @Test
    @DisplayName("Verify that new subscription can be created")
    void createSubscriptionTest() {
        homeSteps.verifyLabelIsVisible();
        sideBarSteps.clickParentOnly(SideBarPage.SideBarParentItem.ACCOUNT_SETTINGS);
        subscriptionsSteps.verifySubscriptionAreaIsVisible();

        subscriptionsSteps.clickOnCreateNewSubscription();
        newContactSteps.fillMandatoryFields(FIRST_NAME, LAST_NAME, EMAIL, NewContactPage.JobRole.TECHNICAL);
        newContactSteps.fillNonMandatoryFields(MIDDLE_NAME, NICKNAME, COMMENT, COMPANY_NAME, JOB_TITLE, JOB_ROLE,
                PHONE, SECONDARY_EMAIL);
        newContactSteps.clickCreateButton();

        open(ConfigProperties.get().baseUrl() + "account");
        refresh();
        subscriptionsSteps.verifyTopRowUser(FIRST_NAME + " " + LAST_NAME, NewContactPage.JobRole.TECHNICAL, EMAIL);
    }

    @AfterAll
    static void deleteSubscription() {
        refresh();
        subscriptionsSteps.deleteSubscriptionByEmail(EMAIL);
        subscriptionsSteps.confirmDeleteSubscription();
    }
}
