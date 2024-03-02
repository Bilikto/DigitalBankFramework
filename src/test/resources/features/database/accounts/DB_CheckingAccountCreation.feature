Feature: A new checking account creation

  @Regression @db
  Scenario: Validate user is able to create a new checking account
    Given user open website
    When user enter username and password
    And click on sign in button
    Then verify user is on Home page

    When user click on Checking menu
    And click on New Checking menu
    Then verify user is on Create Checking page

    When user select checking account type
    And select account ownership type
    And provide checking account name
    And provide initial deposit amount
    And click on submit button
    Then verify a new checking account created
    Then verify a new checking account is saved in database
















