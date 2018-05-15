package netgloo.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import netgloo.Notification;

@RestController
@RequestMapping(value = "/backup-rabbitmq/")
public class RabbitMQWebController {
  
	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(Notification notification) {
	
		rabbitMQSender.send(notification);

		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
}
