package staaankey.group.accountingsalaries.registration.repos;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        final String GET_ONE_USER = "SELECT * FROM users WHERE login=:username";
        SqlParameterSource param = new MapSqlParameterSource("username", username);
        User result = jdbcTemplate.queryForObject(GET_ONE_USER, param, BeanPropertyRowMapper.newInstance(User.class));
        return result;
    }

    public List<User> getUsers() {
        final var GET_ALL_USERS = "SELECT * FROM users";
        return jdbcTemplate.query(GET_ALL_USERS, rowMapper);
    }


    public Integer deleteUser(int userId) {
        final var SQL_DELETE_USER = "DELETE FROM users WHERE user_id = :userId";
        return jdbcTemplate.update(SQL_DELETE_USER, new MapSqlParameterSource()
                .addValue("userId", userId));
    }

    public User findUserById(int userId) {
        final var SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE user_id = :userId";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new MapSqlParameterSource()
                    .addValue("userId", userId), rowMapper);
        } catch (DataAccessException exception) {
            System.out.println("user not found");
        }
        return user;
    }

    public Integer saveUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final var SQL_INSERT_USER = "INSERT INTO users(login, password) VALUES (:login, :password)";
        jdbcTemplate.update(SQL_INSERT_USER, new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword()), keyHolder, new String[]{"user_id"});
        return keyHolder.getKey().intValue();
    }

}
