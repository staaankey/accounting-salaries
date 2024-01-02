package staaankey.group.accountingsalaries.employers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotFoundException;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotSavedException;
import staaankey.group.accountingsalaries.employers.model.Employee;
import staaankey.group.accountingsalaries.employers.repository.EmployeeRepository;
import staaankey.group.accountingsalaries.employers.web.dto.EmployeeDto;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(EmployeeDto employeeDto) throws EmployeeNotSavedException {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeeNotSavedException("Can't save Employee");
        }
    }

    @Transactional
    public Long delete(Long employeeId) throws EmployeeNotSavedException {
        if (employeeRepository.deleteEmployeeByEmployeeId(employeeId) == 0) {
            throw new EmployeeNotSavedException("Employee not presented in database!");
        } else {
            return employeeRepository.deleteEmployeeByEmployeeId(employeeId);
        }
    }

    public Employee findByEmployeeId(Long employeeId) throws EmployeeNotFoundException {
        return employeeRepository.findEmployeeByEmployeeId(employeeId);
    }

    public Employee findEmployeeByRntrc(String rntrc) {
        return employeeRepository.findEmployeeByRntrc(rntrc);
    }
}
