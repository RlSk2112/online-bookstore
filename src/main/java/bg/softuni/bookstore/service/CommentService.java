package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.PageResult;
import bg.softuni.bookstore.domain.dto.comment.ExportCommentDto;
import bg.softuni.bookstore.domain.dto.comment.ImportCommentDto;
import bg.softuni.bookstore.domain.entity.Book;
import bg.softuni.bookstore.domain.entity.Comment;
import bg.softuni.bookstore.repository.CommentRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private static final String COMMENTS_FILE_PATH = "src/main/resources/dbinit/comments.json";

    public PageResult<ExportCommentDto> getAllComments(Long bookId, Pageable pageable) {
        Book book = bookService.getBookById(bookId);
        Page<Comment> currentPage = commentRepository.findAllByBook(book, pageable);
        List<ExportCommentDto> exportCommentDtos = currentPage.stream()
                .map(comment -> modelMapper.map(comment, ExportCommentDto.class))
                .toList();
        return new PageResult<>(commentRepository.count(), exportCommentDtos);
    }

    public void importAllComments() throws IOException {
        if (areImported()) {
            return;
        }
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

    private boolean areImported() {
        return commentRepository.count() > 0;
    }

    private String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(COMMENTS_FILE_PATH));
    }
}
