package controller;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Created by rjpvr on 12-10-2016.
 */
public class DateController {

    public LocalDate getWeekOfYearDate(int week, int year){
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate ld = LocalDate.now()
            .withYear(year)
            .with(weekFields.weekOfYear(), week)
            .with(weekFields.dayOfWeek(), 1);
        return ld;
    }

}
