package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.domain.entity.Role;
import bg.softuni.bookstore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void addRole(RoleName roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        roleRepository.save(role);
    }

    public boolean areImported() {
        return roleRepository.count() > 0;
    }
}
