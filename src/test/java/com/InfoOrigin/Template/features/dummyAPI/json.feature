Feature: JSON compare

  @json @Regression
  Scenario: Method to compare Actual json with expected json
    When Compare "actual.json" is equals to "expected.json"