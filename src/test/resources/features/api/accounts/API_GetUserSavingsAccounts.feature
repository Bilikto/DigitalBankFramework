Feature: Get all savings accounts

  @api
  Scenario: Verify saving accounts
    Given user is authenticated
    When user set up request for "/user/{id}/account/savings"
    And user set up "Authorization" header token
    Then user set up a path parameter "id" as a 4356
    And user perform GET call
    Then verify status code is 200