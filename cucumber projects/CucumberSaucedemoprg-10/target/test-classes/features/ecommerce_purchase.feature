@PurchaseTest
Feature: E-commerce Product Purchase
  
  Background:
    Given User is logged into the e-commerce site

  Scenario: User searches for a product and views details
    When User searches for "Sauce Labs Backpack"
    Then User should see the product in search results
    And User views product details

  Scenario: User adds product to cart and views cart
    When User searches for "Sauce Labs Backpack"
    Then User should see the product in search results
    And User views product details
    When User adds "Sauce Labs Backpack" to cart
    Then User should see the product in the cart

  Scenario: User proceeds to checkout and completes purchase
    When User searches for "Sauce Labs Backpack"
    Then User should see the product in search results
    And User views product details
    When User adds "Sauce Labs Backpack" to cart
    Then User should see the product in the cart
    When User proceeds to checkout
    And User enters checkout details
      | FirstName | LastName | ZipCode |
      | John      | Doe      | 12345   |
    And User completes the purchase
    Then User should see a purchase confirmation message
