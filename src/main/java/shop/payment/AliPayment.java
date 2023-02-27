package shop.payment;

import java.math.BigDecimal;

public class AliPayment implements IPayment {

    private BigDecimal money;

    private String email;

    private String password;

    public void pay() {
        System.out.println(String.format("email %s paid RMB %d", getEmail(), getMoney()));
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
