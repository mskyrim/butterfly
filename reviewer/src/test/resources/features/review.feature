Feature: Review feature

  Background: The resource URI is ready
    Given App is up and running and the endpoint is ready

  Scenario: create new review
    Given the following review
      | id  | rating | comment |
      | 1   | 4      |  hello review  |
    When i call review/add endpoint
    Then the is correctly added