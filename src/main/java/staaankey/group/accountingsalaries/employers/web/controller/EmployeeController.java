package staaankey.group.accountingsalaries.employers.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotFoundException;
import staaankey.group.accountingsalaries.employers.exceptions.EmployeeNotSavedException;
import staaankey.group.accountingsalaries.employers.model.Employee;
import staaankey.group.accountingsalaries.employers.service.EmployeeService;
import staaankey.group.accountingsalaries.employers.web.dto.EmployeeDto;
import staaankey.group.accountingsalaries.passports.exception.PassportNotFoundException;

@RestController
@RequestMapping("/employee")
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

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) {
        try {
            return new ResponseEntity<>(employeeService.delete(employeeId), HttpStatus.OK);
        } catch (EmployeeNotSavedException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Employee with %d not presented in database!".formatted(employeeId)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("find/{employeeId}")
    public ResponseEntity<?> findEmployeeById(@PathVariable int employeeId) {
        try {
            return new ResponseEntity<>(employeeService.findByEmployeeId(employeeId), HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Employee with id %d not found".formatted(employeeId)
                    ), HttpStatus.NOT_FOUND);
        }
    }
}
