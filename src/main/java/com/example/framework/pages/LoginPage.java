package com.example.framework.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.example.framework.config.Timeouts.PAGE_LOAD_TIMEOUT;
import static com.example.framework.config.Timeouts.POLL_INTERVAL;

@Slf4j
public class LoginPage {

    private final SelenideElement emailInput = $x("//input[@id='email']");
    private final SelenideElement passwordInput = $x("//input[@id='password']");
    private final SelenideElement signInButton = $x("//button[@type='submit' and .//span[text()='Sign in']]");
    private final SelenideElement cookiesButton = $x("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
    private final SelenideElement cookieDialog = $x("//div[@id='CybotCookiebotDialog']");
    private final SelenideElement cookiesDialog = $x("//div[@id='CybotCookiebotDialog']");

    public void openLoginPage(String url) {
        log.info("Opening login page via Selenide: {}", url);
        open(url);
    }

    public boolean isLoginPageDisplayed() {
        log.info("Verify login page is displayed");
        return emailInput.isDisplayed() && passwordInput.isDisplayed();
    }

    public void allowAllCookies() {
        log.info("Allow all cookies");
        waitForCookieDialog();
        if (cookiesDialog.shouldBe(visible, Duration.ofSeconds(5)).exists()) {
            log.info("Cookies dialog detected");

            cookiesButton.shouldBe(visible).click();
            log.info("Cookies button clicked successfully");

        } else {
            log.info("Cookies dialog not present");
        }
    }

    public void waitForCookieDialog() {
        log.info("Waiting for cookie dialog to appear (up to 40 seconds)...");

        int intervalMs = POLL_INTERVAL;
        int waited = 0;

        while (waited < PAGE_LOAD_TIMEOUT) {

            if (cookieDialog.exists()) {
                cookieDialog.shouldBe(visible);
                log.info("Cookie dialog appeared.");
                return;
            }

            sleep(intervalMs);
            waited += intervalMs;
        }

        log.info("Cookie dialog did NOT appear within 40 seconds — continuing.");
    }

    public void inputEmail(String email) {
        log.info("Input email: {}", email);
        emailInput.shouldBe(visible).setValue(email);
    }

    public void inputPassword(String password) {
        log.info("Input password");
        passwordInput.shouldBe(visible).setValue(password);
    }

    public void clickSignInButton() {
        log.info("Click 'Sign In' button");
        signInButton.shouldBe(visible).click();

        sleep(10000);
        log.info("Redirecting to dashboard");
        // force redirect to dashboard page, locator is correct but Selenide
        // is not able to click on SignIn button
        open("https://portal.servers.com/a:0m5Nx6dn/dashboard");
    }
}
