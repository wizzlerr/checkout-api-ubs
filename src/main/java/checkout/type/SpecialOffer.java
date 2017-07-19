package checkout.type;

import java.math.BigDecimal;

/**
 * Created by Adam on 2017-07-19.
 */
public class SpecialOffer {

    private final int quantity;
    private final BigDecimal price;

    public SpecialOffer(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    int getQuantity() {
        return quantity;
    }

    BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SpecialOffer: " +
                quantity + " for " +
                price;
    }
}
