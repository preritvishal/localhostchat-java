package localhostchat.message;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl {

	static MessageStore messageStore = MessageStore.getObject();

	public static void workWithIt(String userMessage, String userIP) {

		userMessage = userMessage.strip();

		if (userMessage.charAt(0) == '/') {
			if (userMessage.matches("[/]\\w+(-\\w+)*")) { // matches the simple command with no args
				switch (userMessage) {

				case "/clear", "/cls", "/clean" -> messageStore.removeAll();

				case "/last" -> { // removes the last message sent by the user filtering on ip address
					Optional<Message> lastMessageObject = messageStore.getLastMessage(userIP);

					if (lastMessageObject.isPresent()) {
						messageStore.remove(lastMessageObject.get());
					}
				}

				case "/testing", "/dummy" -> { // generates multiple dummy data objects
					messageStore.removeAll();
					for (int i = 0; i < 9; ++i)
						messageStore.add(new Message(userIP, "dummy message " + i));
				}
				
				case "/rick-roll", "/never-gonna-give-you-up" -> { // Rick rolling people !
					messageStore.removeAll();
					messageStore.add(new Message(userIP, "Never gonna give you up..."));
					messageStore.add(new Message(userIP, "Never gonna let you down..."));
					messageStore.add(new Message(userIP, "Never gonna run around and desert you..."));
					messageStore.add(new Message(userIP, "Never gonna make you cry..."));
					messageStore.add(new Message(userIP, "Never gonna say goodbye..."));
					messageStore.add(new Message(userIP, "Never gonna tell a lie and hurt you..."));
				}
				
				}
			} else if (userMessage.matches("[/]\\w+ \\d+")) { // commands with one arg, type int
				String[] commandString = userMessage.split(" ");
				int value = Integer.parseInt(commandString[1]);
				
				switch (commandString[0]) {

				case "/last" -> { // removes multiple last messages based on ip address
					List<Message> filteredMessageList = messageStore.getFilteredMessageList(msg -> msg.getIp().equals(userIP));
					
					if (value >= filteredMessageList.size()) {
						value = filteredMessageList.size();
					}
					
					for (int i = 0; i < value; ++i) {
						messageStore.remove(filteredMessageList.get(filteredMessageList.size() - 1 - i));
					}
				}
				
				case "/remove" -> {	
					// removes replies by their message id if the root message ip matches with owner
					// or the reply ip mathches the requesting ip
					
//					Message msg = null;
					
//					for (var i : Message.getAllMessages()) {
//						if (i.getId()) { // found the message, no time to find it's parent
//							
//						}
//					}
//					
//					Collections.binarySearch(Message.getAllMessages(), value, new Comparator<Message>() {
//
//						@Override
//						public int compare(Message o1, Message o2) {
//							return Integer.compare(o1.getId(), o2.getId());
//						}
//					});
//					
					
					
				}
				}

			} else if (userMessage.matches("[/]\\w+ \\w+")) { // commands with one arg, type string
//				System.out.println("works 3!");
			} else if (userMessage.matches("[/]\\w+ \\d+ (\\w+.+?|.*)")) { // commands with two arg, type int string

				String[] commandString = userMessage.split(" ", 3);
				int value = Integer.parseInt(commandString[1]);
				
				switch (commandString[0]) {
				case "/reply" -> {

					Message msg = null;
					for (var i: Message.getAllMessages()) {
						if (i.getId() == value) {
							msg = i;
							break;
						}
					}
					if (msg != null) {
						msg.setReplies(new Message(userIP, commandString[2]));
					}
				}
				}
				
				
				
				
			} else if (userMessage.matches("[/]\\w+ \\w+ \\d+")) { // commands with two arg, type string int
//				System.out.println("works 5!");
			} else if (userMessage.matches("[/]\\w+(-\\w+)? \\w+ \\w+")) { // commands with two arg, type string string

				String[] commandString = userMessage.split(" ");

				switch (commandString[0]) {
				case "/edit" -> { // edits the last message sent by user, based on ip
					Optional<Message> lastMessage = messageStore.getLastMessage(userIP);

					if (lastMessage.isPresent()) {
						lastMessage.get().setMessage(
								lastMessage.get().getMessage().replaceFirst(commandString[1], commandString[2] + "*"));
					}
				}
				case "/edit-reply" -> { // edits the last message reply sent by user, based on ip
					Optional<Message> lastMessageReply = messageStore.getLastMessageReply(userIP);

					if (lastMessageReply.isPresent()) {
						lastMessageReply.get().setMessage(
								lastMessageReply.get().getMessage().replaceFirst(commandString[1], commandString[2] + "*"));
					}
				}

				}

			}
		} else {
			messageStore.add(new Message(userIP, userMessage));
		}
	}
}
