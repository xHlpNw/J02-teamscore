package Task3Tests;

import org.example.Task3.WeekDateCalculator;
import org.example.Task3.WeekInputHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class WeekInputHandlerTest {
    @ParameterizedTest
    @ValueSource(ints = { 0, 10000 })
    void validateIncorrectYear(int year) {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> WeekInputHandler.validate(year, 1));
        assertEquals("Год должен быть в диапазоне 1-9999", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "2025, 53",
            "2025, 0",
            "2024, 54",
            "2023, 100"
    })
    void validateIncorrectWeek(int year, int week) {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> WeekInputHandler.validate(year, week));
        String message = String.format(
                "В %d году может быть от 1 до %d недель",
                year,
                WeekDateCalculator.getMaxIsoWeeks(year));
        assertEquals(message, ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = { 53, 1, 26 })
    void validateCorrectWeek(int week) {
        assertDoesNotThrow(() -> WeekInputHandler.validate(2024, week));
    }

    @Test
    void incorrectInputWeekRange() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> WeekInputHandler.getWeekRange(2025, 60)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "2024, 1, 2024-01-01 - 2024-01-07", // 1 января 2024 - понедельник
            "2025, 1, 2025-01-06 - 2025-01-12", // 1 января 2024 - среда
            "2025, 47, 2025-11-24 - 2025-11-30",
            "2024, 53, 2024-12-30 - 2025-01-05"
    })
    void correctWeekRange(int year, int week, String expected) {
        assertEquals(
                expected,
                WeekInputHandler.getWeekRange(year, week));
    }
}
