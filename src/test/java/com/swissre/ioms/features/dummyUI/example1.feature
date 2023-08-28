Feature: User able to navigate POM page

   C3~S4~A5~F9~UI test cases

   @Platform @SampleUITest @Combine @Regression
   Scenario:locators: Verify Home button in InfoOrigin website
#      Then User verify the Home button
      Then Verify the 'Home Text' as 'Home'

   @Platform @SampleUITest @Regression
   Scenario: locators: Verify Platform button in InfoOrigin website
      Then Verify the 'Platform Text' as 'Platform'

   @Leave_message @DataTable_test @SampleUITest @Regression
   Scenario: locators: Fill Leave message form in InfoOrigin website
      When User clicks on 'Leave message'
      And User fills the message form
      |Name |Email            |Subject|Message      |
      |Disha|disha21@gmail.com|Java   |Great leaning|
      Then User should verify 'Reset Button'

    

