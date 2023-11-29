package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/online-bookstore")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/all")
    @ResponseBody
    public Map<String, Object> getBooks() {
       Map<String, Object> resultMap = new HashMap<>();
       resultMap.put("totalRecords", bookService.getCount());
       resultMap.put("bookList", bookService.getAllBooks());
       return resultMap;
    }

    @GetMapping("/books")
    public ModelAndView getAllBooks(ModelAndView modelAndView) {
        List<ExportBookDto> allBooks = bookService.getAllBooks();
        modelAndView.addObject(allBooks);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
