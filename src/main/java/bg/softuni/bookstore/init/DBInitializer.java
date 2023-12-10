package bg.softuni.bookstore.init;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.service.*;
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

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedAuthors();
        seedBooks();
        seedComments();
        seedUsers();
    }

    private void seedRoles() {
        roleService.importRoles();
    }

    private void seedBooks() throws IOException {
        bookService.importBooks();
    }

    private void seedAuthors() throws IOException {
        authorService.importAuthors();
    }

    private void seedComments() throws IOException {
        commentService.importAllComments();
    }

    private void seedUsers() throws IOException {
        userService.importUsers();
    }
}
