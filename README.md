🚀 UI Automation Tests for servers.com Portal

This repository contains UI automated tests for the web portal https://portal.servers.com/
.
The project was developed as part of a technical assignment and covers key portal functionality, including sidebar navigation, account settings, subscription management (CRUD), form validation, and more.

🧰 **Tech Stack**

| Technology         | Purpose                         |
|--------------------|----------------------------------|
| Java 17+           | Main programming language        |
| Selenide           | UI test automation framework     |
| Selenium WebDriver | Browser automation               |
| JUnit 5            | Test framework                   |
| Owner              | Configuration management         |
| Lombok             | Reduces boilerplate code         |
| WebDriverManager   | Manages browser drivers          |
| Maven              | Build and test runner            |

## ▶️ How to Run Tests

### 1. Run all tests
```bash
mvn clean test 
```

### 2. Run a single test class
```bash
mvn -Dtest=LoginTest test  
mvn -Dtest=LogoutTest test 
mvn -Dtest=CreateSubscriptionTest test 
mvn -Dtest=DeleteSubscriptionTest test 
mvn -Dtest=UpdateSubscriptionTest test 
```

### 3. Run tests in headless mode
```bash
mvn clean test -Dheadless=true  
```