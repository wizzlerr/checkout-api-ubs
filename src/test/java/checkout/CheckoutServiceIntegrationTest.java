package checkout;

import checkout.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


/**
 * Created by Adam on 2017-07-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CheckoutConfig.class)
@WebAppConfiguration
public class CheckoutServiceIntegrationTest {

    @Autowired
    private CheckoutService checkoutService;

    @Test
    public void initiallyCartHasZeroItems() throws Exception {
        assertTrue(checkoutService.getCartContents().isEmpty());
    }

    @Test
    public void addItemTest() throws Exception {
        Item item = mock(Item.class);

        checkoutService.addItem(item);

        assertThat(checkoutService.getCartContents().size()).isEqualTo(1);
    }

    @Test
    public void addItemInQuanityOf2() throws Exception {
        Item item = mock(Item.class);

        checkoutService.addItem(item, 2);

        assertThat(checkoutService.getCartContents().get(item)).isEqualTo(2);
    }

    @Test
    public void removeItem() throws Exception {
        Item item = mock(Item.class);

        checkoutService.addItem(item, 1);
        checkoutService.removeItem(item);

        assertTrue(checkoutService.getCartContents().isEmpty());
    }

    @Test
    public void editItem() throws Exception {
        Item item = mock(Item.class);

        checkoutService.addItem(item, 1);
        checkoutService.editItem(item, 3);

        assertThat(checkoutService.getCartContents().get(item)).isEqualTo(3);
    }

    @Test
    public void removeCart() throws Exception {
        checkoutService.emptyCart();
        assertTrue(checkoutService.getCartContents().isEmpty());
    }

    @Test
    public void checkout() throws Exception {
        Item item = Item.withoutSpecialOffer(1L, "Bread", BigDecimal.ONE);

        checkoutService.addItem(item, 3);

        assertThat(checkoutService.checkout()).isEqualTo(BigDecimal.valueOf(3));
    }
}
