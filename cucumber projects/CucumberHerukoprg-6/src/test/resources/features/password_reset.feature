@PasswordResetTest
Feature: Password Reset Functionality

  Scenario: User resets password
    Given User is on the password reset page
    When User enters the email and submits the form
    Then User should see a confirmation message
