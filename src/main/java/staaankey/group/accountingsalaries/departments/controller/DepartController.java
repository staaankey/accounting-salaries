package staaankey.group.accountingsalaries.departments.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.model.Department;
import staaankey.group.accountingsalaries.departments.service.DepartmentService;
import staaankey.group.accountingsalaries.departments.web.dto.DepartmentDto;

import java.util.List;

@RestController("/departments")
@CrossOrigin("http://localhost:3000")
public class DepartController {
    private final DepartmentService departmentService;

    public DepartController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save")
    public Integer saveDepartment(@RequestBody DepartmentDto department) {
        return departmentService.saveDepartment(convertToEntity(department));
    }

    @DeleteMapping("/delete")
    public Integer deleteDepartment(@RequestParam Integer departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/findById")
    public Department getDepartmentById(@RequestParam Integer departmentId) {
        return departmentService.findDepartmentById(departmentId);
    }


    private Department convertToEntity(DepartmentDto departmentDto) {
        var department = new Department();
        department.setParentId(departmentDto.getParentId());
        department.setName(departmentDto.getName());
        return department;
    }
}
