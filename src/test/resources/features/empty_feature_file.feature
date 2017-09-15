Feature: Empty Feature File Rule

  AS an agile team member
  I WANT the ability to run a rule that checks for empty feature files
  SO THAT my BDD test suite does not contain any redundant files

  Scenario: Empty Feature file
    Given I have an empty feature file
    When I run the empty feature file rule
    Then I am notified that the feature file is empty