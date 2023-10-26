Feature: PlayWright Demo MVC Website

  Scenario: Navigate to Playwright Demo MVC Website and perform actions
    Given User navigates to Playwright Demo Website
    When User clicks on MVC Link
    Then User clicks on Downloads button
    And User clicks on Blog button
    And User navigates back to MVC page
    And User clicks on Github Link
    And User performs certain actions on the Master Repo
    And User login into Github account


