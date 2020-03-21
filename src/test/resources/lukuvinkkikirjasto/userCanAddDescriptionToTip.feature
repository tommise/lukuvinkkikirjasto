Feature: User can add description to tip and it shows in listing (optional)

    Scenario: User can add description
        Given Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        And a link "" is entered
        And description "Berliiniin olis kylla kiva paasta, eika maksa paljoo" is entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with description "Berliiniin olis kylla kiva paasta, eika maksa paljoo" can be found from the system

    Scenario: User can leave description empty
        Given Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        And a link "" is entered
        And description "" is entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with title "Lonely Planet Berlin" can be found from the system

    Scenario: Description field is shown in tips listing
        Given some tips with descriptions have been added
        And a user has chosen command 2 to view items
        Then a list containing items with right descriptions is shown