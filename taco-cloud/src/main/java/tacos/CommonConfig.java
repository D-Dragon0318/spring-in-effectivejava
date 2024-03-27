package tacos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class CommonConfig {

	private int pageSize = 20;

}