@calc_sample
Feature: Basic Arithmetic

  Background: Calculator
    Given a functioning calculator

  Scenario: Addition
    When I add 10 and 5
    Then I obtain 15
  
  Scenario: Subtraction
    When I subtract 5 from 3
    Then I obtain -2
  
  Scenario: Division
    When I divide 21 by 7
    Then I obtain 3
  
  Scenario: Multiplication
    When I multiply 7 by 3
    Then I obtain 21
  
  Scenario Outline:
    When I add <a> and <b>
    Then I obtain <c>
  
  Examples: Addition
    | a   | b   | c   |
    | 2   | 209 | 211 |
    | -10 | 10  | 0   |

  Scenario Outline: Subtractions
    When I subtract <a> from <b>
    Then I obtain <c>
  
  Examples: 
    | a   | b   | c   |
    | 2   | 209 | 207 |
    | -10 | 10  | 20  |

  Scenario Outline: Divisions
    When I divide <a> by <b>
    Then I obtain <c>
  
  Examples: 
    | a   | b   | c   |
    | 208 | 2   | 104 |
    | -10 | 10  | -1  |

  Scenario Outline: Multiplications
    When I multiply <a> by <b>
    Then I obtain <c>
  
  Examples: 
    | a   | b   | c    |
    | 208 | 2   | 416  |
    | -10 | 10  | -100 |



