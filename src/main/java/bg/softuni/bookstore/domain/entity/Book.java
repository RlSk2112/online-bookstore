package bg.softuni.bookstore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column
    private Date publicationDate;

    @Column
    private BigDecimal price;

    @ManyToOne(targetEntity = Author.class)
    private Author author;

    @ManyToMany(targetEntity = Order.class)
    @JoinTable(joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Order> orders = new ArrayList<>();

    @OneToMany(targetEntity = Comment.class, mappedBy = "book")
    private List<Comment> comments = new ArrayList<>();
}
