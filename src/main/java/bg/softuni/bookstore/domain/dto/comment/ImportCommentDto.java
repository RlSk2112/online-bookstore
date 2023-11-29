package bg.softuni.bookstore.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ImportCommentDto {

    @NotBlank
    private String comment;

    @NotNull
    private String book;
}
