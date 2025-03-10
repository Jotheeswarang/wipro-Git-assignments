@CheckoutTest
Feature: Checkout Process

  Scenario: User completes the checkout process
    Given User is on the products page
    When User adds a product to the cart
    And User proceeds to checkout
    And User fills in the checkout information
      | firstName | lastName | postalCode |
      | Jone     | jothees      | 12345      |
    Then User should see the order confirmation message