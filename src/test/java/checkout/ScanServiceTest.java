package checkout;

import checkout.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


/**
 * Created by Adam on 2017-07-20.
 */
public class ScanServiceTest {

    private ItemDao itemDao;

    private ScanService scanService;

    @Before
    public void setUp() throws Exception {
        itemDao = mock(ItemDao.class);
        scanService = new ScanService(itemDao);
    }

    @Test
    public void scanWithSpecialOffer() throws Exception {
        BigDecimal itemUnitPrice = BigDecimal.TEN;
        long itemId = 1L;
        Item item = mock(Item.class);

        when(itemDao.getById(anyLong())).thenReturn(item);
        when(item.getUnitPrice()).thenReturn(itemUnitPrice);

        scanService.scan(itemId);

        verify(itemDao).getById(anyLong());
        verify(item).getUnitPrice();
    }

}