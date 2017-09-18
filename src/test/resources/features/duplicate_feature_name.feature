Feature: Duplicate Feature Name Rule

  AS an agile team member
  I WANT the ability to run a rule that checks for duplicate feature names
  SO THAT I can avoid  duplicated feature names in my BDD test suite

  Scenario: Duplicate Feature Name
    Given I have duplicate feature names in my BDD test suite
    When I run the Duplicate Feature Name rule
    Then I am notified of the duplicate feature names
