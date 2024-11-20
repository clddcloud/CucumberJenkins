Feature: Add employee

  Background:
    #Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to see dashboard page
    When user clicks on PIM option
    And user clicks on Add employee option

@sprint1 @alexandra
  Scenario: Adding an employee by firstname and lastname
    And user enters firstname and lastname
    And user clicks on Save button
    Then employ added successfully

  @sprint2 @smoke @regression
  Scenario: Adding an employee by firstname, middlename and lastname
    And user enters firstname and middlename and lastname
    And user clicks on Save button
    Then employ added successfully

@param
  Scenario: adding employees using parameters
    And user enters "mark" and "anthony" and "jacob" in the application
    And user clicks on Save button
    Then employ added successfully

  #data driven testing
  @example
  Scenario Outline: Adding multiple employees
    And user adds "<firstname>", "<middlename>" and "<lastname>"
    And user clicks on Save button
    Then employ added successfully
    Examples:
      | firstname | middlename | lastname |
      |mars       |ms          |jacob     |
      |adam       |ms          |jacob     |
      |steve      |ms          |jacob     |

  @data
  Scenario: Adding multiple employees using data table
    When user adds multiple employees using data table and save them
      | firstName | middleName | lastName |
      |mars       |ms          |jacob     |
      |adam       |ms          |jacob     |
      |steve      |ms          |jacob     |

  @excel
  Scenario: adding employees using excel file
    When user adds multiple employees from excel file