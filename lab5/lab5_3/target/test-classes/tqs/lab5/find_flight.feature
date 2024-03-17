@flight_sample
Feature: Find flights
  Find flights from the given departure city to the given destination city.  

  Background: Flight reservation application
    Given I'm on the flight search page "https://blazedemo.com/"

  # All flight searches are the same in the BlazeDemo website, so to avoid being redundant, I'll keep it to one scenario, which can be easily converted to
  # a generic outline if necessary.
  
  Scenario: Find flights
    When I select "San Diego" as the departure city and "Buenos Aires" as the destination
    And I request to find flights
    Then I get the following flights by ID:
      | 43   |
      | 234  | 
      | 9696 | 
      | 12   | 
      | 4346 |

