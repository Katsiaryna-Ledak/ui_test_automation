package com.example.framework.pages;

import com.codeborne.selenide.Selenide;

/**
 * Base class for all page objects
 * Contains common methods that can be used by all pages
 */
public class BasePage {

    public void openUrl(String path) {
        Selenide.open(path);
    }

    public String getPageTitle() {
        return Selenide.title();
    }
}
