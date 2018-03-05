Feature: Shop Feature

  Scenario: Retrieve shops
    Given I create a request to retrieve shops
    When I sent the request
    Then status code is 200
    And shop list contains
      | code1 | name1 |
      | code2 | name2 |
