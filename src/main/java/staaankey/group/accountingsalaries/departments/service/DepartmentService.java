package staaankey.group.accountingsalaries.departments.service;



import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.departments.exception.DepartmentNotFoundException;
import staaankey.group.accountingsalaries.departments.model.Department;
import staaankey.group.accountingsalaries.departments.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Integer saveDepartment(Department department) {
        return departmentRepository.saveDepartment(department);
    }

    public Integer deleteDepartment(Integer departmentId) throws DepartmentNotFoundException {
        if(departmentRepository.deleteDepartmentById(departmentId) == 0) {
            throw new DepartmentNotFoundException("Department not found!");
        } else {
            return departmentRepository.deleteDepartmentById(departmentId);
        }
    }

    public List<Department> getDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Department findDepartmentById(Integer departmentId) throws DepartmentNotFoundException {
        return departmentRepository.findDepartmentById(departmentId);
    }
}
