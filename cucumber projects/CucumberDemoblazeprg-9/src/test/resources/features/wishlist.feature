@WishlistTest
Feature: Wishlist Functionality

  Background: 
    Given User is logged into the application

  Scenario: User adds product to wishlist
    When User navigates to a product page
    And User adds the product to the wishlist
    Then User should see the product in the wishlist
