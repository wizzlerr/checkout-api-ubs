package checkout.type;

import java.math.BigDecimal;

/**
 * Created by Adam on 2017-07-19.
 */
public class Item {

    private long id;
    private String name;
    private BigDecimal unitPrice;
    private SpecialOffer specialOffer;

    private Item(long id, String name, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
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

    public SpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    private void setSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
    }

    public BigDecimal getDupaPrice(int quantity) {
        if(specialOffer != null) {
            int specialOfferQuantity = specialOffer.getQuantity();
            int specialOfferOccurence = quantity / specialOfferQuantity;
            int notSpecialOccurences = (quantity - specialOfferOccurence) * specialOfferQuantity;

            return quantity > specialOfferQuantity ? calculatePriceWithSpecialOffer(specialOfferOccurence, notSpecialOccurences) : calculate(quantity);
        }

        return calculate(quantity);
    }

    private BigDecimal calculate(int quantity) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    private BigDecimal calculatePriceWithSpecialOffer(int specialOfferOccurence, int notSpecialOccurences) {
        return specialOffer.getPrice().multiply(BigDecimal.valueOf(specialOfferOccurence)).add(BigDecimal.valueOf(notSpecialOccurences).multiply(unitPrice));
    }

    public static final class ItemBuilder {
        private long id;
        private String name;
        private BigDecimal unitPrice;
        private SpecialOffer specialOffer;

        private ItemBuilder(long id, String name, BigDecimal unitPrice) {
            this.id = id;
            this.name = name;
            this.unitPrice = unitPrice;
        }

        public static ItemBuilder anItem(long id, String name, BigDecimal unitPrice) {
            return new ItemBuilder(id, name, unitPrice);
        }

        public ItemBuilder withSpecialOffer(SpecialOffer specialOffer) {
            this.specialOffer = specialOffer;
            return this;
        }

        public Item build() {
            Item item = new Item(id, name, unitPrice);
            item.setSpecialOffer(specialOffer);
            return item;
        }
    }
}