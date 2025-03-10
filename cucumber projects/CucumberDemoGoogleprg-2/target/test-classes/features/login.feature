@SearchTest
Feature: Google Search Functionality
  Scenario: User searches for a product
    Given User is on the Google homepage
    When User searches for "Testing"
    Then Search results are displayed