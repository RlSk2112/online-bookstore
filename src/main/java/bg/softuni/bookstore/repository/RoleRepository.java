package bg.softuni.bookstore.repository;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);
}
