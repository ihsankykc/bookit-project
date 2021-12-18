Feature: Testing the whole information of a person with three layer of information

@hw @db
  Scenario: UI,API,DB Verification
    Given user logs in using "sbirdbj@fc2.com" "asenorval"
    When user is on the my self page
    And I get the current user information from UI
    And I logged Bookit api using "sbirdbj@fc2.com" and "asenorval"
    And I get the current user information from api
    And I get more information about user from API
    And I get the current user information from DataBase
    Then All five information from three environment should match
