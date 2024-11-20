Feature: Search Employees

  Background:
   # Given user is able to access HRM application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to see dashboard page
    When user clicks on PIM option
    And user clicks on employee list option

  @regression @test1
  Scenario: Search employee by employee Id
    And user enters employee ID
    When user click on search button
    Then user is able to see searched employee on screen

  @test2
  Scenario: Search employee by employee name
    And user enters employee name
    When user click on search button
    Then user is able to see searched employee on screen