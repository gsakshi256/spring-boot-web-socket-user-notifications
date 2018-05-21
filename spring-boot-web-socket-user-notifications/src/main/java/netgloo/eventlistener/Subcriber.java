package netgloo.eventlistener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Component
public class Subcriber {

	@Value("${jsa.rabbitmq.queue}")
	private String queue;

	// @Bean
	// public ConnectionFactory connectionFactory() {
	// ConnectionFactory connectionFactory = new ConnectionFactory();
	// connectionFactory.setHost("localhost");
	// connectionFactory.setUsername("guest");
	// connectionFactory.setPassword("guest");
	// return connectionFactory;
	// }
	//
	// @Bean
	// RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	// RabbitTemplate rabbitTemplate = new RabbitTemplate();
	// rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
	// return rabbitTemplate;
	// }
	//
	// @Bean
	// public AmqpAdmin amqpAdmin() {
	// RabbitAdmin rabbitAdmin = new RabbitAdmin(
	// (org.springframework.amqp.rabbit.connection.ConnectionFactory)
	// connectionFactory());
	// return rabbitAdmin;
	// }
	//
	// @Bean
	// public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
	// return new Jackson2JsonMessageConverter();
	// }
	//
	// @RabbitListener(queues = "${jsa.rabbitmq.queue}")
	// public void recievedMessage(Notification notification) throws IOException,
	// TimeoutException {
	// // channel.basicConsume(queue, true, null);
	// System.out.println("Recieved Message: " + notification.getMessage());
	// }

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@RabbitListener(queues = "${jsa.rabbitmq.queue}")
	public void consumer() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(queue, true, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queue, true, consumer);
	}
}
