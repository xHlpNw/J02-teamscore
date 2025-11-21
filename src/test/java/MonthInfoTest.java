import org.example.MonthInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
