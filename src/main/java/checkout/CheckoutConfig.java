package checkout;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Adam on 2017-07-20.
 */
@Configuration
@ComponentScan(basePackageClasses = CheckoutService.class)
public class CheckoutConfig {
}
