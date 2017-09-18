Feature: Missing Scenario Steps rule

  AS an agile team member
  I WANT the ability to run a rule that checks for scenarios that do not have any steps
  SO THAT I can avoid redundant scenarios in my BDD test suite scenarios
  AND avoid my BDD test suite giving an inaccurate indication of test coverage

  Scenario: Missing Scenario Steps
    Given I have a feature file with scenarios that do not have any steps
    When I run the Missing Scenario Steps rule
    Then I am notified of the scenarios with missing steps
