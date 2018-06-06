package netgloo.eventlistener;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.MessageProperties;

@Component
public class Publisher {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@Value("${jsa.rabbitmq.queue}")
	private String queue;
 	private boolean B_ACK = false;
    public ArrayList<String> ar = new ArrayList<String>();
	public void produceMsg(String msg) throws IOException, Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.basicPublish(exchange, "", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
		GetResponse response = channel.basicGet(queue, B_ACK);
		if (response != null) {
			String body = new String(response.getBody());
			// do whatever you want to do here
			System.out.println("body contain "+body);
		}
		// Template.convertAndSend(exchange, "", msg);
		System.out.println("Send msg = " + msg);
		ar.add(msg);
	}

}
