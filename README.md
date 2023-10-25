# Selenium_RestAssuredFramework (UI/API Automation Framework)

It's a framework created with Cucumber, TestNG, Gherkins and Maven for dealing with UI Automation using Selenium & API Automation using REST Assured.

# Core Framework
### Selenium-Cucumber-Java

This repository contains a BDD (Behavior-Driven Development) framework with Cucumber and Java. The projects showcase automation script development and utilize various reporters such as HTML and JSON.

### Installation & Prerequisites

1. JDK 1.8+ (Ensure that the Java class path is properly set)
2. Maven (Ensure that the .m2 class path is properly set)
3. Intellij Community Edition IDE
4. Required IntelliJ Plugins:
   - Maven
   - Cucumber
   - Gherkins
5. Browser driver (Ensure that you have the appropriate browser driver for your desired browser and that the class path is correctly configured)

## Framework Setup

To set up the framework, you can either fork or clone the repository from [here](https://github.com/UmeshJainInfoOrigin/Selenium_RestAssuredFramework/), or download the ZIP file and set it up in your local workspace.

## Running Sample Tests

Run the following command to execute the features: 

`clean compile test -DEnvName=template_dev -DAuthProvider=OAuth -Dcucumber.filter.tags=@Platform`. 

## Reporters

Once you have run your tests, you can generate various types of reports. This `selenium-cucumber-java` framework utilizes different test reporters to communicate pass/failure information.

## Reporting

### HTML Report

To generate an HTML report, use the following plugin in Cucumber Options: `"html:target/cucumber-reports/CucumberRunnerTest-reports.html"`. 
This generates an HTML report and you can find it at `target/cucumber-reports/CucumberRunnerTest-reports.html`.

### JSON Report

To generate a JSON report, use the following plugin in Cucumber Options: `"json:target/cucumber-reports/CucumberRunnerTest.json"`. 
This generates a JSON report and you can find it at `target/cucumber-reports/CucumberRunnerTest.json`.

## BDD Automation with Cucumber-Java and Page Objects

In this repository, we encourage the use of Behavior-Driven Development (BDD) with Cucumber and Java to develop automation scripts. We provide predefined Step Definitions packaged under `com/InfoOrigin/Template/stepDefinitions/common/SharedStepDef.java` to help you accelerate your automation development. These Step Definitions support commonly used helper methods and can be customized according to your needs.

Tests are written in the Cucumber framework using the Gherkin syntax. A typical test will have a structure similar to this:

```gherkin
Feature: Performing a Google Search

    As a user on the Google search page
    I want to search for Selenium-Webdriver
    Because I want to learn more about it

    Background:
        Given I am on the search page

    Scenario: locators: Performing a search operation
        When I enter "Selenium Webdriver" into the search box
        And I click the search button
        Then I should see a list of search results

    Scenario Outline: Performing a search operation with test data from a data table
        When I enter <searchItem> into the search box
        And I click the search button
        Then I should see a list of search results

        Examples:
        | searchItem         |
        | "Selenium Webdriver" |
```

## The Page Object Design Pattern

To better organize your test code and make it more maintainable, we recommend using the Page Object Design Pattern. With this pattern, the UI elements of your web application are modeled as objects within the test code. This approach reduces code duplication and allows easy updates if the UI changes. Writing and maintaining test automation can be challenging, especially when it comes to keeping locators (classes, IDs, or XPath, etc.) up to date with the latest code changes. We provide JSON files of locators under `com/InfoOrigin/Template/jsonData` The Page Object pattern provides a solution by centralizing these locators in separate <pagename>.json files, where you can manage them along with the associated methods.

By using the Page Object pattern, your test files will only call the test methods, while the locators and reusable methods reside in the corresponding Page Objects. This approach helps maintain a separation of concerns and ensures that when a test fails, it fails on an individual step. If a locator becomes invalid, updating it in the Page Object file can fix multiple failing tests that rely on the same locator.

Implementing the Page Object pattern promotes maintainable and scalable test automation code. 


## List of Functionality in Framework for Selenium

Selenium is a popular tool for automating web browser interactions and testing web applications.

#### 1. Web Browser/Application Automation: 
* Automating web browsers/applications to perform tasks like opening web pages, filling out forms, moving cursor via robot class, clicking buttons and many more.

#### 2. Cross-Browser Compatibility:
* Running tests on multiple web browsers like Chrome, Firefox and Edge to ensure compatibility.

#### 3. Headless Browsing:
* Running browser automation in a headless mode (without a graphical user interface) for faster and more efficient testing.

#### 4. Element Locators:
* Locating web elements using various strategies, including by ID, name, class, XPath, CSS selectors, etc.

#### 5. User Interactions:
* Simulating user interactions such as clicking, typing, hovering, and scrolling.

#### 6. Handling Alerts and Pop-ups:
* Dealing with JavaScript alerts, pop-up windows, and authentication dialogs.

#### 7. Test Suites:
* Grouping test cases into suites and executing them in a specified order using tags.

#### 8. Parallel Test Execution:
* Running tests concurrently on multiple browsers or devices for faster test execution.

#### 9. Synchronization:
* Implementing wait mechanisms to ensure that elements are loaded before interacting with them, preventing flakiness in tests.

#### 10. Page Object Model (POM):
* Structuring test automation code using the POM design pattern for maintainability and reusability.

#### 11. Assertions and Verifications:
* Validating expected results by using assertions to check elements, text, or attributes on a web page.

#### 12. Test Reporting:
* Generating test reports with detailed information on test results, including pass/fail status.



## List of Functionality in Framework for REST Assured

REST Assured is a popular Java library for automating and testing RESTful web services. 

#### 1. HTTP Request Methods:
* Sending HTTP requests such as GET, POST, PUT, DELETE, PATCH, etc.

#### 2. Request Payloads:
* Sending request payloads, including JSON, XML.

#### 3. Request Headers:
* Adding custom headers to HTTP requests, including authentication, content type, and custom headers.

#### 4. URL Parameters:
* Including query parameters and path parameters in requests.

#### 5. Authentication:
* Handling authentication mechanisms like Basic Authentication, OAuth, API keys, and tokens.

#### 6. Response Validation:
* Validating HTTP response status codes, response headers, and response body content.

#### 7. JSON Path and XML Path:
* Extracting data from JSON and XML responses using specific path expressions.

#### 8. Assertions:
* Implementing various assertions to check response data, response time, and other aspects of the API response.

#### 9. Response Serialization and Deserialization:
* Converting API responses to Java objects (deserialization) and Java objects to request payloads (serialization).

#### 10. Response Body Content:
* Validating the content of the response body, including content matching.

#### 11. Logging and Reporting:
* Generating logs and reports of API test execution.

#### 12. Test Configuration and Setup:
* Configuring global settings, such as base URLs, authentication tokens, and other common parameters.

#### 13. Response Time Measurement:
* Measuring and asserting response times to check API performance.

#### 14. Error Handling:
* Handling errors and exceptions, including expected and unexpected errors.

#### 15. Parallel Test Execution:
* Running tests in parallel for faster test execution.

#### 16. Dynamic Environments:
* Managing different test environments (e.g., development, test, uat) with environment-specific configurations.

#### 17. Pre- and Post-Processing:
* Executing pre-processing and post-processing steps before and after each test.

#### 18. Code Reusability:
* Creating reusable utility functions and classes to reduce code duplication.
