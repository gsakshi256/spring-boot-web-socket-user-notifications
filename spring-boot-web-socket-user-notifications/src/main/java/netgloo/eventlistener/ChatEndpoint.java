package netgloo.eventlistener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import javax.websocket.EncodeException;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import netgloo.Notification;

@ServerEndpoint(value = "/user/queue/notify")
public class ChatEndpoint {
 
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	private static final Map<String, Queue<Notification>> userMessageBuffer = new HashMap<>();
	
	@OnOpen
	public void open(final Session session) {

	    session.getUserProperties().put("user",session.getId());
	    System.out.println("session id "+session.getId());
	    Queue<Notification> userMsgs = userMessageBuffer.get(session.getId());
	    Notification sendChat = new Notification();

	    if (userMsgs != null && !userMsgs.isEmpty()) {

	        for (int i = 0; i < userMsgs.size(); i++) {

	            sendChat = userMsgs.remove();
	            System.out.println("size!!!!!! " + sendChat.getMessage());
	            try {

	                    session.getBasicRemote().sendObject(sendChat);

	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (EncodeException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	        }
	    }
	}
}
