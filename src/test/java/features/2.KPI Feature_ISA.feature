Feature: Log in to the application,select program Camera System VT1, navigate to KPI Feature and select ISA and Zone1.
  Open the first seven DTIDs and count the FN events in the timeline.

  @Otto
  Scenario Outline: Open KPI Feature/ISA/Zone1

    Given a user reads test data from "otto" "otto" by id "<TC_ID>"
    And user enters email and password
    And user presses login button
    Then user verify login action
    And user presses program picker menu
    And user selects camera system v1
    And user selects zone1 option
    And user selects DTIDs
    And user clicks preview button
    Then user counts all FN events

    Examples:
      | TC_ID  |
      | SI_001 |