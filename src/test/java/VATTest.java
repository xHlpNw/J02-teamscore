import org.example.VAT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VATTest {

    @Nested
    class Constructors {

        @Test
        void priceWithVATInput() {
            VAT vat = new VAT(new BigDecimal("500000.00"), true);
            assertEquals(
                    new BigDecimal("416666.67"),
                    vat.getPriceWithoutVAT());
        }

        @Test
        void priceWithoutVATInput() {
            BigDecimal price = new BigDecimal("416666.67");
            VAT vat = new VAT(price, false);
            assertEquals(price, vat.getPriceWithoutVAT());
        }
    }

    @Nested
    @DisplayName("Negative price throws exception")
    class NegativePrice {
        @Test
        void negativePriceWithoutVATInput() {
            BigDecimal price = new BigDecimal("-1.00");
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> new VAT(price, false));

            assertEquals("Цена не может быть отрицательной", ex.getMessage());
        }

        @Test
        void negativePriceWithVATInput() {
            BigDecimal price = new BigDecimal("-0.01");
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> new VAT(price, true));

            assertEquals("Цена не может быть отрицательной", ex.getMessage());
        }
    }

    @ParameterizedTest
    @DisplayName("Calculate price with VAT")
    @CsvSource({
            "99.99, 119.99",
            "152.50, 183.00",
            "0.01, 0.01"
    })
    void calculatePriceWithVAT(String withoutVAT, String withVAT) {
        VAT vat = new VAT(new BigDecimal(withoutVAT), false);
        assertEquals(new BigDecimal(withVAT), vat.getPriceWithVAT());
    }

    @Nested
    @DisplayName("Rounding VAT values")
    class Rounding {

        @ParameterizedTest
        @DisplayName("Rounding for receipt")
        @CsvSource({
                "0.01, 0.00",
                "0.03, 0.01" // 0.03 * 0.2 = 0.006 -> 0.01
        })
        void receiptVATRounding(String priceWithoutVAT, String expectedVAT) {
            VAT vat = new VAT(new BigDecimal(priceWithoutVAT), false);
            assertEquals(new BigDecimal(expectedVAT), vat.getVARForReceipt());
        }

        @ParameterizedTest
        @DisplayName("Rounding for declaration")
        @CsvSource({
                "0.05, 0", // 0.05 * 0.2 = 0.001 -> 0
                "4.95, 1", // 4.95 * 0.2 = 0.99 -> 1
                "2.50, 1", // 2.50 * 0.2 = 0.50 -> 1
                "2.49, 0" // 2.49 * 0.2 -> 0.498 -> 0
        })
        void declarationVATRounding(String priceWithoutVAT, String expectedVAT) {
            VAT vat = new VAT(new BigDecimal(priceWithoutVAT), false);
            assertEquals(new BigDecimal(expectedVAT), vat.getVARForDeclaration());
        }
    }
}
