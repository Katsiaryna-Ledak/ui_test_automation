package com.example.tests.ui.account_settings.subscriptions;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.ContactInfoPage;
import com.example.framework.pages.NewContactPage;
import com.example.framework.pages.SideBarPage;
import com.example.framework.utils.RandomStringsGenerator;
import com.example.tests.ui.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateSubscriptionTest extends BaseTest {

    private final String GENERATED_MIDDLE_NAME = RandomStringsGenerator.generateMiddleName();

    @BeforeAll
    void login() {
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
    @DisplayName("Verify that subscription can be updated")
    void updateSubscriptionTest() {
        homeSteps.verifyLabelIsVisible();
        sideBarSteps.clickParentOnly(SideBarPage.SideBarParentItem.ACCOUNT_SETTINGS);
        subscriptionsSteps.verifySubscriptionAreaIsVisible();

        subscriptionsSteps.clickOnUserNameInTable(FIRST_NAME + " " + LAST_NAME);
        subscriptionsSteps.editContactInfo();
        newContactSteps.editField(NewContactPage.ContactField.MIDDLE_NAME, GENERATED_MIDDLE_NAME);
        newContactSteps.clickSaveButton();
        contactInfoSteps.verifyContactFieldValue(ContactInfoPage.ContactField.MIDDLE_NAME,
                GENERATED_MIDDLE_NAME);
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
