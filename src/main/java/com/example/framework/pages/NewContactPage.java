package com.example.framework.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class NewContactPage {

    // mandatory fields
    private final SelenideElement firstNameInput = $x("//input[@placeholder='Enter First name']");
    private final SelenideElement lastNameInput = $x("//input[@placeholder='Enter Last name']");
    private final SelenideElement emailInput = $x("//input[@name='email']");
    private final SelenideElement createButton = $x("//button[@type='submit']//span[normalize-space()='Create']");

    // non-mandatory fields
    private final SelenideElement middleNameInput = $x("//input[@placeholder='Enter Middle name']");
    private final SelenideElement nicknameInput = $x("//input[@placeholder='Enter Nickname']");
    private final SelenideElement commentsInput = $x("//textarea[@placeholder='Enter comments']");
    private final SelenideElement companyInput = $x("//input[@placeholder='Enter company']");
    private final SelenideElement jobTitleInput = $x("//input[@placeholder='Enter job title']");
    private final SelenideElement jobRoleInput = $x("//input[@placeholder='Enter job role']");
    private final SelenideElement phoneInput = $x("//input[@name='phone_number']");
    private final SelenideElement secondaryEmailInput = $x("//input[@name='email2']");
    private final SelenideElement saveButton = $x("//button[@title='Save']");

    public void fillFirstName(String firstName) {
        log.info("Entering first name: {}", firstName);
        firstNameInput.shouldBe(visible).clear();
        firstNameInput.setValue(firstName);
    }

    public void fillLastName(String lastName) {
        log.info("Entering last name: {}", lastName);
        lastNameInput.shouldBe(visible).clear();
        lastNameInput.setValue(lastName);
    }

    public void fillEmail(String email) {
        log.info("Entering email: {}", email);
        emailInput.shouldBe(visible).clear();
        emailInput.setValue(email);
    }

    public void clickCreateButton() {
        log.info("Clicking 'Create' button");
        createButton.shouldBe(visible).click();
    }

    private SelenideElement getCheckboxElement(JobRole role) {
        String xpath = "//div[@id='role']//label[normalize-space()='" + role.getLabel() + "']//input";
        return $x(xpath);
    }

    public void selectRole(JobRole role) {
        log.info("Selecting job role: {}", role.getLabel());
        SelenideElement checkbox = getCheckboxElement(role);
        checkbox.shouldBe(visible).click();
    }

    public void deselectRole(JobRole role) {
        log.info("Deselecting job role: {}", role.getLabel());
        SelenideElement checkbox = getCheckboxElement(role);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isRoleSelected(JobRole role) {
        SelenideElement checkbox = getCheckboxElement(role);
        boolean selected = checkbox.isSelected();
        log.info("Job role '{}' selected: {}", role.getLabel(), selected);
        return selected;
    }

    public void fillMiddleName(String middleName) {
        log.info("Entering middle name: {}", middleName);
        middleNameInput.shouldBe(visible).clear();
        middleNameInput.setValue(middleName);
    }

    public void fillNickname(String nickname) {
        log.info("Entering nickname: {}", nickname);
        nicknameInput.shouldBe(visible).clear();
        nicknameInput.setValue(nickname);
    }

    public void fillComments(String comments) {
        log.info("Entering comments: {}", comments);
        commentsInput.shouldBe(visible).clear();
        commentsInput.setValue(comments);
    }

    public void fillCompany(String company) {
        log.info("Entering company: {}", company);
        companyInput.shouldBe(visible).clear();
        companyInput.setValue(company);
    }

    public void fillJobTitle(String jobTitle) {
        log.info("Entering job title: {}", jobTitle);
        jobTitleInput.shouldBe(visible).clear();
        jobTitleInput.setValue(jobTitle);
    }

    public void fillJobRole(String jobRole) {
        log.info("Entering job role: {}", jobRole);
        jobRoleInput.shouldBe(visible).clear();
        jobRoleInput.setValue(jobRole);
    }

    public void fillPhone(String phone) {
        log.info("Entering phone: {}", phone);
        phoneInput.shouldBe(visible).clear();
        phoneInput.setValue(phone);
    }

    public void fillSecondaryEmail(String email) {
        log.info("Entering secondary email: {}", email);
        secondaryEmailInput.shouldBe(visible).clear();
        secondaryEmailInput.setValue(email);
    }

    public void editField(ContactField field, String value) {
        switch (field) {
            case FIRST_NAME -> fillFirstName(value);
            case LAST_NAME -> fillLastName(value);
            case MIDDLE_NAME -> fillMiddleName(value);
            case NICKNAME -> fillNickname(value);
            case COMMENTS -> fillComments(value);
            case COMPANY -> fillCompany(value);
            case JOB_TITLE -> fillJobTitle(value);
            case JOB_ROLE -> fillJobRole(value);
            case PHONE -> fillPhone(value);
            case SECONDARY_EMAIL -> fillEmail(value);
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        }
    }

    public void clickSaveButton() {
        log.info("Clicking 'Save' button");
        saveButton.shouldBe(visible).click();
    }

    @Getter
    public enum JobRole {
        PRIMARY("Primary"),
        TECHNICAL("Technical"),
        BILLING("Billing"),
        ABUSE("Abuse"),
        EMERGENCY("Emergency");

        private final String label;

        JobRole(String label) {
            this.label = label;
        }
    }

    @Getter
    public enum ContactField {
        FIRST_NAME,
        LAST_NAME,
        MIDDLE_NAME,
        NICKNAME,
        COMMENTS,
        COMPANY,
        JOB_TITLE,
        JOB_ROLE,
        PHONE,
        SECONDARY_EMAIL
    }
}
