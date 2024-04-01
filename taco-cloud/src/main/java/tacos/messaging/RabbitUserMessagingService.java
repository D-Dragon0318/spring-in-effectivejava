package tacos.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.UserVO;

@Service
public class RabbitUserMessagingService implements UserMessagingService {
	
	@Autowired
	private RabbitTemplate rabbit;

	public void sendUser(UserVO user) {
		MessageConverter converter = rabbit.getMessageConverter();
	    MessageProperties props = new MessageProperties();
	    Message message = converter.toMessage(user, props);
	    rabbit.send("user", message);
	}

}
