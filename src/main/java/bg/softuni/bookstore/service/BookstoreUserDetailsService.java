package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.entity.Role;
import bg.softuni.bookstore.domain.entity.User;
import bg.softuni.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class BookstoreUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(BookstoreUserDetailsService::getUser)
                .orElseThrow(() -> new UsernameNotFoundException("No user with given name was found!"));
    }

    private static UserDetails getUser(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(BookstoreUserDetailsService::map).toList())
                .build();
    }

    private static GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.getRoleName().name()
        );
    }
}
