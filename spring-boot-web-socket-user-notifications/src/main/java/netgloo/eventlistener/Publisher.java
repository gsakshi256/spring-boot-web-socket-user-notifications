package netgloo.eventlistener;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

@Component
public class Publisher {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@Value("${jsa.rabbitmq.queue}")
	private String queue;

	public void produceMsg(String msg) throws IOException, Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		// System.out.println("before connection " );
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// System.out.println("before channel " );
		channel.basicPublish(exchange, "", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
		// Template.convertAndSend(exchange, "", msg);
		System.out.println("Send msg = " + msg);

	}

}
