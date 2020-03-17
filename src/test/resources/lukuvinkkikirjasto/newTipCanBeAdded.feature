Feature: User can add an item with a valid title using text based UI

    Scenario: User can add an item with a valid title
        Given a user has chosen command one to add items
        When title "Lonely Planet Berlin" is entered
        Then system will respond with "Lukuvinkki lisatty!"
        Then tip with title "Lonely Planet Berlin" can be found from the system
    
    Scenario: Adding an item with empty title gives an error message
        Given a user has chosen command one to add items
        When title "" is entered
        Then system will respond with "Otsikko ei voi olla tyhja!"
