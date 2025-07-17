Feature: Login scenarios include valid and invalid logins with combination of wrong credentials

  @Otto
  Scenario Outline: Login valid user

    Given a user reads test data from "otto" "otto" by id "<TC_ID>"
    And user enter email and password
    And user press login button
    Then user verify login action

    Examples:
      | TC_ID  |
      | SI_001 |