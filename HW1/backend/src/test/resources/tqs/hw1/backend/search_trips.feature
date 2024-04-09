Feature: Search trips

    Background: Search for trips
        Given I'm on the trip_search page

    Scenario: Search for trips
        When I select Aveiro as the departure city
        And I select Lisbon as the destination city
        And I choose the date 2024/04/15
        Then I get the trips:
            | id | departure | destination | startDayOfWeek  | startTime | endTime | price | busNumber | emptySeats |
            | 1  | Aveiro    | Lisbon      | Monday          | 12:30     | 16:00   | 20.00 | 1         | 43         |
            | 2  | Aveiro    | Lisbon      | Monday          | 15:15     | 18:55   | 25.00 | 15        | 10         |
            | 3  | Aveiro    | Lisbon      | Monday          | 14:30     | 18:10   | 21.30 | 3         | 0          |

    Scenario: Search for trips
        When I select Aveiro as the departure city
        And I select Lisbon as the destination city
        And I choose the date 2024/04/16
        Then I get the trips:
            | id  | departure | destination | startDayOfWeek | startTime | endTime | price | busNumber | emptySeats |
            | 5   | Lisbon    | Aveiro      | Tuesday        | 09:15     | 16:00   | 18.03 | 10        | 0          |
            | 11  | Lisbon    | Aveiro      | Tuesday        | 16:15     | 19:55   | 21.30 | 1         | 4          |
            | 50  | Lisbon    | Aveiro      | Tuesday        | 12:30     | 16:00   | 20.30 | 12        | 27         |
            