Feature: Book base operations

  Scenario: Successfully added a book
    Given a book with title "Game of Thrones" to be inserted to database
    When the book is inserted
    Then the status should be created
    And verify that book with the given title "Game of Thrones" exists

  Scenario: Failed to a book with the same isbn
    Given a book with isbn "1234567890123" to be inserted to database
    When i try to add a book with the same isbn "1234567890123"
    Then the response status should be bad request