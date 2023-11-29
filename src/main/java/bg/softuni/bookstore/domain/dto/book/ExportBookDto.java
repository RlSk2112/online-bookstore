package bg.softuni.bookstore.domain.dto.book;

import bg.softuni.bookstore.domain.dto.author.ExportAuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportBookDto implements Serializable {

    private String title;

    private String publicationDate;

    private Double price;

    private String isbn;

    private ExportAuthorDto author;
}
