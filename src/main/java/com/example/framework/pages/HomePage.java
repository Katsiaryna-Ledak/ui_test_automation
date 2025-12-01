package com.example.framework.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final SelenideElement userNameTitle = $x("//button//span[@title]");
    @Getter
    private final SelenideElement welcomeLabel = $x("//a[@title='Servers.com']");
    private SelenideElement userMenu = $("ul[role='presentation']");
    private SelenideElement logoutButton = userMenu.$("li button");

    public SelenideElement getUserNameElement() {
        return userNameTitle.shouldBe(visible);
    }

    public void clickUserName() {
        userNameTitle.shouldBe(Condition.visible).click();
    }

    public void clickLogout() {
        userMenu.shouldBe(Condition.visible);
        logoutButton.shouldBe(Condition.visible).click();
        sleep(1000);
    }
}
