package staaankey.group.accountingsalaries.registration.util;

import org.springframework.stereotype.Component;
import staaankey.group.accountingsalaries.registration.entity.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
