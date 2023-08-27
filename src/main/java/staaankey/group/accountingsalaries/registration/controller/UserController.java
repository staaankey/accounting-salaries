package staaankey.group.accountingsalaries.registration.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import staaankey.group.accountingsalaries.registration.entity.User;
import staaankey.group.accountingsalaries.registration.service.PostgresUserDetailsService;

import java.util.List;

@RestController("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final PostgresUserDetailsService userService;

    public UserController(PostgresUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/find")
    public List<User> findAll() {
        return userService.findAll();
    }
}
