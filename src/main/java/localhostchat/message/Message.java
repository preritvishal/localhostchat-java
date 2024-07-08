package localhostchat.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Message {
	private static int autoId;
	private static List<Message> allMessages = new ArrayList<>();
	private int id;
	private String ip;
	private String message;
	private LocalDateTime timestamp;
	private List<Message> replies;

	public Message() {
		id = ++autoId;
		this.timestamp = LocalDateTime.now();
		allMessages.add(this);
	}

	public Message(String ip, String message) {
		this();
		this.ip = ip;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Message> getReplies() {
		return replies;
	}

	public void setReplies(List<Message> replies) {
		this.replies = replies;
	}
	
	public void setReplies(Message reply) {
		if (this.replies == null) {
			replies = new ArrayList<>();
		}
		replies.add(reply);
	}
	
	public static List<Message> getAllMessages() {
		return allMessages;
	}

	public static void setAllMessages(List<Message> allMessages) {
		Message.allMessages = allMessages;
	}


	@Override
	public String toString() {
		return "Message [id=" + id + ", ip=" + ip + ", message=" + message + ", timestamp=" + timestamp + "]";
	}

}
