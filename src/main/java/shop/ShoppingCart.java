package shop;

import shop.payment.IPayment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ShoppingCart {

    private static volatile ShoppingCart instance = null;

    private ReentrantLock lock = new ReentrantLock();

    private List<Product> products = new ArrayList<Product>();

    public static ShoppingCart getInstance() {
        if(instance == null) {
            synchronized (ShoppingCart.class) {
                if(instance == null) {
                    instance = new ShoppingCart();
                }
            }
        }
        return instance;
    }

    private ShoppingCart() {

    }

    public void add(Product product) throws Exception {
        try
        {
            if(lock.tryLock(10, TimeUnit.SECONDS)) {
                products.add(product);
            }
        }
        catch (InterruptedException e) {
            throw new Exception("add products timeout");
        }
        finally {
            lock.unlock();
        }
    }

    public BigDecimal cal() throws Exception {
        try
        {
            if(lock.tryLock(10, TimeUnit.SECONDS)) {
                if(products.size() > 0) {
                    BigDecimal total = new BigDecimal(0);
                    for(Product product:products) {
                        total = total.add(product.getPrice());
                    }
                    return total;
                }
            }
        }
        catch (InterruptedException e) {
            throw new Exception("cal products timeout");
        }
        finally {
            lock.unlock();
        }
        return new BigDecimal(0);
    }

    public void pay(PayType payType, String accoount, String password) throws Exception {
        try
        {
            if(lock.tryLock(10, TimeUnit.SECONDS)) {
                IPayment payment = PaymentFactory.createPayment(payType, accoount, password);

                payment.setMoney(cal());
                payment.pay();
            }
        }
        catch (InterruptedException e) {
            throw new Exception("payment timeout");
        }
        finally {
            lock.unlock();
        }
    }
}
