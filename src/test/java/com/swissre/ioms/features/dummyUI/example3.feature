Feature: User able to create deal in TCM

  C3~S4~A6~F11~UI test cases

  @IO @SampleUITest @Regression
  Scenario:locators: Scenario Verify Home button in InfoOrigin website
    And Verify the text 'Home Text' as 'Home'
    Then Verify the 'Home Text' as 'OM'

  @Zoho @Umesh @SampleUITest @Regression
  Scenario:locators: Scenario to login into Zoho
    When User clicks on 'Zoho Sign In'
    And Enter 'Email' text as 'utjain2000@gmail.com'
    And User clicks on 'Email Next'
    And Enter 'Email-Password' text as 'GondiaRice@441601'
    And User clicks on 'Sign In'
    Then Verify the 'Welcome Label' as 'Welcome utjain2000'
    Then Verify the 'My Open Deals Label' as 'My Open Deals'