package localhostchat.message;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// this is a singleton class
// and store in-memory messages
public class MessageStore {
	private List<Message> messageStore = new ArrayList<>();
	private static MessageStore singleObject;

	public static MessageStore getObject() {
		if (singleObject == null)
			singleObject = new MessageStore();
		return singleObject;
	}

	// each method returns this instance to allow chaining
	public MessageStore add(Message msg) {
		messageStore.add(msg);
		if (messageStore.size() > 9) {
			messageStore.remove(0);
		}
		return this;
	}

	@SuppressWarnings("finally")
	public MessageStore remove(int idx) {
		try {
			messageStore.remove(idx);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Message with specified index not found or maybe already deleted !");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return this;
		}
	}

	public MessageStore removeLast() {
		return remove(messageStore.size() - 1);
	}

	public MessageStore removeAll() {
		messageStore.clear();
		return this;
	}

	public Message getMessage(int idx) {
		return messageStore.get(idx);
	}

	public String getMessagesInString() {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper.writeValueAsString(messageStore);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "[ sorry! ]";
		}
	}
	
	public int size() {
		return messageStore.size();
	}
}
