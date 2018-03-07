Feature: Order Feature
  Scenario: Retrieve orders
    Given I have a shop whose id is 1 name is name1 and code is code1
    Given I have a shop whose id is 2 name is name2 and code is code2
    Given I create a request to retrieve orders
    And Shop id is 1
    And I have a order whose vehicle name is benz, price is 99 and shop id is 1
    And I have a order whose vehicle name is benz, price is 999 and shop id is 1
    And I have a order whose vehicle name is benz, price is 9999 and shop id is 1
    And I have a order whose vehicle name is benz, price is 9999 and shop id is 2
    When I sent the request
    Then status code is 200
    And order count is 3
