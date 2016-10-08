Feature: Shared state achieved through Dependency Injection.

  Scenario: Shared state example
    When shared state is being initialized
    And shared state is being modified
    Then shared state remains modified
