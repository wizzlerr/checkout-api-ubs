package checkout.model;

import java.math.BigDecimal;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.math.BigDecimal.valueOf;
import static java.util.Optional.ofNullable;

/**
 * Created by Adam on 2017-07-19.
 */
public class Item {

    private long id;
    private String name;
    private BigDecimal unitPrice;
    private SpecialOffer specialOffer;

    private Item(long id, String name, BigDecimal unitPrice, SpecialOffer specialOffer) {
        this.id = id;
        this.name = checkNotNull(name, "Name is null");
        this.unitPrice = checkNotNull(unitPrice, "UnitPrice is null");
        this.specialOffer = specialOffer;
    }

    public static Item withoutSpecialOffer(long id, String name, BigDecimal unitPrice) {
        return new Item(id, name, unitPrice, null);
    }

    public static Item withSpecialOffer(long id, String name, BigDecimal unitPrice, SpecialOffer specialOffer) {
        return new Item(id, name, unitPrice, specialOffer);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Optional<SpecialOffer> getSpecialOffer() {
        return ofNullable(specialOffer);
    }

    /**
     * Calculates total price of items including special offer.
     *
     * @param quantity Count of this item in cart
     * @return total price of item
     */
    BigDecimal getTotalPrice(int quantity) {
        if(specialOffer != null) {
            int specialOfferQuantity = specialOffer.getQuantity();
            int specialOfferCount = quantity / specialOfferQuantity;
            int regularOfferCount = quantity - specialOfferCount * specialOfferQuantity;

            return quantity >= specialOfferQuantity ? calculatePriceWithSpecialOffer(specialOfferCount, regularOfferCount) : calculate(quantity);
        }

        return calculate(quantity);
    }

    private BigDecimal calculate(int quantity) {
        return unitPrice.multiply(valueOf(quantity));
    }

    private BigDecimal calculatePriceWithSpecialOffer(int specialOfferCount, int regularOfferCount) {
        return specialOffer.getPrice().multiply(valueOf(specialOfferCount)).add(valueOf(regularOfferCount).multiply(unitPrice));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}