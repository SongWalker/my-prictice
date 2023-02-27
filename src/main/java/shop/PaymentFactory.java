package shop;

import shop.payment.AliPayment;
import shop.payment.CreditCarPayment;
import shop.payment.IPayment;
import shop.payment.WeChatPayment;

public class PaymentFactory {

    public static IPayment createPayment(PayType payType, String accoount, String password) throws Exception{
        switch (payType) {
            case Alipay:
                AliPayment aliPayment = new AliPayment();
                aliPayment.setEmail(accoount);
                aliPayment.setPassword(password);
                return aliPayment;

            case Credit:
                CreditCarPayment creditCarPayment = new CreditCarPayment();
                creditCarPayment.setBankAccount(accoount);
                creditCarPayment.setPassword(password);
                return creditCarPayment;

            case WeChat:
                WeChatPayment weChatPayment = new WeChatPayment();
                weChatPayment.setWechatAccount(accoount);
                weChatPayment.setCaptcha(password);
                return weChatPayment;

                default:
                    throw new Exception("not supported payment type");
        }
    }
}
