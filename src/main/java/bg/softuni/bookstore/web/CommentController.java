package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.entity.Comment;
import bg.softuni.bookstore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
}
