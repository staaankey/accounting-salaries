package staaankey.group.accountingsalaries.registration.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.registration.entity.User;
import staaankey.group.accountingsalaries.registration.repos.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class PostgresUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;


    public PostgresUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }

    public List<User> findAll() {
        return userRepository.getUsers();
    }

    public Integer deleteUser(int userId) {
        return userRepository.deleteUser(userId);
    }

    public Integer saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }
}
