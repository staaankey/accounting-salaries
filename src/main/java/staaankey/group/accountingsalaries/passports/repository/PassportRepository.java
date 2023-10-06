package staaankey.group.accountingsalaries.passports.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.passports.exception.PassportNotCreatedException;
import staaankey.group.accountingsalaries.passports.exception.PassportNotFoundException;
import staaankey.group.accountingsalaries.passports.model.Passport;
import staaankey.group.accountingsalaries.passports.util.PassportRowMapper;

import static java.util.Objects.*;

@Repository
public class PassportRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PassportRowMapper rowMapper;

    public PassportRepository(NamedParameterJdbcTemplate jdbcTemplate, PassportRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }


    public Integer save(Passport passport) throws PassportNotCreatedException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            final var SQL_INSERT_PASSPORT = "INSERT INTO passports(series, date_of_birth, place_of_birth, date_of_issue, date_of_expiring, place_of_issue) " +
                    "VALUES (:series, :dateOfBirth, :placeOfBirth, :dateOfIssue, :dateOfExpiring, :placeOfIssue)";
            jdbcTemplate.update(SQL_INSERT_PASSPORT, new MapSqlParameterSource()
                    .addValue("series", passport.getSeries())
                    .addValue("dateOfBirth", passport.getDateOfBirth())
                    .addValue("placeOfBirth", passport.getPlaceOfBirth())
                    .addValue("dateOfIssue", passport.getDateOfIssue())
                    .addValue("dateOfExpiring", passport.getDateOfExpiring())
                    .addValue("placeOfIssue", passport.getPlaceOfIssue()), keyHolder, new String[]{"passport_id"});
            return requireNonNull(keyHolder.getKey()).intValue();

        } catch (DataAccessException exception) {
            throw new PassportNotCreatedException("Passport not saved in database!");
        }
    }

    public Integer delete(int passportId) {
        final var SQL_DELETE_PASSPORT = "DELETE FROM passports WHERE passport_id = :passportId";
        return jdbcTemplate.update(SQL_DELETE_PASSPORT, new MapSqlParameterSource()
                .addValue("passportId", passportId));
    }

    public Passport findByPassportId(int passportId) throws PassportNotFoundException {
        final var SQL_GET_PASSPORT_BY_ID = "SELECT * FROM passports WHERE passport_id = :passportId";
        Passport passport = null;
        try {
            passport = jdbcTemplate.queryForObject(SQL_GET_PASSPORT_BY_ID, new MapSqlParameterSource()
                    .addValue("passportId", passportId), rowMapper);
        } catch (DataAccessException exception) {
            throw new PassportNotFoundException("Passport not found in database!");
        }
        return passport;
    }
}
