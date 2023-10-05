package staaankey.group.accountingsalaries.employers.repository;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.employers.model.Employee;
import staaankey.group.accountingsalaries.employers.web.dto.EmployeeDto;

@Repository
public class EmployeeRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer save(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final var SQL_INSERT_EMPLOYEE = "insert into employers(full_name, photo_url, phone, address, passport_id, workplace_id, titles_id, education_id, department_id)" +
                "VALUES (:fullName, :photoUrl, :phone, :address, :passportId, :workplaceId, :titlesId, :educationId, :departmentId)";
        jdbcTemplate.update(SQL_INSERT_EMPLOYEE, new MapSqlParameterSource()
                .addValue("fullName", employee.getFullName())
                .addValue("photoUrl", employee.getPhone())
                .addValue("phone", employee.getPhone())
                .addValue("address", employee.getAddress())
                .addValue("passportId", employee.getPassportId())
                .addValue("workplaceId", employee.getWorkplaceId())
                .addValue("titlesId", employee.getTitlesId())
                .addValue("educationId", employee.getEducationId())
                .addValue("departmentId", employee.getDepartmentId()), keyHolder, new String[]{"employee_id"});
        return keyHolder.getKey().intValue();
    }
}
