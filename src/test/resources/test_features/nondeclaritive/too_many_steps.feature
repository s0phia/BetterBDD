# Rule: too_many_steps

Feature: Too Many Steps rule

  AS an agile team member
  I WANT the ability to run a rule that checks for scenarios having too many steps
  SO THAT I can avoid imperative style BDD scenarios

  Scenario: This scenario has too many steps
    Given an initial context
    When an event
    And another event
    And another event
    And another event
    And another event
    And another event
    And another event
    And another event
    Then an expected outcome
    And another expected outcome
    And another expected outcome
    And another expected outcome