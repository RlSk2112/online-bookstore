Feature: Books base crud operations

  Scenario: Getting available books information
    Given several books in the database
    When a get request is sent
    Then i should receive status ok
    And correct book information