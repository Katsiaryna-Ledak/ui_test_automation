package com.example.framework.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class SubscriptionsPage {

    private final SelenideElement createSubscriptionButton = $x("//a[@title='Create']");

    public void clickOnCreateSubscriptionButton() {
        log.info("Click 'Create Subscription' button");
        createSubscriptionButton.click();
    }
}
