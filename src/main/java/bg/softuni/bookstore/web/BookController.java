package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.UpdateBookDto;
import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import bg.softuni.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookstore/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getBooks() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalRecords", bookService.getCount());
        resultMap.put("bookList", bookService.getAllBooks());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping
    public ModelAndView getAllBooks(ModelAndView modelAndView) {
        List<ExportBookDto> allBooks = bookService.getAllBooks();
        modelAndView.addObject(allBooks);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ExportBookDto> create(@Valid @RequestBody ImportBookDto book) {
        bookService.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{isbn}")
    @ResponseBody
    public ResponseEntity<ExportBookDto> updateBook(@PathVariable String isbn, @RequestBody UpdateBookDto bookImportDto) {
        bookService.updateBook(isbn, bookImportDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{isbn}")
    @ResponseBody
    public ResponseEntity<ExportBookDto> deleteBook(@PathVariable String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
