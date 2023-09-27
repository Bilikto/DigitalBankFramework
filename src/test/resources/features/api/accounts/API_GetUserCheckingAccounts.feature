Feature: Get all checking accounts

  @api
  Scenario: Verify checking accounts
    Given user is authenticated
    When user set up request for "/user/{id}/account/checking"
    And user set up "Authorization" header token
    Then user set up a path parameter "id" as a 4356
    And user perform GET call
    Then verify status code is 200
    And verify the following should be the checking response payload
    | id   | name          | accountNumber | currentBalance | openingBalance | interestRate | dateOpened       |
    | 6121 | Bill_Checking | 486131520     | 60000.00       | 60000.00       | 0.0          | 2023-09-11T07:02 |
    Then verify the following should be the account type response payload
    | id | code | category | name              | interestRate | minDeposit | overdraftLimit | overdraftFee |
    | 8  | SCK  | CHK      | Standard Checking | 0.0          | 25.00      | 25.00          | 10.00        |
    And verify the following should be the ownership type response payload
    | id | code | name        |
    | 17 | IND  | Individual  |
    Then verify the following should be the account standing response payload
    | id | code | name |
    | 19 | A1   | Open |

