package localhostchat.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// this is a singleton class
// and store in-memory messages
public class MessageStore {
	private List<Message> messageStore = new ArrayList<>();
	private List<Message> allMessages = Message.getAllMessages();
	private static MessageStore singleObject;

	public static MessageStore getObject() {
		if (singleObject == null)
			singleObject = new MessageStore();
		return singleObject;
	}
	
	
	// constructive methods

	// each method returns this instance to allow chaining
	public MessageStore add(Message msg) {
		messageStore.add(msg);
		if (messageStore.size() > 9) {
			messageStore.remove(0);
		}
		return this;
	}

	// destructive methods
	
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
	
	public MessageStore remove(Message msg) {
		messageStore.remove(msg);
		return this;
	}

	public MessageStore removeAll() {
		messageStore.clear();
		return this;
	}

	// returning methods
	
	public Optional<Message> getMessage(int id) {
		return messageStore.stream().filter(msg -> msg.getId() == id).findFirst();
	}
	
	public Message getLastMessage() {
		return messageStore.get(messageStore.size() - 1);
	}
	
	public Optional<Message> getLastMessage(String ip) {
		
		for (int i = messageStore.size() - 1; i >= 0; ++i) {
			if (messageStore.get(i).getIp().equals(ip)) {
				return Optional.of(messageStore.get(i));
			}
		}
			
		return Optional.empty();
	}
	
	public Optional<Message> getLastMessageReply(String ip) {
		
		for (int i = allMessages.size() - 1; i >= 0; ++i) {
			if (allMessages.get(i).getIp().equals(ip)) {
				return Optional.of(allMessages.get(i));
			}
		}
			
		return Optional.empty();
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
	
	public List<Message> getFilteredMessageList(Predicate<Message> predicate) {
		return messageStore.stream().filter(predicate).toList(); 
	}
	
	// standard required methods
	
	public int size() {
		return messageStore.size();
	}
}
