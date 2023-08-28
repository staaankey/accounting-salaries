package staaankey.group.accountingsalaries.departments.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.departments.model.Department;
import staaankey.group.accountingsalaries.departments.util.DepartmentRowMapper;

import java.util.List;

@Repository
public class DepartmentRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DepartmentRowMapper rowMapper;

    public DepartmentRepository(NamedParameterJdbcTemplate jdbcTemplate, DepartmentRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public Integer saveDepartment(Department department) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final var SQL_INSERT_DEPARTMENT = "INSERT INTO departments(parent_id, name) VALUES (:parentId, :name)";
        jdbcTemplate.update(SQL_INSERT_DEPARTMENT, new MapSqlParameterSource()
                .addValue("parentId", department.getParentId())
                .addValue("name", department.getName()), keyHolder, new String[]{"department_id"});
        return keyHolder.getKey().intValue();
    }


    public List<Department> getAllDepartments() {
        final var SQL_GET_DEPARTMENTS = "SELECT * FROM departments";
        return jdbcTemplate.query(SQL_GET_DEPARTMENTS, rowMapper);
    }


    public Department findDepartmentById(int departmentId) {
        final var SQL_GET_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE department_id = :departmentId";
        Department department = null;
        try {
            department = jdbcTemplate.queryForObject(SQL_GET_DEPARTMENT_BY_ID, new MapSqlParameterSource()
                    .addValue("departmentId", departmentId), rowMapper);
        } catch (DataAccessException exception) {
            System.out.println("failed to get department");
        }
        return department;
    }

    public Integer deleteDepartmentById(int departmentId) {
        final var SQL_DELETE_DEPARTMENT_BY_ID = "DELETE FROM departments WHERE department_id = :departmentId";
        return jdbcTemplate.update(SQL_DELETE_DEPARTMENT_BY_ID, new MapSqlParameterSource()
                .addValue("departmentId", departmentId));
    }
}
