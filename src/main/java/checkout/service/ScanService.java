package checkout.service;

import checkout.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Adam on 2017-07-19.
 */
@Service
@Scope("session")
public class ScanService {

    //autowire jakiśtam DAO z jedno metodo getById i zamockowac w testach
    @Autowired
    private ItemDAO itemDAO;

    public BigDecimal scan(long id) {
        return itemDAO.getById(id).getUnitPrice();
    }

}
