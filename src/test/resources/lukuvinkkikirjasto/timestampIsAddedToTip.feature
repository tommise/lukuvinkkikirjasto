Feature: A timestamp(date, time) is automically added to saved tip

    Scenario: A timestamp is added to tip
        Given a user has chosen command one to add items
        When new tip fields are filled
        Then the tip is saved with timestamp
