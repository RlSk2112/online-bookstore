package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.user.ImportUserDto;
import bg.softuni.bookstore.domain.dto.user.LoginUserDto;
import bg.softuni.bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bookstore/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login/user")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDto loginuserDto) {
        userService.login(loginuserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> register(@Valid @RequestBody ImportUserDto importUserDto) {
        userService.register(importUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
