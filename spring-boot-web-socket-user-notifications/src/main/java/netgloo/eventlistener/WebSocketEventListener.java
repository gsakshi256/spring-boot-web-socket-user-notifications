package netgloo.eventlistener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import netgloo.Notification;

@Component
public class WebSocketEventListener {
	public int c;
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	public List<String> connectedClientId = new ArrayList<String>();
	private static final Map<String, Queue<Notification>> userMessageBuffer = new HashMap<>();

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		logger.info("connected  sessionId: " + sha.getSessionId());
		String sid = sha.getSessionId();
		if (sid != null) {
			connectedClientId.add(sid);
		}
	}
   

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		logger.info("Disconnect  sessionId: " + sha.getSessionId());
		String sid = sha.getSessionId();
		if (sid != null) {
			connectedClientId.remove(sid);
		}
	}
}
