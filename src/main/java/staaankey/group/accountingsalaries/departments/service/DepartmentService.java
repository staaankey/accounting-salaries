package staaankey.group.accountingsalaries.departments.service;



import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.departments.exception.DepartmentNotFoundException;
import staaankey.group.accountingsalaries.departments.model.Department;
import staaankey.group.accountingsalaries.departments.repository.DepartmentRepository;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Long deleteDepartment(Long departmentId) throws DepartmentNotFoundException {
        if(departmentRepository.deleteDepartmentById(departmentId) == 0) {
            throw new DepartmentNotFoundException("Department not found!");
        } else {
            return departmentRepository.deleteDepartmentById(departmentId);
        }
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        return departmentRepository.findDepartmentById(departmentId);
    }
}
