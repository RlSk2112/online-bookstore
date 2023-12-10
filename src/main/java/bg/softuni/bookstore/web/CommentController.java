package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.PageResult;
import bg.softuni.bookstore.domain.dto.comment.ExportCommentDto;
import bg.softuni.bookstore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/{id}")
    public PageResult<ExportCommentDto> getComments(@PageableDefault(sort = "id", size = 5) Pageable pageable,
                                                    @PathVariable(name = "id") Long id) {
        return commentService.getAllComments(id, pageable);
    }
}
