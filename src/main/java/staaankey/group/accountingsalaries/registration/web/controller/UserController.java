package staaankey.group.accountingsalaries.registration.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.registration.exception.UserAlreadyExistException;
import staaankey.group.accountingsalaries.registration.exception.UserNotFoundException;
import staaankey.group.accountingsalaries.registration.service.PostgresUserDetailsService;
import staaankey.group.accountingsalaries.registration.web.dto.UserDto;

@RestController("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final PostgresUserDetailsService userService;

    public UserController(PostgresUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam int userId) {
        try {
            return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "User with id " + userId + " not found"), HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.CONFLICT.value(), "User with login: " + userDto.getLogin() + " already exist"), HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<?> findUserById(Integer userId) {
        try {
            return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "User with id " + userId + " not found"), HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto);
    }
}
