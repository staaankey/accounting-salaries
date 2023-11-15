package staaankey.group.accountingsalaries.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class TestController {
    @GetMapping("/time")
    public String index() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
        return dateFormat.format(date);
    }

    @GetMapping("/main")
    public String index2() {
        return "Hello LTSNU Program";
    }
}
