Feature: Shop Feature

  Scenario: Retrieve shops
    Given I have a shop whose name is name1 and code is code1
    Given I have a shop whose name is name2 and code is code2
    Given I create a request to retrieve shops
    When I sent the request
    Then status code is 200
    And shop list contains
      | code1 | name1 |
      | code2 | name2 |

