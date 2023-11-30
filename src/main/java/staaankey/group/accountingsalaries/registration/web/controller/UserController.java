package staaankey.group.accountingsalaries.registration.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.registration.exception.UserAlreadyExistException;
import staaankey.group.accountingsalaries.registration.exception.UserNotFoundException;
import staaankey.group.accountingsalaries.registration.service.PostgresUserDetailsService;
import staaankey.group.accountingsalaries.registration.web.dto.UserDto;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final PostgresUserDetailsService userService;

    public UserController(PostgresUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "User with id " + id + " not found"), HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "User with id " + id + " not found"), HttpStatus.NOT_FOUND
            );
        }
    }
}
