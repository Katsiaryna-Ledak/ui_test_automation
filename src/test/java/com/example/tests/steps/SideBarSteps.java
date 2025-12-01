package com.example.tests.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.example.framework.pages.SideBarPage;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SideBarSteps {

    private final SideBarPage sideBarPage = new SideBarPage();

    public void checkAllSidebarItems() {

        for (SideBarPage.SideBarParentItem parent : SideBarPage.SideBarParentItem.values()) {

            sideBarPage.openParent(parent);

            for (SideBarPage.SideBarChildItem child : SideBarPage.SideBarChildItem.values()) {
                if (child.getParent() == parent) {
                    sideBarPage.clickChild(child);
                    sleep(300);
                    verifyUrl(child.getUrl());
                }
            }

            sideBarPage.closeParent(parent);
        }
    }

    public void verifyUrl(String expectedUrl) {
        int retries = 5;
        while (retries-- > 0) {
            String currentUrl = WebDriverRunner.url();
            if (currentUrl.contains(expectedUrl)) return;
            sleep(300);
        }
        assertTrue(WebDriverRunner.url().contains(expectedUrl), "Expected URL: " + expectedUrl + "\nActual url was: " + WebDriverRunner.url());
    }

    public void clickParentOnly(SideBarPage.SideBarParentItem parent) {
        sideBarPage.openParent(parent);
    }
}
