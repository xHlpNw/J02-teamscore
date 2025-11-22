package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Налог на добавленную стоимость (англ. VAT) входит в цену каждого товара.
 * В настоящее время его базовая ставка составляет 20%.
 * Полная цена товара – это сумма без НДС + НДС (120%).
 * В исходных документах суммы округляются до копеек,
 * а в налоговой декларации – до рублей. Сумма менее 50 коп. отбрасывается,
 * а 50 коп. и более округляется до полного рубля.
 */
public class VAT {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.20");
    private final BigDecimal priceWithoutVAT;

    /**
     * @param price - product price
     * @param withVAT - flag: true - with VAT, false - without VAT
     */
    public VAT(BigDecimal price, boolean withVAT) {
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        if (withVAT) {
            BigDecimal coefficient = BigDecimal.ONE.add(VAT_RATE);
            priceWithoutVAT = price.divide(coefficient, 2, RoundingMode.HALF_UP);
        } else {
            priceWithoutVAT = price;
        }
    }

    /**
     * Стоимость товара без НДС
     */
    public BigDecimal getPriceWithoutVAT() {
        return this.priceWithoutVAT;
    }

    /**
     * Стоимость товара с НДС
     */
    public BigDecimal getPriceWithVAT() {
        BigDecimal coefficient = BigDecimal.ONE.add(VAT_RATE);
        return priceWithoutVAT.multiply(coefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * сумма НДС в чеке
     */
    public BigDecimal getVARForReceipt() {
        return priceWithoutVAT.multiply(VAT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Сумма НДС для налоговой декларации
     */
    public BigDecimal getVARForDeclaration() {
        return priceWithoutVAT.multiply(VAT_RATE)
                .setScale(0, RoundingMode.HALF_UP);
    }
}
