Feature: 2
#AS an agile team member
#I WANT the ability to run a rule that checks for empty feature files
#SO THAT my BDD test suite does not contain any redundant files
Scenario: This feature file is missing a Feature name
  Given I have feature file missing a feature name
  When I run the empty feature file rule
  Then I am notified that the feature file is empty









