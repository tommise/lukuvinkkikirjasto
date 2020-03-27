Feature: User can add tag to Tip

    Scenario: tags can be added to to tip while creating tip

    Scenario: Tags can be seen in tips list
        Given some tip items have been added
        And a user has chosen command 2 to view items
        Then a list containing tags is shown

    Scenario: Multiple tags can be added by separatig them with comma