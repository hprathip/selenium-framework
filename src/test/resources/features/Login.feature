Feature: Login functionality on SauceDemo

  Background:
    Given I am on the SauceDemo login page

  @smoke
  Scenario: Valid login with correct credentials
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be on the products page

  @regression
  Scenario: Invalid login with wrong password
    When I enter username "standard_user" and password "wrong_password"
    And I click the login button
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"

  @regression
  Scenario: Locked out user cannot login
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click the login button
    Then I should see error message "Epic sadface: Sorry, this user has been locked out."

  @regression
  Scenario Outline: Login with multiple users
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see "<result>"

    Examples:
      | username        | password     | result                |
      | standard_user   | secret_sauce | products              |
      | locked_out_user | secret_sauce | locked out            |
      | invalid_user    | wrong_pass   | do not match any user |
