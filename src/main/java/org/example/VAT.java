package org.example;

/*
    Налог на добавленную стоимость (англ. VAT) входит в цену каждого товара.
    В настоящее время его базовая ставка составляет 20%.
    Полная цена товара – это сумма без НДС + НДС (120%).
    Причем в исходных документах суммы округляются до копеек,
    а в налоговой декларации – до рублей. Сумма менее 50 коп. отбрасывается,
    а 50 коп. и более округляется до полного рубля.
    Реализуйте класс VAT, в который можно передать как стоимость товара с НДС,
    так и без НДС, а получить:
    •	полную стоимость с НДС,
    •	стоимость товара без НДС,
    •	сумму НДС в чеке,
    •	сумму НДС для налоговой декларации.
*/

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VAT {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.20");
    private final BigDecimal priceWithoutVAT;

    public VAT(BigDecimal price, boolean withVAT) {
        if (withVAT) {
            BigDecimal coefficient = BigDecimal.ONE.add(VAT_RATE);
            priceWithoutVAT = price.divide(coefficient, 2, RoundingMode.HALF_UP);
        } else {
            priceWithoutVAT = price;
        }
    }
}
