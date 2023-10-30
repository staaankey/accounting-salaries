package staaankey.group.accountingsalaries.departments.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.departments.exception.DepartmentNotFoundException;
import staaankey.group.accountingsalaries.departments.model.Department;
import staaankey.group.accountingsalaries.departments.service.DepartmentService;
import staaankey.group.accountingsalaries.departments.web.dto.DepartmentDto;

@RestController("/departments")
@CrossOrigin("http://localhost:3000")
public class DepartController {
    private final DepartmentService departmentService;

    public DepartController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDto department) {
        return new ResponseEntity<>(departmentService.saveDepartment(convertToEntity(department)), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDepartment(@RequestParam Long departmentId) {
        try {
            return new ResponseEntity<>(departmentService.deleteDepartment(departmentId), HttpStatus.NO_CONTENT);
        } catch (DepartmentNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Department with id " + departmentId + " not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getDepartments() {
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getDepartmentById(@RequestParam Long departmentId) {
        try {
            Department department = departmentService.findDepartmentById(departmentId);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Department with id " + departmentId + " not found"), HttpStatus.NOT_FOUND);
        }
    }


    private Department convertToEntity(DepartmentDto departmentDto) {
        var department = new Department();
        department.setParentId(departmentDto.getParentId());
        department.setName(departmentDto.getName());
        return department;
    }
}
