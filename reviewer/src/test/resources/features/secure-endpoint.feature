Feature: Rest endpoints are ssl secured

  Background: The resource URI is HTTPS secure ready
    Given App is up and running and the endpoint is secure and ready
  Scenario: client makes call to GET /version
    When the client calls /version
    Then the client receives status code of 200
    And the client receives server version 1.0