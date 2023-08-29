package staaankey.group.accountingsalaries.registration.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.registration.entity.User;
import staaankey.group.accountingsalaries.registration.service.PostgresUserDetailsService;
import staaankey.group.accountingsalaries.registration.web.UserDto;

import java.util.List;

@RestController("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final PostgresUserDetailsService userService;

    public UserController(PostgresUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAllUsers")
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/deleteUser")
    public Integer deleteUser(@RequestParam int userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/saveUser")
    public Integer saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(convertToEntity(userDto));
    }

    @GetMapping("/findByUserId")
    public User findUserById(Integer userId) {
        return userService.findUserById(userId);
    }


    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }
}
