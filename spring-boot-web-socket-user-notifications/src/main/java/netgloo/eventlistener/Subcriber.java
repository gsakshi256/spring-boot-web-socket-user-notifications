package netgloo.eventlistener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import netgloo.Notification;

@Component
public class Subcriber {
	boolean NOACK = true;

	public static final String queueName = "jsa.queue.2";
	public static final String EXCHANGE_NAME = "jsa.fanout";

	@RabbitListener(queues = "${jsa.rabbitmq.queue}")
	public void recievedMessage(Notification notification) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection con = factory.newConnection();
		Channel channel = con.createChannel();
		GetResponse response = channel.basicGet(queueName, NOACK);
		System.out.println("before response loop ");
		if (response != null) {
			String body = new String(response.getBody());
			// do whatever you want to do here
			System.out.println("respone has something " + body);
		}
	}

}
