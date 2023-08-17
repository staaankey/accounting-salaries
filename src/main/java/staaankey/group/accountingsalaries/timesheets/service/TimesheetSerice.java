package staaankey.group.accountingsalaries.timesheets.service;

import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.registration.repos.UserRepository;
import staaankey.group.accountingsalaries.timesheets.repos.TimesheetRepository;
import staaankey.group.accountingsalaries.timesheets.web.dto.TimesheetDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Service
public class TimesheetSerice {
    private final TimesheetRepository timesheetRepository;
    private final UserRepository userRepository;

    public TimesheetSerice(TimesheetRepository timesheetRepository, UserRepository userRepository) {
        this.timesheetRepository = timesheetRepository;
        this.userRepository = userRepository;
    }

    public Integer generateTimesheet(String username, int year, int month) {
        var user = userRepository.getUser(username);
        timesheetRepository.writeTimesheet(user.getUserId(), weekendCount(year, month));
        return 1;
    }

    public static List<LocalDate> weekendCount(int year, int month) {
        var days = new ArrayList<LocalDate>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weekend = 0;
        for (int i= 1; i<= daysInMonth; i++) {
            calendar.set(year, month - 1, i);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SUNDAY) {
                days.add(LocalDate.of(year, month, i));
            } else {
                weekend++;
            }
        }
        return days;
    }
}