package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.UpdateBookDto;
import bg.softuni.bookstore.domain.dto.book.ExportBookDto;
import bg.softuni.bookstore.domain.dto.book.ImportBookDto;
import bg.softuni.bookstore.domain.entity.Author;
import bg.softuni.bookstore.domain.entity.Book;
import bg.softuni.bookstore.repository.BookRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    private final AuthorService authorService;

    private static final String BOOKS_FILE_PATH = "src/main/resources/dbinit/books.json";


    public List<ExportBookDto> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .map(book -> modelMapper.map(book, ExportBookDto.class))
                .collect(Collectors.toList());
    }


    public boolean areImported() {
        return bookRepository.count() > 0;
    }

    public void importBooks() throws IOException {

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

    private String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOKS_FILE_PATH));
    }

    public Book getBookByISBN(String ISBN) {
        Optional<Book> optionalBook = bookRepository.findByISBN(ISBN);
        if (optionalBook.isEmpty()) {
            throw new IllegalArgumentException("No such book with given ISBN!");
        }
        return optionalBook.get();
    }

    public long getCount() {
        return bookRepository.count();
    }

    public void delete(String isbn) {
        if (!bookRepository.existsByISBN(isbn)) {
            throw new IllegalArgumentException("No book with given isbn was found!");
        }
        bookRepository.deleteByISBN(isbn);
    }

    public void addBook(ImportBookDto book) {
        //ToDo
    }

    public void updateBook(String isbn, UpdateBookDto bookImportDto) {
        //ToDo
    }
}
