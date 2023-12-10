package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.user.ImportUserDto;
import bg.softuni.bookstore.domain.dto.user.LoginUserDto;
import bg.softuni.bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/login")
    public ResponseEntity<String> loginPage(@Valid @RequestBody LoginUserDto loginuserDto) {
        String loggedUser = userService.login(loginuserDto);
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody ImportUserDto importUserDto) {
        userService.register(importUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDto loginuserDto) {
        String loggedUser = userService.login(loginuserDto);
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        userService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
