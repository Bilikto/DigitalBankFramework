Feature: Transaction validation

  @Regression
  Scenario: Verify user can transfer the fund
    Given user open website
    When user enter username and password
    And click on sign in button
    Then verify user is on Home page

    When user select Withdraw menu
    Then verify a Withdraw page is displayed

    When user select account for withdraw
    And user enter withdraw amount
    And user click submit button
    Then verify an account transaction page is displayed
    And verify a new transaction is presented in transaction history
    And verify transaction is getting updated in database