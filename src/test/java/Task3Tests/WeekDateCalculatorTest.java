package Task3Tests;

import org.example.Task3.WeekDateCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeekDateCalculatorTest {
    @ParameterizedTest
    @CsvSource({
            "2023, 2023-01-02", // 1.01.2023 - воскресенье
            "2024, 2024-01-01", // 1.01.2024 - понедельник
            "2015, 2015-01-05" // 1.01.2015 - четверг
    })
    void getFirstMonday(int year, String expectedDate) {
        LocalDate expectedMondayDate = LocalDate.parse(expectedDate);
        assertEquals(
                expectedMondayDate,
                WeekDateCalculator.getFirstMonday(year));
    }

    @ParameterizedTest
    @CsvSource({
            "2025, 2025-12-29",
            "2019, 2019-12-30"
    })
    void getLastMonday(int year, String expectedDate) {
        LocalDate expectedMondayDate = LocalDate.parse(expectedDate);
        assertEquals(
                expectedMondayDate,
                WeekDateCalculator.getLastMonday(year));
    }

    @Test
    void getMonday() {
        LocalDate expected = LocalDate.parse("2025-11-17");
        assertEquals(
                expected,
                WeekDateCalculator.getMonday(2025, 46));
    }

    @Test
    void getSunday() {
        LocalDate expected = LocalDate.parse("2025-11-23");
        assertEquals(
                expected,
                WeekDateCalculator.getSunday(2025, 46));
    }

    @ParameterizedTest
    @CsvSource({
            "2025, 52",
            "2024, 53"
    })
    void getMaxWeeks(int year, int expectedWeeksNumber) {
        assertEquals(
                expectedWeeksNumber,
                WeekDateCalculator.getMaxIsoWeeks(year));
    }
}
