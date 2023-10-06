package staaankey.group.accountingsalaries.employers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotFoundException;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotSavedException;
import staaankey.group.accountingsalaries.employers.model.Employee;
import staaankey.group.accountingsalaries.employers.repository.EmployeeRepository;
import staaankey.group.accountingsalaries.employers.web.dto.EmployeeDto;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Integer save(EmployeeDto employeeDto) throws EmployeeNotSavedException {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeeNotSavedException("Can't save Employee");
        }
    }

    public int delete(int employeeId) throws EmployeeNotSavedException {
        if (employeeRepository.delete(employeeId) == 0) {
            throw new EmployeeNotSavedException("Employee not presented in database!");
        } else {
            return employeeRepository.delete(employeeId);
        }
    }


    public Employee findByEmployeeId(int employeeId) throws EmployeeNotFoundException {
        return employeeRepository.findEmployeeById(employeeId);
    }
}
