package staaankey.group.accountingsalaries.salaries.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import staaankey.group.accountingsalaries.employers.model.Employee;

@RestController
public class SalaryController {
    public boolean defaultPaymentByMonth(@RequestParam Employee employeeId) {
        return true;
    }
}
