package checkout.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static checkout.model.Item.withSpecialOffer;
import static checkout.model.Item.withoutSpecialOffer;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Adam on 2017-07-20.
 */
public class CartTest {

    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void shouldReturnZeroForEmptyCartContent() throws Exception {
        assertThat(cart.calculateValue()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void shouldReturnItemValueForOneItem() throws Exception {
        Item item = withoutSpecialOffer(1L, "Bread", BigDecimal.ONE);
        cart.addItemNTimes(item, 1);

        assertThat(cart.calculateValue()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void shouldReturnItemsValueForItemsWithoutSpecialOffer() throws Exception {
        Item firstItem = withoutSpecialOffer(1L, "Bread", BigDecimal.ONE);
        Item secondItem = withoutSpecialOffer(2L, "Tomato", BigDecimal.TEN);

        cart.addItemNTimes(firstItem, 1);
        cart.addItemNTimes(secondItem, 1);

        assertThat(cart.calculateValue()).isEqualTo(BigDecimal.ONE.add(BigDecimal.TEN));
    }

    @Test
    public void shouldReturnItemValueForItemWithSpecialOffer() throws Exception {
        Item item = withSpecialOffer(2L, "Tomato", BigDecimal.valueOf(20),
                new SpecialOffer(1, BigDecimal.TEN));

        cart.addItemNTimes(item, 1);

        assertThat(cart.calculateValue()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void shouldReturnItemValueForItemWithSpecialOffer2() throws Exception {
        Item item = withSpecialOffer(2L, "Tomato", BigDecimal.valueOf(20),
                new SpecialOffer(3, BigDecimal.TEN));

        cart.addItemNTimes(item, 2);

        assertThat(cart.calculateValue()).isEqualTo(BigDecimal.valueOf(40));
    }

    @Test
    public void shouldReturnItemValueForItemWithSpecialOffer3() throws Exception {
        Item item = withSpecialOffer(2L, "Tomato", BigDecimal.valueOf(20),
                new SpecialOffer(3, BigDecimal.TEN));

        cart.addItemNTimes(item, 4);

        assertThat(cart.calculateValue()).isEqualTo(BigDecimal.valueOf(20).add(BigDecimal.TEN));
    }

    @Test
    public void addingOneItemResultsInOneElementCart() throws Exception {
        Item firstItem = Mockito.mock(Item.class);

        cart.addItemNTimes(firstItem, 1);

        assertThat(cart.getCartContents().size()).isEqualTo(1);
    }

    @Test
    public void addingTwoItemsResultsInTwoElementCart() throws Exception {
        Item firstItem = Mockito.mock(Item.class);
        Item secondItem = Mockito.mock(Item.class);

        cart.addItemNTimes(firstItem, 1);
        cart.addItemNTimes(secondItem, 1);

        assertThat(cart.getCartContents().size()).isEqualTo(2);
    }

    @Test
    public void addingOneItemInQuantityOf13ResultsInCartWithOneItemOfQuantity13() throws Exception {
        Item firstItem = Mockito.mock(Item.class);

        cart.addItemNTimes(firstItem, 13);
        assertThat(cart.getCartContents().get(firstItem)).isEqualTo(13);
    }

    @Test
    public void addingOneItemInQuantityOf14ResultsInCartWithOneItemOfQuantity14() throws Exception {
        Item firstItem = Mockito.mock(Item.class);

        cart.addItemNTimes(firstItem, 13);
        cart.addItemNTimes(firstItem, 1);

        assertThat(cart.getCartContents().get(firstItem)).isEqualTo(14);
    }
}