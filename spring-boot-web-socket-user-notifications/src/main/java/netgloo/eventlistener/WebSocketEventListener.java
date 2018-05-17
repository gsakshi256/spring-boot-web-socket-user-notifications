package netgloo.eventlistener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	private List<String> connectedClientId = new ArrayList<String>();
	public List<String> UserList = new ArrayList<String>();

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		logger.info(headerAccessor.getUser().getName());
		String sessionId = headerAccessor.getSessionId();
		String Username = headerAccessor.getUser().getName();
		UserList.add(Username);
		connectedClientId.add(sessionId);
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		logger.info("web socket Disconnect ");
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = headerAccessor.getSessionId();
		String Username = headerAccessor.getUser().getName();
		UserList.remove(Username);
		connectedClientId.remove(sessionId);

	}
}
