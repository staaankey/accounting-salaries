package staaankey.group.accountingsalaries.timesheets.service;

import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.registration.repos.UserRepository;
import staaankey.group.accountingsalaries.timesheets.repos.TimesheetRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

@Service
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;
    private final UserRepository userRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, UserRepository userRepository) {
        this.timesheetRepository = timesheetRepository;
        this.userRepository = userRepository;
    }

    public Integer generateTimesheet(String username, int year, int month) {
        var user = userRepository.getUser(username);
        return timesheetRepository.writeTimesheet(user.getUserId());
    }

    public HashMap<LocalDate, Integer> weekendCount(int year, int month, String username) {
        var days = new HashMap<LocalDate, Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weekend = 0;
        for (int i = 1; i<= daysInMonth; i++) {
            calendar.set(year, month - 1, i);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SUNDAY) {
                days.put(LocalDate.of(year, month, i), 6);
            } else {
                days.put(LocalDate.of(year, month, i), 0);
            }
        }
        return days;
    }
}