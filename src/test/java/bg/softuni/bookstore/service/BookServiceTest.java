package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testDeleteShouldThrowIfNoSuchBookWithGivenIsbn() {
        //Given
        String isbn = "-0000000000000";
        String message = "No book with given isbn was found!";

        //When
        //Then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> bookService.delete(isbn));
        assertEquals(message, exception.getMessage());
    }


    //ToDo
}
