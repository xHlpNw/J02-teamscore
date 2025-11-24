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
        // 7 января всегда в той же неделе, что и первый понедельник января
        LocalDate jan7 = LocalDate.of(year, 1, 7);
        return jan7.with(DayOfWeek.MONDAY);
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
        // 31 декабря всегда в последней неделе
        LocalDate dec31 = LocalDate.of(year, 12, 31);
        return dec31.with(DayOfWeek.MONDAY);
    }

    /**
     * max number of ISO-weeks in this year
     * @param year
     * @return 52 or 53
     */
    public static int getMaxIsoWeeks(int year) {
        LocalDate firstMonday = getFirstMonday(year);
        LocalDate lastMonday = getLastMonday(year);
        return (int) ChronoUnit.WEEKS.between(firstMonday, lastMonday) + 1;
    }
}
