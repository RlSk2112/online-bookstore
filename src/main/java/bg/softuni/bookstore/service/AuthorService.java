package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.author.ExportAuthorDto;
import bg.softuni.bookstore.domain.dto.author.ImportAuthorDto;
import bg.softuni.bookstore.domain.entity.Author;
import bg.softuni.bookstore.repository.AuthorRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private static final String AUTHORS_FILE_PATH = "src/main/resources/dbinit/authors.json";

    public boolean areImported() {
        return authorRepository.count() > 0;
    }

    public void importAuthors() throws IOException {

        ImportAuthorDto[] importAuthorDtos = gson.fromJson(readAuthorsFromFile(), ImportAuthorDto[].class);

        StringBuilder stringBuilder = new StringBuilder();

        for (@Valid ImportAuthorDto importAuthorDto : importAuthorDtos) {
            Author author = modelMapper.map(importAuthorDto, Author.class);
            authorRepository.save(author);
            stringBuilder.append(String.format("Successfully imported author %s %s%n", author.getFirstName(),
                    author.getLastName()));
        }
        System.out.println(stringBuilder);
    }

    public Author getAuthorByFirstAndLastName(String firstName, String lastName) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (optionalAuthor.isEmpty()) {
            throw new IllegalArgumentException("No such author with searched names!");
        }
        return optionalAuthor.get();
    }

    private String readAuthorsFromFile() throws IOException {
        return Files.readString(Path.of(AUTHORS_FILE_PATH));
    }

    public Author getAuthorById(Integer author) {
        Optional<Author> optionalAuthor = authorRepository.findById(author.longValue());
        if (optionalAuthor.isEmpty()) {
            throw new IllegalArgumentException("No such author with given id!");
        }
        return optionalAuthor.get();
    }

    public List<ExportAuthorDto> getAllAuthors() {
        List<Author> allAuthors = authorRepository.findAll();
        return allAuthors.stream()
                .map(author -> modelMapper.map(author, ExportAuthorDto.class))
                .collect(Collectors.toList());
    }
}
