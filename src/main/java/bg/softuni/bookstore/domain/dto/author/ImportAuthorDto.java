package bg.softuni.bookstore.domain.dto.author;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ImportAuthorDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
