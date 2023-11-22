package staaankey.group.accountingsalaries.registration.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.registration.entity.User;
import staaankey.group.accountingsalaries.registration.exception.UserAlreadyExistException;
import staaankey.group.accountingsalaries.registration.exception.UserNotFoundException;
import staaankey.group.accountingsalaries.registration.repos.UserRepository;
import staaankey.group.accountingsalaries.registration.web.dto.UserDto;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class PostgresUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public PostgresUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
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

    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        UserBuilder userBuilder = withUsername(user.getLogin());
        userBuilder.password(user.getPassword());
        userBuilder.roles("ADMIN");

        return userBuilder.build();
    }
}
