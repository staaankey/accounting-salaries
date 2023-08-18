package staaankey.group.accountingsalaries.timesheets.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import staaankey.group.accountingsalaries.timesheets.service.TimesheetService;

import java.time.LocalDate;
import java.util.HashMap;

@RestController("/timesheet")
public class TimesheetController {
    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

//    @PostMapping("/generate")
//    public Integer generateTimesheetForUser(@RequestParam String username,
//                                                 @RequestParam int year,
//                                                 @RequestParam int month) {
//
//        return timesheetService.generateTimesheet(username, year, month);
//    }


    @GetMapping("/generation")
    public HashMap<LocalDate, Integer> userTimesheet(@RequestParam String username,
                                                     @RequestParam int year,
                                                     @RequestParam int month)
    {
        return timesheetService.weekendCount(year, month, username);
    }
}
