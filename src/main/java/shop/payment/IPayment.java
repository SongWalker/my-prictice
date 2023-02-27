package shop.payment;

import java.math.BigDecimal;

public interface IPayment {

    /**
     * 实现具体的支付
     */
    void pay();

    BigDecimal getMoney();

    void setMoney(BigDecimal money);
}
