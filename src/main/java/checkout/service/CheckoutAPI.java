package checkout.service;

import checkout.type.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Adam on 2017-07-19.
 */
public interface CheckoutAPI {

    void addItem(Item item);

    void addItem(Item item, int quantity);

    Integer removeItem(Item item);

    void editItem(Item item, int quantity);

    void createCart();

    void removeCart();

    List<Item> getCartContents();

    BigDecimal calculateValue();
}
