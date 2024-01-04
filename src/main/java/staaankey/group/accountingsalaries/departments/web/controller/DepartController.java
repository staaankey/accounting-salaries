package staaankey.group.accountingsalaries.departments.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.departments.exception.DepartmentNotFoundException;
import staaankey.group.accountingsalaries.departments.model.Department;
import staaankey.group.accountingsalaries.departments.service.DepartmentService;
import staaankey.group.accountingsalaries.departments.web.dto.DepartmentDto;

@RestController
@RequestMapping("/departments")
@CrossOrigin("http://localhost:3000")
public class DepartController {
    private final DepartmentService departmentService;

    public DepartController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDto department) {
        return new ResponseEntity<>(departmentService.saveDepartment(convertToEntity(department)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable long id) {
        try {
            return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.NO_CONTENT);
        } catch (DepartmentNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Department with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getDepartments() {
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        try {
            Department department = departmentService.findDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Department with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<?> getDepartmentByName(@RequestParam(value="name") String name) {
        try {
            Department department = departmentService.findDepartmentByName(name);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Department with name: " + name + "not found"), HttpStatus.NOT_FOUND);
        }
    }


    private Department convertToEntity(DepartmentDto departmentDto) {
        var department = new Department();
        department.setParentId(departmentDto.getParentId());
        department.setName(departmentDto.getName());
        return department;
    }
}
