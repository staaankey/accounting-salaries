package staaankey.group.accountingsalaries.titiles.repos;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.titiles.entity.Title;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotFoundException;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotSavedException;
import staaankey.group.accountingsalaries.titiles.util.TitleRowMapper;

import java.util.Objects;

@Repository
public class TitleRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final TitleRowMapper rowMapper;

    public TitleRepository(NamedParameterJdbcTemplate jdbcTemplate, TitleRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public Integer save(Title title) throws TitleNotSavedException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            final var SQL_INSERT_TITLE = "INSERT INTO titles(title_name, speciality, serial_number, date_of_issue, name_of_place) VALUES " +
                    "(:titleName, :speciality, :serialNumber, :dateOfIssue, :nameOfPlace)";

            jdbcTemplate.update(SQL_INSERT_TITLE, new MapSqlParameterSource()
                    .addValue("titleName", title.getTitleName())
                    .addValue("speciality", title.getSpeciality())
                    .addValue("serialNumber", title.getSerialNumber())
                    .addValue("dateOfIssue", title.getDateOfIssue())
                    .addValue("nameOfPlace", title.getNameOfPlace()), keyHolder, new String[] {"title_id"});
            return Objects.requireNonNull(keyHolder.getKey()).intValue();
        } catch (DataAccessException exception) {
            throw new TitleNotSavedException("Title not saved!");
        }
    }

    public Integer delete(int id) {
        final var SQL_DELETE_TITLE = "DELETE FROM titles WHERE title_id = :titleId";
        return jdbcTemplate.update(SQL_DELETE_TITLE, new MapSqlParameterSource()
                .addValue("titleId", id));
    }

    public Title findById(int id) throws TitleNotFoundException {
        final var SQL_GET_TITLE = "SELECT * FROM titles WHERE title_id = :titleId";
        Title title = null;

        try {
            title = jdbcTemplate.queryForObject(SQL_GET_TITLE, new MapSqlParameterSource()
                    .addValue("titleId", id), rowMapper);
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            throw new TitleNotFoundException("Title not saved!");
        }
        return title;
    }
}
