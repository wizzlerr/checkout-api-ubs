package checkout.service;


import checkout.type.Cart;
import checkout.type.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Adam on 2017-07-19.
 */
@Service
public class CheckoutService implements CheckoutAPI {

    @Autowired
    private Cart cart;

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void addItem(Item item, int quantity) {

    }

    @Override
    public Integer removeItem(Item item) {
        return null;
    }

    @Override
    public void editItem(Item item, int quantity) {

    }

    @Override
    public void createCart() {

    }

    @Override
    public void removeCart() {

    }

    @Override
    public List<Item> getCartContents() {
        return null;
    }

    @Override
    public BigDecimal calculateValue() {
        return null;
    }
}
