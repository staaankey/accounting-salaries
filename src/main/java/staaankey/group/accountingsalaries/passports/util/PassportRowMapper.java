package staaankey.group.accountingsalaries.passports.util;

import org.springframework.stereotype.Component;
import staaankey.group.accountingsalaries.passports.model.Passport;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component
public class PassportRowMapper implements RowMapper<Passport> {
    @Override
    public Passport mapRow(ResultSet rs, int rowNum) throws SQLException {
        var passport = new Passport();
        passport.setId(rs.getInt("passport_id"));
        passport.setSeries(rs.getString("series"));
        passport.setDateOfBirth(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date_of_birth"))));
        passport.setPlaceOfBirth(rs.getString("place_of_birth"));
        passport.setDateOfIssue(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date_of_issue"))));
        passport.setDateOfExpiring(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date_of_expiring"))));
        passport.setPlaceOfIssue(rs.getString("place_of_issue"));
        return passport;
    }
}
