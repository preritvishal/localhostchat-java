package localhostchat.message;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl {

	static MessageStore messageStore = MessageStore.getObject();

	public static void workWithIt(String userMessage, String userIP) {

		userMessage = userMessage.strip();

		if (userMessage.charAt(0) == '/') {
			if (userMessage.matches("[/]\\w+")) { // matches the simple command with no args
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
				}
			} else if (userMessage.matches("[/]\\w+ \\d+")) { // commands with one arg, type int
				String[] commandString = userMessage.split(" ");
				int value = 0;
				try {
					value = Integer.parseInt(commandString[1]);
				} catch (NumberFormatException e) {
					System.out.println("Entered argument to command " + commandString[0] + " is wrong!");
				}

				switch (commandString[0]) {

				case "/last" -> { // removes multiple last messages based on ip address
//
					List<Message> filteredMessageList = messageStore.getFilteredMessageList(msg -> msg.getIp().equals(userIP));
					
					if (value >= filteredMessageList.size()) {
						value = filteredMessageList.size();
					}
					
					for (int i = 0; i < value; ++i) {
						messageStore.remove(filteredMessageList.get(filteredMessageList.size() - 1 - i));
					}
				}
				}

			} else if (userMessage.matches("[/]\\w+ \\w+")) { // commands with one arg, type string
//				System.out.println("works 3!");
			} else if (userMessage.matches("[/]\\w+ \\d+ \\w+")) { // commands with two arg, type int string
//				System.out.println("works 4!");
			} else if (userMessage.matches("[/]\\w+ \\w+ \\d+")) { // commands with two arg, type string int
//				System.out.println("works 5!");
			} else if (userMessage.matches("[/]\\w+ \\w+ \\w+")) { // commands with two arg, type string string

				String[] commandString = userMessage.split(" ");

				switch (commandString[0]) {
				case "/edit" -> { // edits the last message sent by user, based on ip
					Optional<Message> lastMessage = messageStore.getLastMessage(userIP);

					if (lastMessage.isPresent()) {
						lastMessage.get().setMessage(
								lastMessage.get().getMessage().replaceFirst(commandString[1], commandString[2] + "*"));
					}
				}

				}

			}
		} else {
			messageStore.add(new Message(userIP, userMessage));
		}
	}
}
