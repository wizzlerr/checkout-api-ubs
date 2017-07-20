package checkout;


import checkout.model.Cart;
import checkout.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Adam on 2017-07-19.
 */
@Service
public class CheckoutService {

    private final ApplicationContext applicationContext;

    @Autowired
    public CheckoutService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void addItem(Item item) {
        applicationContext.getBean(Cart.class).addItemNTimes(item, 1);
    }

    public void addItem(Item item, int quantity) {
        applicationContext.getBean(Cart.class).addItemNTimes(item, quantity);
    }

    public Integer removeItem(Item item) {
        return applicationContext.getBean(Cart.class).removeItem(item);
    }

    public void editItem(Item item, int quantity) {
        applicationContext.getBean(Cart.class).editItem(item, quantity);
    }

    public void emptyCart() {
        applicationContext.getBean(Cart.class).empty();
    }

    public Map<Item, Integer> getCartContents() {
        return applicationContext.getBean(Cart.class).getCartContents();
    }

    public BigDecimal checkout() {
        return applicationContext.getBean(Cart.class).calculateValue();
    }
}
