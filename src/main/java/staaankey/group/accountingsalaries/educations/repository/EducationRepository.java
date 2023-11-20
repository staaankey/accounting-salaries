package staaankey.group.accountingsalaries.educations.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.educations.exceptions.EducationNotFindEducation;
import staaankey.group.accountingsalaries.educations.model.Education;
import staaankey.group.accountingsalaries.educations.util.EducationRowMapper;

@Repository
public class EducationRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EducationRowMapper rowMapper;

    public EducationRepository(NamedParameterJdbcTemplate jdbcTemplate, EducationRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public int save(Education education) {
        final var SQL_INSERT_EDUCATION = "insert into educations(degree, diploma_number, diploma_series, university_name) " +
                "values (:degree, :diplomaNumber, :diplomaSeries, :universityName)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_INSERT_EDUCATION, new MapSqlParameterSource()
                .addValue("degree", education.getDegree())
                .addValue("diplomaNumber", education.getDiplomaNumber())
                .addValue("diplomaSeries", education.getDiplomaSeries())
                .addValue("universityName", education.getUniversityName()), keyHolder, new String[] {"education_id"});
        return keyHolder.getKey().intValue();
    }

    public Education findEducationById(int id) throws EducationNotFindEducation {
        final var SQL_EDUCATION_BY_ID = "SELECT * FROM educations where education_id = :educationId";
        Education education = null;

        try {
            education = jdbcTemplate.queryForObject(SQL_EDUCATION_BY_ID, new MapSqlParameterSource()
                    .addValue("educationId", id), rowMapper);
        } catch (DataAccessException exception) {
            throw new EducationNotFindEducation("Education not found!");
        }
        return education;
    }

    public int delete(int id) {
        final var SQL_DELETE_EDUCATION_BY_ID = "DELETE FROM educations WHERE education_id = :educationId";
        return jdbcTemplate.update(SQL_DELETE_EDUCATION_BY_ID, new MapSqlParameterSource()
                .addValue("educationId", id));
    }
}
