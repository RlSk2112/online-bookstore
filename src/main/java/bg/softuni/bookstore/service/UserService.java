package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.constant.RoleName;
import bg.softuni.bookstore.domain.dto.user.ImportUserDto;
import bg.softuni.bookstore.domain.dto.user.LoginUserDto;
import bg.softuni.bookstore.domain.entity.Role;
import bg.softuni.bookstore.domain.entity.User;
import bg.softuni.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public void login(LoginUserDto loginuserDto) {
        Optional<User> optionalUser = userRepository.findByUserNameAndPassword(loginuserDto.getUserName(),
                loginuserDto.getPassword());
    }

    public void register(ImportUserDto importUserDto) {
        User user = modelMapper.map(importUserDto, User.class);
        if (userRepository.count() == 0) {
            user.setRole(new Role(RoleName.ADMIN));
        } else {
            user.setRole(new Role(RoleName.USER));
        }
        userRepository.save(user);
    }
}
