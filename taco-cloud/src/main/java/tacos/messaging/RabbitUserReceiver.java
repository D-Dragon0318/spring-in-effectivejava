package tacos.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tacos.UserVO;

@Component
public class RabbitUserReceiver {
	
	private RabbitTemplate rabbit;
	private MessageConverter converter;

	@Autowired
	public RabbitUserReceiver(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
		this.converter = rabbit.getMessageConverter();
	}

	public UserVO receiveUser() {
		return (UserVO) rabbit.receiveAndConvert("user");
	}
}
