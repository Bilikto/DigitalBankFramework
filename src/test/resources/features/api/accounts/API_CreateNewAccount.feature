Feature: Create a new account

  @api
  Scenario: Verify a new account is created
    Given user setup request for "/user/account"
    When user set up "Authorization" header token
    When user set up "Content-Type" header as "application/json"
    When user set up "Accept" header as "application/json"
    And user set body "create_new_account.json" as pojo
    When user perform post call
    Then verify status code is 200
    And verify response has the same account name as in the request body
    Then verify response has the same account type code as in the request body
    And verify response has the same opening deposit as in the request body
    Then verify response has the same owner type code as in the request body

