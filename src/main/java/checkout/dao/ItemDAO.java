package checkout.dao;

import checkout.type.Item;

/**
 * Created by Adam on 2017-07-19.
 */
public interface ItemDAO {

    Item getById(long id);

}
