package staaankey.group.accountingsalaries.salaries.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SalaryService {

    private long getDaysWithoutWeekends(LocalDate start, LocalDate end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
