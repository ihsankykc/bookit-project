Feature: Testing the whole information of a person with three layer of information
@hw
  Scenario: UI,API,DB Verification
    Given user logs in using "sbirdbj@fc2.com" "asenorval"
    When user is on the my self page
    And I logged Bookit api using "sbirdbj@fc2.com" and "asenorval"
    And I get the current user information from api
    Then All five information from three environment should match
