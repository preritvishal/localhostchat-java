package localhostchat.message;

public class Test {

	public static void main(String[] args) {
//		var test = new Message("localhost", "test message").getJsonString();
//		System.out.println(test);
//		var test = MessageStore.getObject()
//				.add(new Message("localhost", "test 1"))
//				.add(new Message("localhost", "test 2"));
//		
//		System.out.println(test.getMessagesInString());
		
		MessageServiceImpl.workWithIt("/clear");
		MessageServiceImpl.workWithIt("/clear 12");
		MessageServiceImpl.workWithIt("/clear this");
		MessageServiceImpl.workWithIt("/clear 12 delete");
		MessageServiceImpl.workWithIt("/clear test 12");
		MessageServiceImpl.workWithIt("/clear test test");
		MessageServiceImpl.workWithIt("testing this");
		
	
	}

}
