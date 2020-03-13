Feature: User can add an item with a valid title using text based UI

  Scenario: User can add an item with a valid title
    Given a user has chosen to add an item
    When a "Refactoring UI" is entered
    Then the item is saved succesfully

  Scenario: Adding an item with empty title gives an error message
    Given a user has chosen to add an item
    When "" is entered
    Then an error message is shown