package netgloo.eventlistener;

import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
	Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "back up")
    public void processQueue1(String message) {
        logger.info("Received from queue 1: " + message);
    }
}
