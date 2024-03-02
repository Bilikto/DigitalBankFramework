Feature: Get all checking accounts

  @api
  Scenario: Verify user standard checking accounts
    Given user set up request for "/user/{id}/account/checking"
    When user set up "Authorization" header token
    And user set up a path parameter "id" as a 4356
    And user perform GET call
    Then verify status code is 200
    And verify the following should be in the standard checking accounts response payload
    | id   | name              | accountNumber | currentBalance | openingBalance | interestRate | dateOpened       |
    | 6121 | Bill_Checking     | 486131520     | 60000.00       | 60000.00       | 0.0          | 2023-09-11T07:02 |
    | 6568 | Test_Checking     | 486131663     | 26000.00       | 26000.00       | 0.0          | 2023-09-13T08:07 |
    | 6898 | Test_account1     | 486131771     | 85000.00       | 85000.00       | 0.0          | 2023-09-14T04:13 |
    | 6901 | Test_account1     | 486131772     | 85000.00       | 85000.00       | 0.0          | 2023-09-14T04:13 |
    | 6904 | Test_account2     | 486131773     | 85000.00       | 85000.00       | 0.0          | 2023-09-14T04:15 |
    | 6943 | Test_account      | 486131785     | 10000.00       | 10000.00       | 0.0          | 2023-09-14T06:49 |
    | 7039 | Checking_account  | 486131816     | 37896.00       | 44000.00       | 0.0          | 2023-09-15T12:18 |
    | 7577 | Checking_account  | 486131967     | 44000.00       | 44000.00       | 0.0          | 2023-09-20T01:54 |
    | 9583 | Checking_account  | 486132485     | 44000.00       | 44000.00       | 0.0          | 2023-09-27T04:52 |
    | 10583| Checking_account  | 486132670     | 44000.00       | 44000.00       | 0.0          | 2023-10-03T07:17 |
    Then verify an account type "standard_checking_account.json" in response payload
    And verify ownership type in the response payload
    Then verify an account standing in response payload


  @api
  Scenario: Verify user interest checking accounts
    Given user set up request for "/user/{id}/account/checking"
    When user set up "Authorization" header token
    And user set up a path parameter "id" as a 4356
    When user perform GET call
    Then verify status code is 200
    And verify the following should be in the interest checking accounts response payload
      | id   | name              | accountNumber | currentBalance | openingBalance | interestRate | dateOpened       |
      | 6157 | Test Checking     | 486131531     | 50000.00       | 50000.00       | 0.5          | 2023-09-12T03:22 |
      | 6166 | Test Checking     | 486131534     | 50000.00       | 50000.00       | 0.5          | 2023-09-12T03:29 |
      | 6289 | Test Checking     | 486131571     | 50000.00       | 50000.00       | 0.5          | 2023-09-12T09:42 |
      | 6292 | Test Checking     | 486131572     | 50000.00       | 50000.00       | 0.5          | 2023-09-12T09:44 |
      | 6295 | Test Checking     | 486131573     | 50000.00       | 50000.00       | 0.5          | 2023-09-12T09:44 |
      | 6715 | Test_account      | 486131711     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T12:52 |
      | 6718 | Test_account      | 486131712     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T12:54 |
      | 6865 | Test_account      | 486131760     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T03:54 |
      | 6868 | Test_account1     | 486131761     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T03:56 |
      | 6877 | Test_account1     | 486131764     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T03:59 |
      | 6880 | Test_account1     | 486131765     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T04:00 |
      | 6883 | Test_account1     | 486131766     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T04:00 |
      | 6886 | Test_account1     | 486131767     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T04:01 |
      | 6889 | Test_account1     | 486131768     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T04:04 |
      | 6892 | Test_account1     | 486131769     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T04:05 |
      | 6895 | Test_account1     | 486131770     | 85000.00       | 85000.00       | 0.5          | 2023-09-14T04:09 |
      | 6907 | Test_account3     | 486131774     | 90000.00       | 90000.00       | 0.5          | 2023-09-14T04:17 |
    Then verify an account type "interest_checking_account.json" in response payload
    And verify ownership type in the response payload
    Then verify an account standing in response payload