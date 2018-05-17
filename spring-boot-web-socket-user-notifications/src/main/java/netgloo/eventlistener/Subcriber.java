package netgloo.eventlistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import netgloo.Notification;

@Component
public class Subcriber {

	@RabbitListener(queues = "${jsa.rabbitmq.queue}")
	public void recievedMessage(Notification notification) {
		
		System.out.println("Recieved Message: " + notification.getContent());
	}

}
