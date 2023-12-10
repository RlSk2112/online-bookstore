package bg.softuni.bookstore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResult<T> {

    private Long totalRecords;

    private List<T> items;
}