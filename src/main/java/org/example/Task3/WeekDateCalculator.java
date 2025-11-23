package org.example.Task3;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WeekDateCalculator {
    /**
     * @param year
     * @return LocalDate of first Monday in the year
     */
    public static LocalDate getFirstMonday(int year) {
        LocalDate jan4 = LocalDate.of(year, 1, 4);
        return jan4.with(DayOfWeek.MONDAY);
    }

    /**
     * @param year
     * @param week
     * @return LocalDate of Monday in this week
     */
    public static LocalDate getMonday(int year, int week) {
        LocalDate firstMonday = getFirstMonday(year);
        return firstMonday.plusWeeks(week - 1);
    }

    /**
     * @param year
     * @param week
     * @return LocalDate of Sunday in this week
     */
    public static LocalDate getSunday(int year, int week) {
        return getMonday(year, week).plusDays(6);
    }

    public static LocalDate getLastMonday(int year) {
        // 28 декабря всегда в последней ISO-неделе
        LocalDate dec28 = LocalDate.of(year, 12, 28);
        return dec28.with(DayOfWeek.MONDAY);
    }

    /**
     * max number of ISO-weeks in this year
     * @param year
     * @return 52 or 53
     */
    private static int getMaxIsoWeeks(int year) {
        LocalDate firstMonday = getFirstMonday(year);
        LocalDate lastMonday = getLastMonday(year);
        return (int) ChronoUnit.WEEKS.between(firstMonday, lastMonday) + 1;
    }
}
