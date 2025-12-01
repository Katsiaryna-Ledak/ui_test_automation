package com.example.framework.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.example.framework.config.Timeouts;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class SubscriptionsPage {

    private final SelenideElement createSubscriptionButton = $x("//a[@title='Create']");
    private final SelenideElement subscriptionsAreaTitle = $x("//div[@role='region']//h2//span[normalize-space()='Subscriptions']");
    private final SelenideElement editIcon = $x("//button[@title='Edit']");
    private final SelenideElement deleteSubscriptionIcon = $x("//button[@title='Delete']");
    private final String userNameXpathTemplate = "//span[text()='%s']";

    public void clickOnCreateSubscriptionButton() {
        log.info("Click 'Create Subscription' button");
        createSubscriptionButton.click();
    }

    public boolean verifySubscriptionsAreaIsVisible() {
        log.info("Verify Subscriptions area is visible on Accounts settings page");

        return subscriptionsAreaTitle.shouldBe(
                visible,
                Duration.ofMillis(Timeouts.PAGE_LOAD_TIMEOUT)
        ).isDisplayed();
    }

    private ElementsCollection getNameCells() {
        return $$x("//td[@data-label='Name']");
    }

    private ElementsCollection getTypeCells() {
        return $$x("//td[@data-label='Type']");
    }

    private ElementsCollection getEmailCells() {
        return $$x("//td[@data-label='Emails']//a");
    }

    public boolean isUserInTopRow(String expectedName, String expectedJob, String expectedEmail) {
        log.info("Verifying user in top row: name='{}', job='{}', email='{}'", expectedName, expectedJob, expectedEmail);

        SelenideElement nameCell = getNameCells().first();
        SelenideElement typeCell = getTypeCells().first();
        SelenideElement emailCell = getEmailCells().first();

        String actualName = nameCell.attr("title").trim();
        String actualJob = typeCell.$("span").getText().trim();
        String actualEmail = emailCell.attr("title").trim();

        log.info("Found in top row: name='{}', job='{}', email='{}'", actualName, actualJob, actualEmail);

        return actualName.equals(expectedName) &&
                actualJob.equals(expectedJob) &&
                actualEmail.equals(expectedEmail);
    }

    public void clickUserByName(String fullName) {
        log.info("Click on user with name: {}", fullName);
        SelenideElement userElement = $x(String.format(userNameXpathTemplate, fullName));
        userElement.shouldBe(visible).click();
    }

    public void clickEditIcon() {
        log.info("Click Edit icon");
        editIcon.shouldBe(visible).click();
    }

    public void clickDeleteIconByUserEmail(String email) {
        log.info("Click Delete icon for user with email: {}", email);

        String xpath = String.format(
                "//a[@title='%s']/ancestor::tr//td//button[@role='button']",
                email
        );

        $x(xpath)
                .shouldBe(Condition.visible)
                .click();
    }

    public void clickOnDeleteSubscriptionIcon() {
        log.info("Click 'Delete Subscription' button");
        deleteSubscriptionIcon.click();
        sleep(1000);
    }

    public boolean isUserAbsent(String fullName) {
        log.info("Checking that user '{}' is absent in the subscriptions table", fullName);

        ElementsCollection matchingUsers = $$x("//td[@data-label='Name']//span[@title='" + fullName + "']");

        boolean absent = matchingUsers.isEmpty();
        log.info("User '{}' is absent: {}", fullName, absent);

        return absent;
    }
}
