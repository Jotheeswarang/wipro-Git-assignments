@CartTest
Feature: Add to Cart

  Background:
    Given User is on the login page
    When User logs in with valid credentials
    Given User is on the products page

  Scenario: User adds a product to the cart
    When User adds a product to the cart
    Then The product should be added successfully

