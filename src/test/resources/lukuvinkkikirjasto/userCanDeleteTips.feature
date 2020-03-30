Feature: User can delete tips

    Scenario: Tips can be deleted based on their index number
        Given some tip items have been added 
        And a user has chosen command 3 to delete items
        When a user has entered tip id "1"
        Then system will respond with "Vinkki poistettu."
        Then tip with id "1" can not be found from the system


    Scenario: User is informed if tries to delete tip that does not exist
            Given some tip items have been added 
            And a user has chosen command 3 to delete items
            When a user has entered tip id "999"
            Then system will respond with "Sinulla ei ole vinkkia annetulla id:lla."

        