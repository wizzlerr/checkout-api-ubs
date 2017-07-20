package checkout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Adam on 2017-07-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CheckoutConfig.class)
@WebAppConfiguration
public class ScanServiceIntegrationTest {

    @Autowired
    private ScanService scanService;

    @Test
    public void scan() throws Exception {
        assertThat(scanService.scan(1L)).isEqualTo(BigDecimal.TEN);
    }
}
