package checkout;

import checkout.model.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static checkout.model.Item.withoutSpecialOffer;

/**
 * Created by Adam on 2017-07-19.
 */
@Component
class ItemDao {

    //Dummy implementation of IteamDao.getById
    public Item getById(long id) {
        return withoutSpecialOffer(1L, "Bread", BigDecimal.TEN);
    }

}
