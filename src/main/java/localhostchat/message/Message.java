package localhostchat.message;

import java.time.LocalDateTime;

public class Message {
	private static int autoId;
	private int id;
	private String ip;
	private String message;
	private LocalDateTime timestamp;

	public Message() {
		id = ++autoId;
		this.timestamp = LocalDateTime.now();
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

	@Override
	public String toString() {
		return "Message [id=" + id + ", ip=" + ip + ", message=" + message + ", timestamp=" + timestamp + "]";
	}

}
