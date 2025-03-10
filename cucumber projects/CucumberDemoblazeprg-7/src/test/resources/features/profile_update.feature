@ProfileTest
Feature: Profile Update Functionality

  Scenario: User updates profile details
    Given User is logged into the application
    When User navigates to the profile section
    And User updates the profile details
    Then User should see a profile update confirmation message
