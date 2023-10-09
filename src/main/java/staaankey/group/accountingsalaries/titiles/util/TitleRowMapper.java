package staaankey.group.accountingsalaries.titiles.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import staaankey.group.accountingsalaries.titiles.entity.Title;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component
public class TitleRowMapper implements RowMapper<Title> {
    @Override
    public Title mapRow(ResultSet rs, int rowNum) throws SQLException {
        Title title = new Title();
        title.setId(rs.getInt("title_id"));
        title.setTitleName(rs.getString("title_name"));
        title.setDateOfIssue(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date_of_issue"))));
        title.setSerialNumber(rs.getString("serial_number"));
        title.setNameOfPlace(rs.getInt("name_of_place"));
        title.setSpeciality(rs.getInt("speciality"));
        return title;
    }
}
