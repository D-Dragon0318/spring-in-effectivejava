package tacos;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class CommonConfig {

//	@Min(value = 5, message = "must be between 5 and 25")
//	@Max(value = 25, message = "must be between 5 and 25")
	private int pageSize = 20;
	
	@Bean
	public RestTemplate restTemplate() {
	  return new RestTemplate();
	}
	
	/**
	 * 声明队列
	 * 
	 * @return
	 */
	@Bean
	public Queue hello() {
		return new Queue("user");
	}
	
	/**
	 * 为rabbitMQ配置基于JSON的转换器
	 * @return
	 */
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
	  return new Jackson2JsonMessageConverter();
	}

}