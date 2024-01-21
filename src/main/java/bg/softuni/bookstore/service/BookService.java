package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.PageResult;
import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import bg.softuni.bookstore.domain.dto.book.UpdateBookDto;
import bg.softuni.bookstore.domain.dto.user.UserDetailsDto;
import bg.softuni.bookstore.domain.entity.Author;
import bg.softuni.bookstore.domain.entity.Book;
import bg.softuni.bookstore.repository.BookRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    private final AuthorService authorService;

    private static final String BOOKS_FILE_PATH = "src/main/resources/dbinit/books.json";

    public PageResult<ExportBookDto> getAllBooks(Pageable pageable, UserDetailsService viewer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDto map = modelMapper.map(authentication, UserDetailsDto.class);

        Page<Book> currentPage = bookRepository.findAll(pageable);
        List<ExportBookDto> exportBookDtos = currentPage.stream()
                .map(book -> modelMapper.map(book, ExportBookDto.class))
                .toList();
        return new PageResult<>(bookRepository.count(), exportBookDtos);
    }

    public Book getBookByISBN(String ISBN) {
        Optional<Book> optionalBook = bookRepository.findByISBN(ISBN);
        if (optionalBook.isEmpty()) {
            throw new IllegalArgumentException("No such book with given ISBN!");
        }
        return optionalBook.get();
    }

    public void delete(String isbn) {
        if (!bookRepository.existsByISBN(isbn)) {
            throw new IllegalArgumentException("No book with given isbn was found!");
        }
        bookRepository.deleteByISBN(isbn);
    }

    public void addBook(ImportBookDto importBookDto) {
        Optional<Book> optionalBook = bookRepository.findByISBN(importBookDto.getISBN());
        if (optionalBook.isPresent()) {
            throw new IllegalArgumentException("Book with given isbn already exists!");
        }
        Book book = modelMapper.map(importBookDto, Book.class);
        bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new IllegalArgumentException("No book with given id was found!");
        }
        return optionalBook.get();
    }

    public void updateBook(String isbn, UpdateBookDto bookImportDto) {
        //ToDo
    }

    public long getCount() {
        return bookRepository.count();
    }

    public void importBooks() throws IOException {
        if (areImported()) {
            return;
        }

        ImportBookDto[] importBookDtos = gson.fromJson(readBooksFromFile(), ImportBookDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        for (@Valid ImportBookDto importBookDto : importBookDtos) {
            Book book = modelMapper.map(importBookDto, Book.class);
            Author author = authorService.getAuthorById(importBookDto.getAuthor());
            book.setAuthor(author);
            bookRepository.save(book);
            stringBuilder.append(String.format("Successfully imported book %s%n", book.getTitle()));
        }
        System.out.println(stringBuilder);
    }

    private boolean areImported() {
        return bookRepository.count() > 0;
    }

    private String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOKS_FILE_PATH));
    }
}
