package demo.indicator

import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health

/**
 * Created by domix on 08/08/15.
 */
class MyHealthIndicator extends AbstractHealthIndicator {
  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    builder.up().withDetail('chido', 'es sabado!!!')

  }
}
