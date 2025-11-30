package com.example.framework.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class LoginPage {

    private final SelenideElement emailInput = $x("//input[@id='email']");
    private final SelenideElement passwordInput = $x("//input[@id='password']");
    private final SelenideElement signInButton = $x("//button[@type='submit' and .//span[text()='Sign in']]");
    private final SelenideElement cookiesButton = $x("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
    private final SelenideElement userNameTitle = $x("//button//span[@title]");
    private final SelenideElement welcomeLabel = $x("//a[@title='Servers.com']");
    SelenideElement cookiesDialog = $x("//div[@id='CybotCookiebotDialog']");

    public void openLoginPage(String url) {
        log.info("Opening login page via Selenide: {}", url);
        open(url);
    }

    public void allowAllCookies() {
        log.info("Allow all cookies");
        if (cookiesDialog.shouldBe(visible, Duration.ofSeconds(5)).exists()) {
            log.info("Cookies dialog detected");

            cookiesButton.shouldBe(visible).click();
            log.info("Cookies button clicked successfully");

        } else {
            log.info("Cookies dialog not present");
        }
    }

    public void inputEmail(String email) {
        log.info("Input email: {}", email);
        emailInput.shouldBe(visible).setValue(email);
        log.info("Email entered successfully");
    }

    public void inputPassword(String password) {
        log.info("Input password");
        passwordInput.shouldBe(visible).setValue(password);
        log.info("Password entered successfully");
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

    public SelenideElement getUserNameElement() {
        return userNameTitle.shouldBe(visible);
    }

    public SelenideElement getWelcomeLabelElement() {
        return welcomeLabel.shouldBe(visible);
    }
}
