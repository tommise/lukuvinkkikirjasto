Feature: User can add tag to Tip

    Scenario: tags can be added to to tip while creating tip
        Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        And a link "" is entered
        And description "" is entered
        And tag "matkailu" is entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with tag "matkailu" can be found from the system

    Scenario: Tags can be seen in tips list
        Given some tip items have been added
        And a user has chosen command 2 to view items
        Then a list containing tags is shown

    Scenario: Multiple tags can be added by separatig them with comma
        Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        And a link "" is entered
        And description "" is entered
        And tags "matkailu, eurooppa" are entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with tags "matkailu, eurooppa" can be found from the system