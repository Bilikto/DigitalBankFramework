Feature: Transaction validation

  @Regression
  @Smoke
  Scenario: Verify user can transfer the fund
    Given user open website
    When user enter username and password
    And click on sign in button
    Then verify user is on Home page


