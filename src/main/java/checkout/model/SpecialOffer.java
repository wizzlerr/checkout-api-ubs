package checkout.model;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Adam on 2017-07-19.
 */
public class SpecialOffer {

    private final int quantity;
    private final BigDecimal price;

    SpecialOffer(int quantity, BigDecimal price) {
        checkArgument(quantity > 0, "Quantity must be greater than 0, %s given.", quantity);
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
