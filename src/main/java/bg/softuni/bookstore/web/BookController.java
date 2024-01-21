package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.PageResult;
import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import bg.softuni.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthenticationManager authenticationManager;

    @Secured(value = "USER")
    @GetMapping("/all")
    public PageResult<ExportBookDto> getBooks(@PageableDefault(sort = "id", size = 5, page = 1) Pageable pageable,
                                              @AuthenticationPrincipal UserDetailsService viewer) {
        return bookService.getAllBooks(pageable, viewer);
    }

    @Secured(value = "USER")
    @PostMapping
    public ResponseEntity<ExportBookDto> create(@Valid @RequestBody ImportBookDto book) {
        bookService.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured(value = "USER")
    @DeleteMapping("/{isbn}")
    public ResponseEntity<ExportBookDto> deleteBook(@PathVariable String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
