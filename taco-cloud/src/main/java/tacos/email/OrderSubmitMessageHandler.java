package tacos.email;

import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {

	@Override
	public Object handle(EmailOrder order, MessageHeaders headers) {
		// TODO: 对订单进行自定义处理，这里仅做打印显示
		log.info("Processing user: {}", order);
		return null;
	}
}
