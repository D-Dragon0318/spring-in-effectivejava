package tacos.messaging.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.UserVO;

@Component
@Slf4j
public class UserMessageListener {

	@RabbitListener(queues = "user")
	public void receiveUser(UserVO user) {
		log.info("监听消息，Receive user: {}", user);
	}

	@KafkaListener(topics = "quickstart-events")
	public void handle(UserVO user) {
		log.info("监听消息，Receive user: {}", user);
	}

}