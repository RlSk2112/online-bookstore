package bg.softuni.bookstore.domain.dto.user;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginUserDto implements Serializable {

    private String username;

    private String password;
}
