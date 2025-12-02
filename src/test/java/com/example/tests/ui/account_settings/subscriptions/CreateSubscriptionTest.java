package com.example.tests.ui.account_settings.subscriptions;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.NewContactPage;
import com.example.framework.pages.SideBarPage;
import com.example.tests.ui.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateSubscriptionTest extends BaseTest {

    @BeforeAll
    static void login() {
        open(ConfigProperties.get().baseUrl() + "/login");
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();
    }

    @Test
    @DisplayName("Verify that new subscription could be created")
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
    void deleteSubscriptionAfterAll() {
        try {
            open(ConfigProperties.get().baseUrl() + "account");

            subscriptionsSteps.deleteSubscriptionByEmail(EMAIL);
            subscriptionsSteps.confirmDeleteSubscription();

            log.info("Test subscription '{}' deleted successfully", EMAIL);
        } catch (Exception e) {
            log.error("Failed to delete test subscription '{}': {}", EMAIL, e.getMessage(), e);
        }
    }
}
