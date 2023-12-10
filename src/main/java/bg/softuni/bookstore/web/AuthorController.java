package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.author.ExportAuthorDto;
import bg.softuni.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/all")
    public List<ExportAuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
