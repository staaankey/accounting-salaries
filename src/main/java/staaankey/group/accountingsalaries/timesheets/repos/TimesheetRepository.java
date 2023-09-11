package staaankey.group.accountingsalaries.timesheets.repos;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository ;

@Repository
public class TimesheetRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TimesheetRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Integer writeTimesheet(int userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        final var SQL_INSERT = "insert into timesheets (hours, user_id) values (:hours, :userId)";

        jdbcTemplate.update(SQL_INSERT, new MapSqlParameterSource()
                        .addValue("hours", 6)
                        .addValue("userId", userId), keyHolder, new String[] {"timesheet_id"});


        return keyHolder.getKey().intValue();
    }
}
