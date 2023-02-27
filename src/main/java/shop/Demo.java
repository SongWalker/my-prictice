package shop;

import java.math.BigDecimal;

public class Demo {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        String account = "wechatUser";
        String captcha = "123456";
        Product product1 = new Product("商品1", new BigDecimal(10));
        Product product2 = new Product("商品2", new BigDecimal(12.5));
        try {
            shoppingCart.add(product1);
            shoppingCart.add(product2);
            shoppingCart.pay(PayType.WeChat, account, captcha);
        }
        catch (Exception e) {

        }
    }
}
