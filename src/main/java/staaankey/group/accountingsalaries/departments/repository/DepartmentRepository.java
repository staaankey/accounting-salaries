package staaankey.group.accountingsalaries.departments.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.departments.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Long deleteDepartmentById(Long id);
    Department findDepartmentById(Long id);
    Department findDepartmentByName(String name);
}
