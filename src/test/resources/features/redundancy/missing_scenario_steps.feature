Feature: Missing Scenario Steps rule

  AS an agile team member
  I WANT the ability to run a rule that checks for scenarios that do not have any steps
  SO THAT I can avoid redundant scenarios in my BDD test suite scenarios
  AND avoid the risk of false impression of implemented scenarios

  Scenario: This scenario is missing steps

  Scenario: This scenario also is missing steps

  Scenario: This scenario has steps
    Given my feature file has steps
    When I run the 'missing scenario steps' rule
    Then I do not get an error








