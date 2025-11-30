package com.example.tests.steps;

import com.example.framework.pages.SubscriptionsPage;

public class SubscriptionsSteps {

    private final SubscriptionsPage subscriptionsPage = new SubscriptionsPage();

    public void createNewSubscription() {
        subscriptionsPage.clickOnCreateSubscriptionButton();
    }
}
