Feature: User able to create scenario in TCM

  C3~S4~A6~F12~API test cases

  @Test @Regression @SampleAPITest
  Scenario: Test sample
    When User enters the text
    Then User should see the text

  @SampleAPITest @Combine @Regression
  Scenario: Test API automation
    When User enter 'https://www.google.com/' to open google
    Then Ensure that user will get isOk response