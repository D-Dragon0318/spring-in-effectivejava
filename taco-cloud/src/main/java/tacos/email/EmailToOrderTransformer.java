package tacos.email;

import java.io.IOException;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<EmailOrder> {

	private static final String SUBJECT_KEYWORDS = "TACO ORDER";

	@Override
	protected AbstractIntegrationMessageBuilder<EmailOrder> doTransform(Message mailMessage) {
		EmailOrder tacoOrder = processPayload(mailMessage);
		return MessageBuilder.withPayload(tacoOrder);
	}

	private EmailOrder processPayload(Message mailMessage) {
		try {
			String subject = mailMessage.getSubject();
			if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
				String email = ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
				String content = mailMessage.getContent().toString();
				return parseEmailToOrder(email, content);
			}
		} catch (MessagingException e) {
			log.error("MessagingException: {}", e);
		} catch (IOException e) {
			log.error("IOException: {}", e);
		}
		return null;
	}

	private EmailOrder parseEmailToOrder(String email, String content) {
		EmailOrder order = new EmailOrder(email);
		String[] lines = content.split("\\r?\\n");
		for (String line : lines) {
			if (line.trim().length() > 0 && line.contains(":")) {
				String[] lineSplit = line.split(":");
				String tacoName = lineSplit[0].trim();
				Taco taco = new Taco(tacoName);
				order.addTaco(taco);
			}
		}
		return order;
	}
}
