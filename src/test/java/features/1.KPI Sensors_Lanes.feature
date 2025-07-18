Feature: Log in to the application,select program Camera System V1, navigate to KPI Sensor and select Lanes.
  Check if the sum of values from each row corresponds with the value from the total row.

  @Otto
  Scenario Outline: Open KPI Sensor/FCM/Lanes

    Given a user reads test data from "otto" "otto" by id "<TC_ID>"
    And user enters email and password
    And user presses login button
    Then user verify login action
    And user presses program picker menu
    And user selects camera system vt1
    And user selects lines option
    Then user compares the average of the row values with the total value

    Examples:
      | TC_ID  |
      | SI_001 |