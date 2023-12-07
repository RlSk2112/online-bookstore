package bg.softuni.bookstore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column
    private String userName;

    @Column
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne
    private Role role;
}
