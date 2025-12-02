package com.example.tests.ui;

import com.codeborne.selenide.Selenide;
import com.example.framework.driver.DriverManager;
import com.example.framework.utils.RandomEmailGenerator;
import com.example.framework.utils.RandomStringsGenerator;
import com.example.tests.steps.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    // mandatory fields
    protected static final String FIRST_NAME = RandomStringsGenerator.generateFirstName();
    protected static final String LAST_NAME = RandomStringsGenerator.generateLastName();
    protected static final String FULL_NAME = FIRST_NAME + " " + LAST_NAME;
    protected static final String EMAIL = RandomEmailGenerator.generate();

    // non-mandatory fields
    protected final String MIDDLE_NAME = RandomStringsGenerator.generateMiddleName();
    protected final String NICKNAME = RandomStringsGenerator.generateNickname();
    protected final String COMMENT = RandomStringsGenerator.generateTestComment();
    protected final String COMPANY_NAME = RandomStringsGenerator.generateCompanyName();
    protected final String JOB_TITLE = RandomStringsGenerator.generateJobTitle();
    protected final String JOB_ROLE = RandomStringsGenerator.generateJobRole();
    protected final String PHONE = RandomStringsGenerator.generateCyprusPhoneNumber();
    protected final String SECONDARY_EMAIL = RandomEmailGenerator.generate();

    // steps
    protected static LoginSteps loginSteps = new LoginSteps();
    protected static SideBarSteps sideBarSteps = new SideBarSteps();
    protected static SubscriptionsSteps subscriptionsSteps = new SubscriptionsSteps();
    protected static NewContactSteps newContactSteps = new NewContactSteps();
    protected static ContactInfoSteps contactInfoSteps = new ContactInfoSteps();
    protected static HomeSteps homeSteps = new HomeSteps();

    @BeforeAll
    static void setupDriver() {
        DriverManager.setupDriver();
        // Allure listener
        com.codeborne.selenide.logevents.SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }
}
