package com.example.tests.steps;

import com.example.framework.pages.NewContactPage;
import com.example.framework.pages.SubscriptionsPage;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SubscriptionsSteps {

    private final SubscriptionsPage subscriptionsPage = new SubscriptionsPage();

    public void clickOnCreateNewSubscription() {
        subscriptionsPage.clickOnCreateSubscriptionButton();
    }

    public void verifySubscriptionAreaIsVisible() {
        log.info("Verify Subscriptions area is visible");

        boolean isVisible = subscriptionsPage.verifySubscriptionsAreaIsVisible();
        assertTrue(isVisible, "Subscriptions area is NOT visible on Account Settings page!");
    }

    public void verifyTopRowUser(String expectedName, NewContactPage.JobRole expectedJob, String expectedEmail) {
        log.info("Step: verify top row user with name = '{}', job = '{}', email = '{}'", expectedName, expectedJob.getLabel(), expectedEmail);
        assertTrue(subscriptionsPage.isUserInTopRow(expectedName, expectedJob.getLabel(), expectedEmail),
                "Top row does not match expected user data");
    }

    public void clickOnUserNameInTable(String fullName) {
        log.info("Click on user name to open contact details");
        subscriptionsPage.clickUserByName(fullName);
    }

    public void editContactInfo() {
        log.info("Click on pencil icon to edit info");
        subscriptionsPage.clickEditIcon();
    }

    public void deleteSubscriptionByEmail(String email) {
        subscriptionsPage.clickDeleteIconByUserEmail(email);
    }

    public void confirmDeleteSubscription() {
        subscriptionsPage.clickOnDeleteSubscriptionIcon();
    }

    public void verifyUserDeleted(String fullName) {
        log.info("Verify user '{}' was deleted", fullName);
        assertTrue(subscriptionsPage.isUserAbsent(fullName),
                "User '" + fullName + "' is still present in the table!");
    }
}
