Feature: Book trip

    Background: Book a trip
        Given I'm on the search results page 

    Scenario: Book a trip
        Given I've searched for trips from Aveiro to Lisbon
        When I select the trip with id 2
        And fill out the form with my personal information and submit it:
            | name        | CC           | email                       | phoneNumber |
            | João Santos | 111222347PH3 | joao.santos102@supermail.pt | 111222333   |
        Then I receive my ticket information on screen:
            | id | tripId | status  | price | date       | startTime | endTime | busNumber | CC           | email                       |
            | 25 | 2      | PENDING | 25.00 | 2024-04-15 | 15:15     | 18:55   | 15        | 111222347PH3 | joao.santos102@supermail.pt |

    Scenario: Book a trip
        Given I've searched for trips from Lisbon to Aveiro
        When I select the trip with id 50
        And fill out the form with my personal information and submit it:
            | name        | CC           | email                       | phoneNumber |
            | João Santos | 111222347PH3 | joao.santos102@supermail.pt | 111222333   |
        Then I receive my ticket information on screen:
            | id | tripId  | status  | price | date       | startTime | endTime | busNumber | CC           | email                       |
            | 17 | 50      | PENDING | 20.30 | 2024-04-16 | 12:30     | 16:00   | 12        | 111222347PH3 | joao.santos102@supermail.pt |