Feature: Login feature

  @Regression
  @Smoke
  Scenario: Validate user is able to login successfully
    Given user open website
    When user enter username and password
    And click on sign in button
    Then verify user is on Home page



