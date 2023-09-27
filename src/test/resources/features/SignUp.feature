Feature: SignUp feature

  @Regression
  Scenario: Validate user is able to sign up
    Given user open website
    When user click on Sign Up here link
    Then validate the user is on Sign Up page

    When user fill out personal information on Sign Up page
    And click on next button
    Then validate the user is on Register page

    When user fill out personal information on Register page
    And click on register button
    Then validate registration success message
