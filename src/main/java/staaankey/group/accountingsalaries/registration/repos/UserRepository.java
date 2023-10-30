package staaankey.group.accountingsalaries.registration.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.registration.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Long deleteUserById(Long id);
    User findUserById(Long id);
    User findUserByLogin(String login);

}
