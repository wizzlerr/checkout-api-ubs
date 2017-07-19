package checkout.type;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Adam on 2017-07-19.
 */
@Component
@Scope("session")
public class Cart {

    private long id;
    private Map<Item, Integer> items;

    public long getId() {
        return id;
    }

    public void createCart(long id) {
        this.id = id;
        items = new LinkedHashMap<>();
    }

    public void removeCart() {
        items = null;
    }

    public BigDecimal calculateValue() {
        BigDecimal sum = BigDecimal.ZERO;
        items.forEach((item, quantity) -> sum.add(item.getDupaPrice(quantity)));

        return sum;
    }

    public void addOrEditItem(Item item, int quantity) {
        items.put(item, items.computeIfPresent(item, (K,V) -> V + quantity));
    }

    public Integer removeItem(Item item) {
        return items.remove(item);
    }

    public Map<Item, Integer> getCartContents() {
        return items;
    }
}
