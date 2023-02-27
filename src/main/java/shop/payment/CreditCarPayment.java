package shop.payment;

import java.math.BigDecimal;
import java.util.Date;

public class CreditCarPayment implements IPayment {

    private BigDecimal money;

    private String bankAccount;

    private String password;

    private Date invalidDate;

    public void pay() {
        System.out.println(String.format("email %s paid RMB %d", getBankAccount(), getMoney()));
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }
}
