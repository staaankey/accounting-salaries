package staaankey.group.accountingsalaries.timesheets.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import staaankey.group.accountingsalaries.timesheets.service.TimesheetService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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


    @GetMapping("/displayTimesheet")
    public String displayUserTimesheet(@RequestParam String username,
                                       @RequestParam int year,
                                       @RequestParam int month
    ) {
        HashMap<LocalDate, Integer> jcbc = timesheetService.weekendCount(year, month, username);
        StringBuilder stringMapTable = new StringBuilder();
        stringMapTable.append("<table>");

        Iterator it = jcbc.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            stringMapTable.append("<tr><td>" + pair.getKey() + "</td><td>" + pair.getValue() + "</td></tr>");
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        String mapTable = stringMapTable.toString();
        return mapTable;
    }
}
