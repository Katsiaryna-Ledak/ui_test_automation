package com.example.tests.ui;

import com.codeborne.selenide.Selenide;
import com.example.framework.driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setupDriver() {
        DriverManager.setupDriver();
    }

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }
}
