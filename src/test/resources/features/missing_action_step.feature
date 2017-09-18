Feature: Missing Action Step rule

  AS an agile team member
  I WANT the ability to run a rule that checks for scenarios that do have a verification step
  SO THAT my BDD scenarios clearly identify the functionality being tested

  Scenario: Missing Verification Step
    Given I have a feature file with scenarios that do not have a action step
    When I run the Missing Action Step rule
    Then I am notified of the scenarios that are missing an action step
