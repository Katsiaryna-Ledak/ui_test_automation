package com.example.tests.ui.account_settings.subscriptions;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.NewContactPage;
import com.example.framework.pages.SideBarPage;
import com.example.tests.ui.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class DeleteSubscriptionTest extends BaseTest {

    @BeforeAll
    static void login() {
        open(ConfigProperties.get().baseUrl() + "/login");
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();
    }

    @BeforeEach
    void createSubscription() {
        homeSteps.verifyLabelIsVisible();
        sideBarSteps.clickParentOnly(SideBarPage.SideBarParentItem.ACCOUNT_SETTINGS);
        subscriptionsSteps.verifySubscriptionAreaIsVisible();

        subscriptionsSteps.clickOnCreateNewSubscription();
        newContactSteps.fillMandatoryFields(FIRST_NAME, LAST_NAME, EMAIL, NewContactPage.JobRole.TECHNICAL);
        newContactSteps.fillNonMandatoryFields(MIDDLE_NAME, NICKNAME, COMMENT, COMPANY_NAME, JOB_TITLE, JOB_ROLE,
                PHONE, SECONDARY_EMAIL);
        newContactSteps.clickCreateButton();
    }

    @Test
    @DisplayName("Verify that subscription could be deleted")
    void deleteSubscriptionTest() {
        open(ConfigProperties.get().baseUrl() + "account");

        subscriptionsSteps.deleteSubscriptionByEmail(EMAIL);
        subscriptionsSteps.confirmDeleteSubscription();
        subscriptionsSteps.verifyUserDeleted(FULL_NAME);
    }
}
