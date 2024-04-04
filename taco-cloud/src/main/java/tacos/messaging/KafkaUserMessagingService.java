package tacos.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tacos.UserVO;

@Service
public class KafkaUserMessagingService implements UserMessagingService {
	
	@Autowired
	private KafkaTemplate<String, UserVO> kafkaTemplate;

	public void sendUser(UserVO user) {
	    kafkaTemplate.send("quickstart-events", user);
	}

}
