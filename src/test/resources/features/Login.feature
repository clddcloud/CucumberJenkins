Feature: Login related scenarios

#Given user is able to access HRMS application
# just one common line for background

  @sprint1 @smoke @regression @artem
  Scenario: valid admin login
    When user enters valid username and password
    And user clicks on login button
    Then user is able to see dashboard page

    @invalid
    Scenario: Invalid admin login
      When user enters invalid username and password
      And user clicks on login button
      Then user can see error message