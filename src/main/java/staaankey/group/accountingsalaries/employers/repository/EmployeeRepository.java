package staaankey.group.accountingsalaries.employers.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotFoundException;
import staaankey.group.accountingsalaries.employers.model.Employee;
import staaankey.group.accountingsalaries.employers.util.EmployeeRowMapper;
import staaankey.group.accountingsalaries.registration.exception.UserNotFoundException;

@Repository
public class EmployeeRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EmployeeRowMapper rowMapper;

    public EmployeeRepository(NamedParameterJdbcTemplate jdbcTemplate, EmployeeRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
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

    public Integer delete(int employeeId) {
        final var SQL_DELETE_EMPLOYEE = "DELETE FROM employers WHERE employee_id = :employeeId";
        return jdbcTemplate.update(SQL_DELETE_EMPLOYEE, new MapSqlParameterSource()
                .addValue("employeeId", employeeId));
    }

    public Employee findEmployeeById(int employeeId) throws EmployeeNotFoundException {
        final var SQL_GET_EMPLOYEE_BY_ID = "SELECT * FROM employers WHERE employee_id = :employeeId";
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject(SQL_GET_EMPLOYEE_BY_ID, new MapSqlParameterSource()
                    .addValue("employeeId", employeeId), rowMapper);
        } catch (DataAccessException exception) {
            throw new EmployeeNotFoundException("Employee not found in database!");
        }
        return employee;
    }
}
