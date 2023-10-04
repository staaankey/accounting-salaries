package staaankey.group.accountingsalaries.salaries.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {
    public boolean defaultPaymentByMonth(@RequestParam String employee) {
        return true;
    }
}
