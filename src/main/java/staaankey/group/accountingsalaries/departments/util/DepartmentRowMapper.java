package staaankey.group.accountingsalaries.departments.util;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import staaankey.group.accountingsalaries.departments.exception.model.Department;


import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        var department = new Department();
        department.setId(rs.getInt("department_id"));
        department.setName(rs.getString("name"));
        department.setParentId(rs.getInt("parent_id"));
        return department;
    }
}
