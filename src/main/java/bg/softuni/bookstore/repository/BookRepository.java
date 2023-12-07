package bg.softuni.bookstore.repository;

import bg.softuni.bookstore.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    Page<Book> findAll(@PageableDefault(size = 5, sort = "id") Pageable pageable);

    Optional<Book> findByISBN(String ISBN);

    void deleteByISBN(String isbn);

    boolean existsByISBN(String isbn);
}
