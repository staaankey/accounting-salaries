package staaankey.group.accountingsalaries.employers.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotSavedException;
import staaankey.group.accountingsalaries.employers.service.EmployeeService;
import staaankey.group.accountingsalaries.employers.web.dto.EmployeeDto;

@RestController("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
        } catch (EmployeeNotSavedException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), String.format("Employee with login %s not saved!", employeeDto.getFullName())), HttpStatus.NOT_FOUND);
        }
    }
}
