package staaankey.group.accountingsalaries.employers.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import staaankey.group.accountingsalaries.employers.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        var employee = new Employee();

        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFullName(rs.getString("full_name"));
        employee.setPhotoUrl(rs.getString("photo_url"));
        employee.setPhone(rs.getString("phone"));
        employee.setAddress(rs.getString("address"));
        employee.setPassportId(rs.getInt("passport_id"));
        employee.setWorkplaceId(rs.getInt("workplace_id"));
        employee.setTitlesId(rs.getInt("titles_id"));
        employee.setEducationId(rs.getInt("education_id"));
        employee.setDepartmentId(rs.getInt("department_id"));
        return employee;
    }
}
