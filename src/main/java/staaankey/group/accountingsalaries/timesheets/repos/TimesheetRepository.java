package staaankey.group.accountingsalaries.timesheets.repos;

import io.swagger.models.auth.In;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.*;

@Repository
public class TimesheetRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TimesheetRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Integer writeTimesheet(int userId, List<LocalDate> scheduleTimesheet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        final var SQL_INSERT = "insert into timesheets (schedule, hours, user_id) values (:schedule, :hours, :userId)";

        jdbcTemplate.update(SQL_INSERT, new MapSqlParameterSource()
                        .addValue("schedule", scheduleTimesheet)
                        .addValue("hours", 6)
                        .addValue("userId", userId), keyHolder, new String[] {"timesheet_id"});


        return keyHolder.getKey().intValue();
    }
}
