package org.example.Task1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Информация о дате, переданной в конструкторе.
 * Если дата не передана, используется текущую дату и время.
 */
public class MonthInfo {
    private final LocalDate date; // Дата

    public MonthInfo() {
        date = LocalDate.now();
    }

    public MonthInfo(LocalDate date) {
        this.date = date;
    }

    /**
     * Дата, переданная объекту
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Полное название месяца на русском языке.
     */
    public String getMonthFullName() {
        return date.getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("ru", "RU"));
    }

    /**
     * Номер месяца в виде числа (1 — январь, 2 — февраль, …).
     */
    public int getMonthNumber() {
        return date.getMonthValue();
    }

    /**
     * День недели первого числа месяца в виде краткого текста
     */
    public String getFirstDayOfWeekShort() {
        LocalDate firstDay = date.withDayOfMonth(1);
        DayOfWeek dow = firstDay.getDayOfWeek();

        return switch (dow) {
            case MONDAY -> "пн";
            case TUESDAY -> "вт";
            case WEDNESDAY -> "ср";
            case THURSDAY -> "чт";
            case FRIDAY -> "пт";
            case SATURDAY -> "сб";
            case SUNDAY -> "вс";
        };
    }

    /**
     * Дата последнего дня месяца.
     */
    public LocalDate getLastDateOfMonth() {
        YearMonth ym = YearMonth.from(date);
        return ym.atEndOfMonth();
    }

    /**
     * Количество дней в месяце.
     */
    public int getDaysInMonth() {
        return YearMonth.from(date).lengthOfMonth();
    }

    /**
     * Номер квартала, к которому относится месяц, с годом и приставкой Q
     */
    public String getQuarter() {
        int month = date.getMonthValue();
        int q = (month - 1) / 3 + 1;
        return date.getYear() + " Q" + q;
    }
}
