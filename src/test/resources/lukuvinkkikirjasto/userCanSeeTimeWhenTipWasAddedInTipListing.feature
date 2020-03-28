Feature: user can see time when tip was added in tip listing

    Scenario: Date field is shown in tips listing
        Given some tip items have been added
        And a user has chosen command 2 to view items
        Then a list containing items with dates is shown

    Scenario: user can see tips in time order
        Given tip items with different timestamp have been added
        And a user has chosen command 2 to view items
        Then the tip entered first appears last in list
