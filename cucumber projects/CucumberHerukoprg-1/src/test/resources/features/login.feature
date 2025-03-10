@LoginTest
Feature: Verify successful login message
  Scenario: User logs in with valid credentials.
    Given I am on the login page
    When I enter valid credentials
    And I click on the login button
    Then I should see a success message

