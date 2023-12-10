package bg.softuni.bookstore.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportCommentDto {

    private Integer bookId;

    private String comment;

    private Integer commentId;
}
