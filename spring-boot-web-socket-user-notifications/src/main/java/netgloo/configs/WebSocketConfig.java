package netgloo.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Enable and configure Stomp over WebSocket.
 */
@SuppressWarnings("deprecation")
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
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
      
      registry.setApplicationDestinationPrefixes("/app");
      System.out.println("websocket config");
       
      //   Use this for enabling a Full featured broker like RabbitMQ
      registry.enableStompBrokerRelay("/queue/notify")
      .setRelayHost("localhost")
      .setRelayPort(61613)
      .setClientLogin("guest")
      .setClientPasscode("guest");
      
      
  }

} // class WebSocketConfig
