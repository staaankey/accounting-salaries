package staaankey.group.accountingsalaries.registration.repos;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.registration.entity.User;
import staaankey.group.accountingsalaries.registration.util.UserRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    public UserRepository(DataSource dataSource, UserRowMapper rowMapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }

    public User getUser(String username) {

        final String sql = "SELECT * FROM users WHERE login=:username";

        SqlParameterSource param = new MapSqlParameterSource("username", username);

        User result = jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(User.class));

        return result;
    }

    public List<User> getUsers() {
        final var GET_ALL_USERS = "SELECT * FROM users";
        return jdbcTemplate.query(GET_ALL_USERS, rowMapper);
    }

}
