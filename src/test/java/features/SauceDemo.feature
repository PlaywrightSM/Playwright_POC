@SauceDemo
Feature: PlayWright Demo with SauceDemo Website
  Scenario: Navigate to SauceDemo Website and Perform actions
    Given User navigate to SauceDemo Website
    When User enters the Username and password
    Then User adds the items into the cart
    And User clicks on view cart button
    And User clicks on continue shopping and additional item
    And User removes item from the cart
    And User Checkout the cart items and place and order
