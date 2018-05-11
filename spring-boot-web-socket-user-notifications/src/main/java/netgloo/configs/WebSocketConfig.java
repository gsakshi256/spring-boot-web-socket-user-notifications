package netgloo.configs;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Enable and configure Stomp over WebSocket.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  /**
   * Register Stomp endpoints: the url to open the WebSocket connection.
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    
    // Register the "/ws" endpoint, enabling the SockJS protocol.
    // SockJS is used (both client and server side) to allow alternative 
    // messaging options if WebSocket is not available.
    registry.addEndpoint("/ws").withSockJS();
    
    return;
  }
 
//  /**
//   * Configure the message broker.
//   */
  @Bean(initMethod = "start", destroyMethod = "stop")
  public BrokerService broker() throws Exception {
      final BrokerService broker = new BrokerService();
      broker.addConnector("stomp://localhost:61613");

      broker.setPersistent(false);
      final ManagementContext managementContext = new ManagementContext();
      managementContext.setCreateConnector(true);
      broker.setManagementContext(managementContext);

      return broker;
  }
} // class WebSocketConfig
