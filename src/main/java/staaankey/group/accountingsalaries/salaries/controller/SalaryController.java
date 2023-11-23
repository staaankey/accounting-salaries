package staaankey.group.accountingsalaries.salaries.controller;

import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.employers.model.Employee;

@RestController
@RequestMapping("/salaries")
public class SalaryController {

    @GetMapping("{/{employeeId}")
    public int defaultPaymentByMonth(@PathVariable int employeeId) {
        return 0;
    }


    
    public int getTaxes(int employeeId) {
        return 0;
    }


}
