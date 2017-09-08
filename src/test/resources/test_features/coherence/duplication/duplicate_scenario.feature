Feature: Duplicate Scenario Name rule

  Scenario: This scenario name is a duplicate
    Given I have a feature file containing duplicate scenario names
    When I run the rule: duplicate scenario name
    Then I get the error: Duplicate scenario names detected

  Scenario: This scenario name is a duplicate
    Given I have a feature file containing duplicate scenario names
    When I run the rule: duplicate scenario name
    Then I get the error: Duplicate scenario names detected
    
  Scenario: This scenario name is NOT a duplicate
    Given I have a feature file containing duplicate scenario names
    When I run the rule: duplicate scenario name
    Then I get the error: Duplicate scenario names detected

