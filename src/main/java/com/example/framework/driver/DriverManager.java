package com.example.framework.driver;

import com.codeborne.selenide.Configuration;
import com.example.framework.config.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();

        Configuration.baseUrl = ConfigProperties.get().baseUrl();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = ConfigProperties.get().timeout();
        Configuration.headless = ConfigProperties.get().headless();

        // Maximize browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;
    }
}
