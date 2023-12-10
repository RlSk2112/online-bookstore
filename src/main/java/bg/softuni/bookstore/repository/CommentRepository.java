package bg.softuni.bookstore.repository;

import bg.softuni.bookstore.domain.entity.Book;
import bg.softuni.bookstore.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByBook(Book book, Pageable pageable);

    List<Comment> findAllByBook(Book book);
}
