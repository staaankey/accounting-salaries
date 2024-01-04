package staaankey.group.accountingsalaries.educations.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.educations.exceptions.EducationNotFindEducation;
import staaankey.group.accountingsalaries.educations.model.Education;
import staaankey.group.accountingsalaries.educations.util.EducationRowMapper;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    void deleteByDiplomaNumber(Integer number);
    Education findByDiplomaNumber(Integer number);

}
