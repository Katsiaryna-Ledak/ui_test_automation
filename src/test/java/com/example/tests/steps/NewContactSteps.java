package com.example.tests.steps;

import com.example.framework.pages.NewContactPage;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class NewContactSteps {

    private final NewContactPage newContactPage = new NewContactPage();

    public void enterFirstName(String firstName) {
        log.info("Enter First Name");
        newContactPage.fillFirstName(firstName);
    }

    public void enterLastName(String lastName) {
        log.info("Enter Last Name");
        newContactPage.fillLastName(lastName);
    }

    public void enterEmail(String email) {
        log.info("Enter Email");
        newContactPage.fillEmail(email);
    }

    public void clickCreateButton() {
        log.info("Click 'Create' button");
        newContactPage.clickCreateButton();
    }

    public void fillMandatoryFields(String firstName, String lastName, String email, NewContactPage.JobRole jobRole) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        selectJobRole(jobRole);
    }

    public void fillNonMandatoryFields(
            String middleName,
            String nickname,
            String comments,
            String company,
            String jobTitle,
            String jobRole,
            String phone,
            String secondaryEmail
    ) {
        enterMiddleName(middleName);
        enterNickname(nickname);
        enterComments(comments);
        enterCompany(company);
        enterJobTitle(jobTitle);
        enterJobRole(jobRole);
        enterPhone(phone);
        enterSecondaryEmail(secondaryEmail);
    }

    public void selectJobRole(NewContactPage.JobRole role) {
        log.info("Select job role '{}'", role.getLabel());
        newContactPage.selectRole(role);
    }

    public void deselectJobRole(NewContactPage.JobRole role) {
        log.info("Deselect job role '{}'", role.getLabel());
        newContactPage.deselectRole(role);
    }

    public void verifyJobRoleSelected(NewContactPage.JobRole role) {
        log.info("Verify job role '{}' is selected", role.getLabel());
        assertTrue(newContactPage.isRoleSelected(role), "Job role " + role.getLabel() + " should be selected");
    }

    public void enterMiddleName(String middleName) {
        newContactPage.fillMiddleName(middleName);
    }

    public void enterNickname(String nickname) {
        newContactPage.fillNickname(nickname);
    }

    public void enterComments(String comments) {
        newContactPage.fillComments(comments);
    }

    public void enterCompany(String company) {
        newContactPage.fillCompany(company);
    }

    public void enterJobTitle(String jobTitle) {
        newContactPage.fillJobTitle(jobTitle);
    }

    public void enterJobRole(String jobRole) {
        newContactPage.fillJobRole(jobRole);
    }

    public void enterPhone(String phone) {
        newContactPage.fillPhone(phone);
    }

    public void enterSecondaryEmail(String email) {
        newContactPage.fillSecondaryEmail(email);
    }

    public void editField(NewContactPage.ContactField fieldName, String value) {
        log.info("Edit field '{}' with value '{}'", fieldName, value);
        newContactPage.editField(fieldName, value);
    }

    public void clickSaveButton() {
        log.info("Click 'Save' button");
        newContactPage.clickSaveButton();
    }
}
