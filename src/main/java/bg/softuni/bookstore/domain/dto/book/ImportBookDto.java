package bg.softuni.bookstore.domain.dto.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ImportBookDto {

    @NotNull
    private String title;

    @NotNull
    @Size(min = 13, max = 13)
    private String ISBN;

    @NotNull
    private Integer author;
}
