@book_search_sample
Feature: Book Search
  Allow efficient search of books in the library via various book characteristics

  Background: Book Search
    Given the following books exist:
      | title                                     | author        | ISBN          | date        | publisher             |
      | Uranium recipes for the nuclear engineer  | Homer Simpson | 1112309145190 | 2023-03-10  | Simpson Papers        |
      | Donut recipes for the couch potato        | Homer Simpson | 7122309145191 | 2024-02-11  | Simpson Papers        |
      | The Odyssey                               | Homer         | 7715349115199 | 2007-03-15  | Ancient Press         |
      | Nineteen eighty-four                      | George Orwell | 9947181058420 | 1949-06-07  | Secker & Warburg      |

  Scenario: Search books by title
    When I search for a book by title "Uranium recipes for the nuclear engineer"
    Then then I should get:
    | title | author | ISBN | date | publisher |
    | Uranium recipes for the nuclear engineer  | Homer Simpson | 1112309145190 | 2023-03-10  | Simpson Papers        |

  Scenario: Search books by author
    When I search for a book by author "Homer Simpson"
    Then then I should get:
    | title | author | ISBN | date | publisher |
    | Uranium recipes for the nuclear engineer  | Homer Simpson | 1112309145190 | 2023-03-10  | Simpson Papers        |
    | Donut recipes for the couch potato        | Homer Simpson | 7122309145191 | 2024-02-11  | Simpson Papers        |

  Scenario: Search books by ISBN
    When I search for a book by ISBN "9947181058420"
    Then then I should get:
    | title | author | ISBN | date | publisher |
    | Nineteen eighty-four                      | George Orwell | 9947181058420 | 1949-06-07  | Secker & Warburg      |

  Scenario: Search books by date
    When I search for a book published between 2000-01-01 and 2025-01-01
    Then then I should get:
    | title | author | ISBN | date | publisher |
    | Uranium recipes for the nuclear engineer  | Homer Simpson | 1112309145190 | 2023-03-10  | Simpson Papers        |
    | Donut recipes for the couch potato        | Homer Simpson | 7122309145191 | 2024-02-11  | Simpson Papers        |
    | The Odyssey                               | Homer         | 7715349115199 | 2007-03-15  | Ancient Press         |    

