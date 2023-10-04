Feature: A new saving account creation

  @Regression
  Scenario: Validate user is able to create a new saving account
    Given user open website
    When user enter username and password
    And click on sign in button
    Then verify user is on Home page

    When user click on Savings menu
    And click on New Savings menu
    Then verify user is on Create Savings page

    When user select savings account type
    And select savings account ownership type
    And provide savings account name
    And provide savings initial deposit amount
    And click on submit button on savings account
    Then verify a new saving account created
    Then verify a new saving account is saved in database