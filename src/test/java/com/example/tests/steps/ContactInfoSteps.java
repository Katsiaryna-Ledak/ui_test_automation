package com.example.tests.steps;

import com.example.framework.pages.ContactInfoPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContactInfoSteps {

    private final ContactInfoPage contactInfoPage = new ContactInfoPage();

    public void verifyContactFieldValue(ContactInfoPage.ContactField field, String expectedValue) {
        log.info("Verify that filed {} has the expected va;lue {}", field, expectedValue);
        contactInfoPage.verifyFieldValue(field, expectedValue);
    }
}
