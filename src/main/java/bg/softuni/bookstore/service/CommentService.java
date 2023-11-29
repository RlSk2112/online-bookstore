package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.comment.ImportCommentDto;
import bg.softuni.bookstore.domain.entity.Book;
import bg.softuni.bookstore.domain.entity.Comment;
import bg.softuni.bookstore.repository.CommentRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private static final String COMMENTS_FILE_PATH = "src/main/resources/dbinit/comments.json";


    public boolean areImported() {
        return commentRepository.count() > 0;
    }

    public void importAllComments() throws IOException {

        ImportCommentDto[] importCommentDtos = gson.fromJson(readBooksFromFile(), ImportCommentDto[].class);

        StringBuilder stringBuilder = new StringBuilder();

        for (@Valid ImportCommentDto importCommentDto : importCommentDtos) {
            Comment comment = modelMapper.map(importCommentDto, Comment.class);
            Book book = bookService.getBookByISBN(importCommentDto.getBook());
            comment.setBook(book);
            commentRepository.save(comment);
            stringBuilder.append(String.format("Successfully imported book %s%n", book.getTitle()));
        }
        System.out.println(stringBuilder);


    }

    private String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(COMMENTS_FILE_PATH));
    }
}
