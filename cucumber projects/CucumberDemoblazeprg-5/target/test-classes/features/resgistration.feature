@RegistrationTest
Feature: User Registration
  As a new user
  I want to register on the website
  So that I can access user-specific features

  Scenario: User registers with valid details
    Given I am on the registration page
    When I enter username and password
      | Username | JONE |
      | Password | JONE@123  |
    Then I should see a successful registration message