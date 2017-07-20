package checkout.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

/**
 * Created by Adam on 2017-07-19.
 */
@Component
@Scope(SCOPE_SESSION)
public class Cart {

    private long id;
    private Map<Item, Integer> items;

    Cart() {
        this.items = new LinkedHashMap<>();
    }

    public long getId() {
        return id;
    }

    public void empty() {
        items = new LinkedHashMap<>();
    }

    public BigDecimal calculateValue() {
        return items.entrySet().stream()
                .map(entry -> entry.getKey().getTotalPrice(entry.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItemNTimes(Item item, int quantity) {
        items.merge(item, quantity, Integer::sum);
    }

    public void editItem(Item item, int quantity) {
        items.put(item, quantity);
    }

    public Integer removeItem(Item item) {
        return items.remove(item);
    }

    public Map<Item, Integer> getCartContents() {
        return items;
    }
}
