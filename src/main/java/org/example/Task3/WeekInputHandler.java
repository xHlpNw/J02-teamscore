package org.example.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeekInputHandler {
    public static void validate(int year, int week) {
        if (year < 1 || year > 9999)
            throw new IllegalArgumentException("Год должен быть в диапазоне 1-9999");

        int maxWeeks = WeekDateCalculator.getMaxIsoWeeks(year);
        if (week < 1 || week > maxWeeks)
            throw new IllegalArgumentException(
                    String.format("В %d году может быть от 1 до %d недель",
                            year, maxWeeks));
    }

    public static String getWeekRange(int year, int week) {
        validate(year, week);

        LocalDate monday = WeekDateCalculator.getMonday(year, week);
        LocalDate sunday = WeekDateCalculator.getSunday(year, week);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return String.format("%s - %s",
                formatter.format(monday),
                formatter.format(sunday));
    }
}
