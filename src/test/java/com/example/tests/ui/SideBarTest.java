package com.example.tests.ui;

import com.example.framework.config.ConfigProperties;
import com.example.framework.pages.LoginPage;
import com.example.tests.steps.SideBarSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SideBarTest {

    private static LoginPage loginPage;
    private static SideBarSteps sideBarSteps;

    @BeforeAll
    static void setup() {
        loginPage = new LoginPage();
        loginPage.openLoginPage(ConfigProperties.get().baseUrl() + "/login");
        loginPage.allowAllCookies();
        loginPage.inputEmail(ConfigProperties.get().email());
        loginPage.inputPassword(ConfigProperties.get().password());
        loginPage.clickSignInButton();

        sideBarSteps = new SideBarSteps();
    }

    @Test
    @DisplayName("Verify redirection to correct URL after clicking on SideBar items")
    void sideBarMenuTest() {
        sideBarSteps.checkAllSidebarItems();
    }
}
