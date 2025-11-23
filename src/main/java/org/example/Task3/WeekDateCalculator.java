package org.example.Task3;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDateCalculator {
    public static LocalDate getMonday(int year, int week) {
        LocalDate jan4 = LocalDate.of(year, 1, 4);
        LocalDate firstMonday = jan4.with(DayOfWeek.MONDAY);
        return firstMonday.plusWeeks(week - 1);
    }

    public static LocalDate getSunday(int year, int week) {
        return getMonday(year, week).plusDays(6);
    }
}
