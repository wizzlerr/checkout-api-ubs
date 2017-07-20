package checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * Created by Adam on 2017-07-19.
 */
@Service
public class ScanService {

    private final ItemDao itemDao;

    @Autowired
    public ScanService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public BigDecimal scan(long id) {
        return itemDao.getById(id).getUnitPrice();
    }

}
