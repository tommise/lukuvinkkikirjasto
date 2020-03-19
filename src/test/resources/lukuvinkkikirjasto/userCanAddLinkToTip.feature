Feature: User can add a link to tip while it is being created (optional)

    Scenario: User can add a link
        Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        And a link "www.lonelyplanet.com" is entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with link "www.lonelyplanet.com" can be found from the system

    Scenario: User can leave link empty
        Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        And a link "" is entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with title "Lonely Planet Berlin" can be found from the system