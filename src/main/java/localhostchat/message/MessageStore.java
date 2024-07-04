package localhostchat.message;

import java.util.ArrayList;
import java.util.List;

// this is a singleton class
// and store in-memory messages
public class MessageStore {
	private List<Message> messageStore = new ArrayList<>();
	private static MessageStore singleObject = new MessageStore();

	public static MessageStore getObject() {
		return singleObject;
	}

	// each method returns this instance to allow chaining
	public MessageStore add(Message msg) {
		messageStore.add(msg);
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

		String finalString = "[";

		for (int i = 0; i < messageStore.size(); ++i) {

			String separator = ",";

			if (i == 0) {
				separator = "";
			}

			finalString += String.format("%s%n%s", separator, messageStore.get(i).toJsonString());

			if (i + 1 == messageStore.size()) {
				finalString += String.format("%n]");
			}
		}
		return finalString;
	}
}
