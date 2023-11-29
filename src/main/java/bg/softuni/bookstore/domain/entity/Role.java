package bg.softuni.bookstore.domain.entity;

import bg.softuni.bookstore.domain.constant.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity {

    @Column
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;
}
