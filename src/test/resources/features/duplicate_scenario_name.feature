Feature: Duplicate Scenario Name Rule

  AS an agile team member
  I WANT the ability to run a rule that checks for duplicate scenario names
  SO THAT I can avoid potentially duplicated scenarios in my BDD test suite

  Scenario: Duplicate Scenario Name
    Given I have duplicate feature name in my BDD test suite
    When I run the Duplicate Scenario Name rule
    Then I am notified of the duplicate scenario names