package bg.softuni.bookstore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column
    private String userName;

    @Column
    private String password;

    private String email;

    @OneToOne
    private Role role;
}
