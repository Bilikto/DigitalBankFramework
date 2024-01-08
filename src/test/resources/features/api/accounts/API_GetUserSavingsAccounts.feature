Feature: Get all savings accounts

  @api
  Scenario: Verify user saving accounts
    When user set up request for "/user/{id}/account/savings"
    When user set up "Authorization" header token
    Then user set up a path parameter "id" as a 4356
    And user perform GET call
    Then verify status code is 200
    And verify the following should be in the saving accounts response payload
      | id   | name              | accountNumber | currentBalance | openingBalance | interestRate | dateOpened       |
      | 7036 | Saving_account3   | 486131815     | 53000.00       | 53000.00       | 1.85         | 2023-09-15T12:15 |
      | 7574 | Saving_account    | 486131966     | 53000.00       | 53000.00       | 1.85         | 2023-09-20T01:53 |
    Then verify saving account type "saving_account.json" in response payload
    And verify saving account ownership type in the response payload
    Then verify saving account standing in response payload