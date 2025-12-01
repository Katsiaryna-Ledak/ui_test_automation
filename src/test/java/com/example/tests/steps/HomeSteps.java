package com.example.tests.steps;

import com.codeborne.selenide.SelenideElement;
import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.HomePage;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.sleep;
import static com.example.framework.config.Timeouts.PAGE_LOAD_TIMEOUT;
import static com.example.framework.config.Timeouts.POLL_INTERVAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class HomeSteps {

    private final HomePage homePage = new HomePage();

    public void verifyLoggedUserName() {
        SelenideElement userName = homePage.getUserNameElement();
        userName.shouldBe(com.codeborne.selenide.Condition.visible);
        String actualName = userName.getText();
        log.info("Logged in user: {}", actualName);

        assertEquals(ConfigProperties.get().name(), actualName, "Logged user name is incorrect!");
    }

    public void verifyLabelIsVisible() {
        SelenideElement label = homePage.getWelcomeLabel();

        int intervalMs = POLL_INTERVAL;
        int waited = 0;

        while (waited < PAGE_LOAD_TIMEOUT) {
            if (label.exists() && label.isDisplayed()) {
                log.info("'Servers.com' label is visible on HomePage");
                return;
            }

            sleep(intervalMs);
            waited += intervalMs;
        }

        throw new AssertionError("'Servers.com' welcome label did not become visible within 60 seconds");
    }

    public void clickOnLoggedUserName() {
        homePage.clickUserName();
        log.info("Clicked on logged user name");
    }

    public void logout() {
        homePage.clickLogout();
        log.info("Clicked on Logout");
    }
}
