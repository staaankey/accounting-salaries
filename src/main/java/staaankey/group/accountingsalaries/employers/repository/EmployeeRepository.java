package staaankey.group.accountingsalaries.employers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.employers.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Long deleteEmployeeByEmployeeId(Long id);
    Employee findEmployeeByEmployeeId(Long id);
    Employee findEmployeeByRntrc(String rntrc);

}
