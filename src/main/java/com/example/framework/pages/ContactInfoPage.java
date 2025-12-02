package com.example.framework.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class ContactInfoPage {

    public void verifyFieldValue(ContactField field, String expectedValue) {
        log.info("Verify that field '{}' has value '{}'", field.getLabel(), expectedValue);

        SelenideElement valueElement = $x("//div[text()='" + field.getLabel() + "']//following-sibling::div//span//span")
                .shouldBe(visible, Duration.ofSeconds(10));

        String actualValue = valueElement.getText();
        if (!expectedValue.equals(actualValue)) {
            throw new AssertionError("Field '" + field.getLabel() + "' value mismatch. Expected: '"
                    + expectedValue + "', but found: '" + actualValue + "'");
        }

        log.info("Field '{}' value matches expected '{}'", field.getLabel(), expectedValue);
    }

    @Getter
    public enum ContactField {
        FIRST_NAME("First name"),
        LAST_NAME("Last name"),
        MIDDLE_NAME("Middle name"),
        NICKNAME("Nickname"),
        EMAIL("Email"),
        SECONDARY_EMAIL("Secondary email"),
        PHONE("Phone number"),
        ROLE("Role"),
        COMPANY("Company"),
        JOB_TITLE("Job title"),
        JOB_ROLE("Job role"),
        COMMENTS("Comments");

        private final String label;

        ContactField(String label) {
            this.label = label;
        }
    }
}

