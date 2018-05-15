package netgloo.eventlistener;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import netgloo.Notification;
@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("")
	private String exchange;
	
	@Value("")
	private String routingkey;	
	
	public void send(Notification notification) {
		rabbitTemplate.convertAndSend(exchange, routingkey, notification.getContent());
		System.out.println("Send msg = " + notification.getContent());
	    
	}
}
