package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.author.ExportAuthorDto;
import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import bg.softuni.bookstore.service.BookService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetAllBooksShouldFindAllBooks() throws Exception {
        //Given
        String url = "/api/bookstore/books/all";
        List<ExportBookDto> books = new ArrayList<>();
        ExportBookDto bookOne = new ExportBookDto("The boogeyman", "2023-10-10", 40.5,
                "1234567891230", new ExportAuthorDto());
        ExportBookDto bookTwo = new ExportBookDto("The shining", "2023-11-11", 45.5,
                "1234567891221", new ExportAuthorDto());
        books.add(bookOne);
        books.add(bookTwo);
        when(bookService.getAllBooks()).thenReturn(books);

        //When
        //Then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(books)));
    }

    @Test
    public void testDeleteBookShouldReturnStatusBadRequest() throws Exception {
        //Given
        String isbn = "1234567890123";
        String url = "/api/bookstore/books/" + isbn;

        //When
        doThrow(IllegalArgumentException.class).when(bookService).delete(isbn);

        //Then
        mockMvc.perform(delete(url))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteBookShouldDeleteBookCorrectly() throws Exception {
        //Given
        String isbn = "1234567890123";
        String url = "/api/bookstore/books/" + isbn;

        //When
        doNothing().when(bookService).delete(isbn);

        //Then
        mockMvc.perform(delete(url))
                .andExpect(status().isNoContent());
    }

}
