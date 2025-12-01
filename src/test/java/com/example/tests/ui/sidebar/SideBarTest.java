package com.example.tests.ui.sidebar;

import com.example.framework.config.ConfigProperties;
import com.example.tests.ui.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SideBarTest extends BaseTest {

    @BeforeAll
    static void login() {
        open(ConfigProperties.get().baseUrl() + "/login");
        loginSteps.allowAllCoolies();
        loginSteps.loginWithConfig();
    }

    @Test
    @DisplayName("Verify redirection to correct URL after clicking on SideBar items")
    void sideBarMenuTest() {
        homeSteps.verifyLabelIsVisible();
        sideBarSteps.checkAllSidebarItems();
    }
}
