Feature: Verify TCM home page details

  C3~S4~A6~F10~UI test cases

  @Service @SampleUITest @Error_message @Regression
  Scenario: locators: Verify Services button in InfoOrigin website
    Then Verify the 'Services Text' as 'Services'


  @Career @SampleUITest @Regression
  Scenario: locators: Verify Careers button in InfoOrigin website
    Then Verify the 'Careers Text' as 'Careers'

  @Outline_test @SampleUITest @Regression
  Scenario Outline: locators: Verify Careers button in InfoOrigin website
    When User clicks on 'Leave message'
    And User enters 'Name' text as '<names>'
    Then User should verify 'Reset Button'
    Examples:
      | names |
      | Disha |
      | Umesh |
