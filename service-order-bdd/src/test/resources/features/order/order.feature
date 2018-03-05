Feature: Order Feature
  Scenario: Retrieve orders
    Given I create a request to retrieve orders
    When I sent the request
    Then status code is 200
    And order count is 10
