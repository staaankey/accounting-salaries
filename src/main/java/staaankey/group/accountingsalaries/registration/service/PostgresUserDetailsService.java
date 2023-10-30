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
        return userRepository.findAll();
    }

    public Long deleteUser(Long userId) throws UserNotFoundException {
        if (userRepository.deleteUserById(userId) == 0) {
            throw new UserNotFoundException("User not found");
        } else {
            return userRepository.deleteUserById(userId);
        }
    }

    public User saveUser(UserDto user) throws UserAlreadyExistException {
        return userRepository.save(convertToEntity(user));
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findUserById(id);
    }

    public Boolean loginUser(UserDto user) {
        User loginUser = userRepository.findUserByLogin(user.getLogin());

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
