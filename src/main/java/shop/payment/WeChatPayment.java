package shop.payment;

import java.math.BigDecimal;

public class WeChatPayment implements IPayment {

    private BigDecimal money;

    private String wechatAccount;

    private String mobile;

    private String captcha;

    public void pay() {
        System.out.println(String.format("Wechat %s paid RMB %s", getWechatAccount(), getMoney().toString()));
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
