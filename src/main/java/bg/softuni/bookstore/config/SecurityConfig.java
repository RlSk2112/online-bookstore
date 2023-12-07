package bg.softuni.bookstore.config;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.domain.dto.author.ImportAuthorDto;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        Object importAuthorDto = new ImportAuthorDto();
        security.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/api/bookstore/books", "/api/bookstore/users/login", "/api/bookstore/users/register")
                .permitAll()
                .requestMatchers("/api/bookstore/users/register/user").permitAll()
                .requestMatchers("/api/bookstore/users/login/user").permitAll()
                .requestMatchers("/api/bookstore/books").hasRole(RoleName.ADMIN.name())
                .requestMatchers("src/main/resources/static/css/**").permitAll()
                .requestMatchers("src/main/resources/static/js/**").permitAll()
                .requestMatchers("src/main/resources/static/images/**").permitAll()
                .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin.loginPage("/api/bookstore/users/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/api/bookstore/home")
                        .failureForwardUrl("/api/bookstore/users/login-error")
        ).logout(
                logout -> logout
                        .logoutUrl("/api/bookstore/users/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
        );
        return security.build();
    }
}
