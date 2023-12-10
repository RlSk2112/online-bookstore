package bg.softuni.bookstore.domain.entity;

import bg.softuni.bookstore.domain.constant.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Column
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;

    @OneToMany(targetEntity = User.class, mappedBy = "role")
    private List<User> user;
}
