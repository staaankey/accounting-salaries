package staaankey.group.accountingsalaries.security.educations.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import staaankey.group.accountingsalaries.security.educations.model.Education;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class EducationRowMapper implements RowMapper<Education> {
    @Override
    public Education mapRow(ResultSet rs, int rowNum) throws SQLException {
        var education = new Education();
        education.setId(rs.getInt("education_id"));
        education.setDegree(rs.getString("degree"));
        education.setDiplomaNumber(rs.getInt("diploma_number"));
        education.setDiplomaSeries(rs.getString("diploma_series"));
        education.setUniversityName(rs.getInt("university_name"));
        return education;
    }
}
