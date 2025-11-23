package Task1Tests;

import org.example.Task1.MonthInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MonthInfoTest {

    @Nested
    class Constructors {

        @Test
        @DisplayName("Default constructor must set today's date")
        void defaultConstructor () {
            LocalDate before = LocalDate.now();
            MonthInfo mi = new MonthInfo();
            LocalDate after = LocalDate.now();
            assertFalse(mi.getDate().isBefore(before));
            assertFalse(mi.getDate().isAfter(after));
        }

        @Test
        void parametrizedConstructor () {
            LocalDate date = LocalDate.of(2025, 11, 22);
            MonthInfo mi = new MonthInfo(date);
            assertEquals(mi.getDate(), date);
        }
    }

    @Nested
    class MonthBasicInfo {
        MonthInfo mi = new MonthInfo(
                LocalDate.of(2025, 11, 22));
        @Test
        void getMonthFullName() {
            assertEquals("ноября", mi.getMonthFullName().toLowerCase());
        }

        @Test
        void getMonthNumber() {
            assertEquals(11, mi.getMonthNumber());
        }
    }

    @Nested
    class MonthCalculations {
        MonthInfo mi = new MonthInfo(LocalDate.of(2025, 8, 22));
        @Test
        void getFirstDayOfWeek() {
            // 1 августа 2025 - пятница
            assertEquals("пт", mi.getFirstDayOfWeekShort());
        }

        @Test
        void getLastDateOfMonth() {
            assertEquals(
                    LocalDate.of(2025, 8, 31),
                    mi.getLastDateOfMonth());
        }

        @Test
        void getDaysInMonth() {
            assertEquals(31, mi.getDaysInMonth());
        }
    }

    @Nested
    class LeapYearMonthCalculations {
        MonthInfo leapYear = new MonthInfo(LocalDate.of(2024, 2, 5));
        MonthInfo notLeapYear = new MonthInfo(LocalDate.of(2025, 2, 5));

        @Test
        @DisplayName("Getting the month last date in a leap year")
        void getLastDateOfMonthLeapYear() {
            assertEquals(29, leapYear.getLastDateOfMonth().getDayOfMonth());
            assertEquals(28, notLeapYear.getLastDateOfMonth().getDayOfMonth());
        }

        @Test
        @DisplayName("Getting number of days in february of a leap year")
        void getDaysInMonthLeapYear() {
            assertEquals(28, notLeapYear.getDaysInMonth());
            assertEquals(29, leapYear.getDaysInMonth());
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"2025-10-01", "2025-11-15", "2025-12-31"})
    void getQuarter(String dateString) {
        MonthInfo mi = new MonthInfo(LocalDate.parse(dateString));
        assertEquals("2025 Q4", mi.getQuarter());
    }
}
