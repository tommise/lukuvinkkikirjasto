Feature: User can view saved tip items as a list showing tip's title and link

    Scenario: User can view a list of saved tip items
        Given database has been initialized with items
        And a user has chosen command 2
        Then a list containing right items is shown
