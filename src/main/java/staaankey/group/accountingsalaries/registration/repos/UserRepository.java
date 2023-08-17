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
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    public UserRepository(DataSource dataSource, UserRowMapper rowMapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }

    public User getUser(String username) {

        String sql = "SELECT * FROM users WHERE login=:username";

        SqlParameterSource param = new MapSqlParameterSource("username", username);

        User result = jdbcTemplate.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(User.class));

        return result;
    }

}
