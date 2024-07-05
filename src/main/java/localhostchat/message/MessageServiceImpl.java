package localhostchat.message;

public class MessageServiceImpl {

	static MessageStore messageStore = MessageStore.getObject();

	public static void workWithIt(String userMessage) {

		if (userMessage.charAt(0) == '/') {
			if (userMessage.matches("[/]\\w+")) { // matches the simple command with no args
				switch (userMessage) {

				case "/clear", "/cls", "/clean" -> messageStore.removeAll();

				case "/last" -> messageStore.removeLast();

				case "/testing", "/dummy" -> {
					messageStore.removeAll();
					for (int i = 0; i < 13; ++i)
						messageStore.add(new Message("localhost", "dummy message " + i));
					}
				}
			} else if (userMessage.matches("[/]\\w+ \\d+")) { // commands with one arg, type int
//				System.out.println("works 2!");
			} else if (userMessage.matches("[/]\\w+ \\w+")) { // commands with one arg, type string
//				System.out.println("works 3!");
			} else if (userMessage.matches("[/]\\w+ \\d+ \\w+")) { // commands with two arg, type int string
//				System.out.println("works 4!");
			} else if (userMessage.matches("[/]\\w+ \\w+ \\d+")) { // commands with two arg, type string int
//				System.out.println("works 5!");
			} else if (userMessage.matches("[/]\\w+ \\w+ \\w+")) { // commands with two arg, type string string
//				System.out.println("works 6!");
			}
		} else {
			messageStore.add(new Message("user ip", userMessage));
		}
	}
}
