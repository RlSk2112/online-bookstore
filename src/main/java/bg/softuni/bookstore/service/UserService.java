package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.user.ImportUserDto;
import bg.softuni.bookstore.domain.dto.user.LoginUserDto;
import bg.softuni.bookstore.domain.entity.User;
import bg.softuni.bookstore.repository.UserRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USERS_FILE_PATH = "src/main/resources/dbinit/users.json";

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;

    private final Gson gson;

    public String login(LoginUserDto loginuserDto) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(loginuserDto.getUsername(),
                loginuserDto.getPassword());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("No such user!");
        }
        return optionalUser.get().getUsername();
    }

    public void register(ImportUserDto importUserDto) {
        User user = modelMapper.map(importUserDto, User.class);
        setRole(user);
        userRepository.save(user);
    }

    public void importUsers() throws IOException {
        if (areImported()) {
            return;
        }

        ImportUserDto[] importUserDtos = gson.fromJson(readUsersFromFile(), ImportUserDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        for (ImportUserDto importUserDto : importUserDtos) {
            User user = modelMapper.map(importUserDto, User.class);
            setRole(user);
            userRepository.save(user);
            stringBuilder.append(String.format("Successfully added user with username %s to DB!%n", user.getUsername()));
        }
        System.out.println(stringBuilder.toString().trim());
    }

    private boolean areImported() {
        return userRepository.count() > 0;
    }

    private String readUsersFromFile() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    private void setRole(User user) {
        if (userRepository.count() == 0) {
            user.setRole(roleService.getAdminRole());
            return;
        }
        user.setRole(roleService.getUserRole());
    }

    public void logout() {
        //ToDo
    }
}
