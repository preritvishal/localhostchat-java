package localhostchat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatsocket")
public class ChatWebSocket {

	private static Set<Session> connectedClients = new HashSet<>();

	@OnOpen
	public void onOpen(Session session) {
		connectedClients.add(session);
		System.out.println("Client connected: " + session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		connectedClients.remove(session);
		System.out.println("Client disconnected: " + session.getId());
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.err.println("WebSocket error: " + throwable.getMessage());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from client: " + message);
		// Process the message content here
		// Optionally, send a response message back to the client
	}

	public void sendMessage(String message, Session sender) {
		for (Session client : connectedClients) {
			if (!client.equals(sender)) { // Avoid sending to the sender itself
				try {
					client.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
