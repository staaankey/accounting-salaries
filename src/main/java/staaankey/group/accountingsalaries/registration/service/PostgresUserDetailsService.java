package staaankey.group.accountingsalaries.registration.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.registration.entity.User;
import staaankey.group.accountingsalaries.registration.exception.UserAlreadyExistException;
import staaankey.group.accountingsalaries.registration.exception.UserNotFoundException;
import staaankey.group.accountingsalaries.registration.repos.UserRepository;
import staaankey.group.accountingsalaries.registration.web.dto.UserDto;

import java.util.List;

@Service
public class PostgresUserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public PostgresUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> findAll() {
        return userRepository.getUsers();
    }

    public Integer deleteUser(int userId) throws UserNotFoundException {
        if (userRepository.deleteUser(userId) == 0) {
            throw new UserNotFoundException("User not found");
        } else {
            return userRepository.deleteUser(userId);
        }
    }

    public Integer saveUser(UserDto user) throws UserAlreadyExistException {
        return userRepository.saveUser(convertToEntity(user));
    }

    public User findUserById(Integer id) throws UserNotFoundException {
        return userRepository.findUserById(id);
    }

    public Boolean loginUser(UserDto user) {
        User loginUser = userRepository.getUser(user.getLogin());

        if (loginUser != null) {
            String password = user.getPassword();
            String encodedPassword = loginUser.getPassword();
            Boolean isPwdRigth = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRigth) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
