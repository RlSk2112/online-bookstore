package bg.softuni.bookstore.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
//import org.springframework.security.access.annotation.Secured;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(targetEntity = Role.class)
    private List<Role> roles = new ArrayList<>();

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public String getUsername() {
        return username;
    }

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public void setUsername(String username) {
        this.username = username;
    }

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public String getPassword() {
        return password;
    }

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public void setPassword(String password) {
        this.password = password;
    }

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public String getEmail() {
        return email;
    }

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public void setEmail(String email) {
        this.email = email;
    }

//    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    public List<Role> getRoles() {
        return roles;
    }

//    @Secured(value = {"ROLE_ADMIN"})
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
