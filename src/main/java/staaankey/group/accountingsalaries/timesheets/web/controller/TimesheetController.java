package staaankey.group.accountingsalaries.timesheets.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import staaankey.group.accountingsalaries.timesheets.service.TimesheetSerice;
import staaankey.group.accountingsalaries.timesheets.web.dto.TimesheetDto;

import java.time.LocalDate;
import java.util.Map;

@RestController("/timesheet")
public class TimesheetController {
    private final TimesheetSerice timesheetSerice;

    public TimesheetController(TimesheetSerice timesheetSerice) {
        this.timesheetSerice = timesheetSerice;
    }

    @PostMapping("/generate")
    public Integer generateTimesheetForUser(@RequestParam String username,
                                                 @RequestParam int year,
                                                 @RequestParam int month) {

        return timesheetSerice.generateTimesheet(username, year, month);
    }
}
