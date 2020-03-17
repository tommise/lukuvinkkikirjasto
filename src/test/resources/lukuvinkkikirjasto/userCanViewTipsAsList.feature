Feature: User can view saved tip items as a list showing tip's title and link

    Scenario: User can view a list of saved tip items
        Given some tip items have been added
        And a user has chosen command 2 to view items
        Then a list containing right items is shown

    Scenario: When no tips have been saved user is informed
        Given no tip items have been added
        And a user has chosen command 2 to view items
        Then system will respond with "Sinulla ei ole yhtaan lukuvinkkia."