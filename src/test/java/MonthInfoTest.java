import org.example.MonthInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MonthInfoTest {
    @Test
    @DisplayName("Default constructor must set today's date")
    void defaultConstructor() {
        LocalDate before = LocalDate.now();
        MonthInfo mi = new MonthInfo();
        LocalDate after = LocalDate.now();
        assertFalse(mi.getDate().isBefore(before));
        assertFalse(mi.getDate().isAfter(after));
    }

    @Test
    void parametrizedConstructor() {
        LocalDate date = LocalDate.of(2025, 11, 22);
        MonthInfo mi = new MonthInfo(date);
        assertEquals(mi.getDate(), date);
    }

    @Test
    void getMonthFullName() {
        MonthInfo mi = new MonthInfo(LocalDate.of(2025, 11, 22));
        assertEquals("ноября", mi.getMonthFullName().toLowerCase());
    }

    @Test
    void getMonthNumber() {
        MonthInfo mi = new MonthInfo(LocalDate.of(2025, 11, 22));
        assertEquals(11, mi.getMonthNumber());
    }

    @Test
    void getFirstDayOfWeek() {
        // 1 ноября 2025 - суббота
        MonthInfo mi = new MonthInfo(LocalDate.of(2025, 11, 22));
        assertEquals("сб", mi.getFirstDayOfWeekShort());
    }

    @Test
    void getLastDateOfMonth() {
        MonthInfo mi = new MonthInfo(LocalDate.of(2025, 2, 22));
        assertEquals(
                LocalDate.of(2025, 2, 28),
                mi.getLastDateOfMonth());
    }

    @Test
    @DisplayName("Getting the month last date in a leap year")
    void getLastDateOfMonthLeapYear() {
        MonthInfo mi = new MonthInfo(LocalDate.of(2024, 2, 22));
        assertEquals(
                LocalDate.of(2024, 2, 29),
                mi.getLastDateOfMonth());
    }

    @Test
    void getDaysInMonth() {
        MonthInfo mi = new MonthInfo(LocalDate.of(2025, 8, 11));
        assertEquals(31, mi.getDaysInMonth());
    }

    @Test
    @DisplayName("Getting number of days in february of a leap year")
    void getDaysInMonthLeapYear() {
        MonthInfo notLeapYear = new MonthInfo(
                LocalDate.of(2025, 2, 5));
        assertEquals(28, notLeapYear.getDaysInMonth());

        MonthInfo leapYear = new MonthInfo(
                LocalDate.of(2024, 2, 5));
        assertEquals(29, leapYear.getDaysInMonth());
    }

    @ParameterizedTest
    @ValueSource(strings =
            {"2025-10-01", "2025-11-15", "2025-12-31"})
    void getQuarter(String dateString) {
        MonthInfo mi = new MonthInfo(LocalDate.parse(dateString));
        assertEquals("2025 Q4", mi.getQuarter());
    }
}
