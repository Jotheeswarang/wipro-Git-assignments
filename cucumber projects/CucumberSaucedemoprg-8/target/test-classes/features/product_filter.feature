@FilterTest
Feature: Product Filter

  Scenario: User applies product filters
    Given User is on the products page
    When User selects the filter option "Price (low to high)"
    Then User should see products sorted by "Price (low to high)"
