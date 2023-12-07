package bg.softuni.bookstore.init;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.service.AuthorService;
import bg.softuni.bookstore.service.BookService;
import bg.softuni.bookstore.service.CommentService;
import bg.softuni.bookstore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DBInitializer implements CommandLineRunner {

    private final AuthorService authorService;

    private final BookService bookService;

    private final CommentService commentService;

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedAuthors();
        seedBooks();
        seedComments();
    }

    private void seedRoles() {
        if (roleService.areImported()) {
            return;
        }
        Arrays.stream(RoleName.values())
                .forEach(roleService::addRole);
    }

    private void seedBooks() throws IOException {
        if (bookService.areImported()) {
            return;
        }
        bookService.importBooks();
    }

    private void seedAuthors() throws IOException {
        if (authorService.areImported()) {
            return;
        }
        authorService.importAuthors();
    }

    private void seedComments() throws IOException {
        if (commentService.areImported()) {
            return;
        }
        commentService.importAllComments();
    }
}
