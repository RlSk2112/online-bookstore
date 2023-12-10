package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.domain.entity.Role;
import bg.softuni.bookstore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void importRoles() {
        if (areImported()) {
            return;
        }
        Arrays.stream(RoleName.values())
                .forEach(this::addRole);
    }

    public Role getAdminRole() {
        Optional<Role> optionalRole = roleRepository.findByRoleName(RoleName.ADMIN);
        if (optionalRole.isEmpty()) {
            throw new IllegalArgumentException("Role Admin not present!");
        }
        return optionalRole.get();
    }

    public Role getUserRole() {
        Optional<Role> optionalRole = roleRepository.findByRoleName(RoleName.USER);
        if (optionalRole.isEmpty()) {
            throw new IllegalArgumentException("Role User not present!");
        }
        return optionalRole.get();
    }

    private boolean areImported() {
        return roleRepository.count() > 0;
    }

    private void addRole(RoleName roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        roleRepository.save(role);
    }
}
