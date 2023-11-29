package bg.softuni.bookstore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

    @Column
    private Date orderDate;

    @ManyToMany(targetEntity = Book.class, mappedBy = "orders")
    private List<Book> orderedBooks = new ArrayList<>();
}
