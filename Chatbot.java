import java.util.Scanner;

public class Chatbot {
	
	private static Scanner scan = new Scanner(System.in);
	private static FakeDatabase DB;
	
	public static void main(String args[]) {
		//Connect to database and store starting data.
		DB = new FakeDatabase();
		User.createUser(DB, "Chat Bot", "Admin", "No other info");
		User.createUser(DB, "Client", "password", "No other info");
		//Store messages in database.
		//This would be very different in a real implementation.
		Message.storeMessage(DB, "Greeting", "Hello.");
		Message.storeMessage(DB, "Question", "Sorry, but I am a simple bot and cannot answer questions.");
		Message.storeMessage(DB, "Other", "Interesting.");
		Message.storeMessage(DB, "Account", "Account creation not yet supported");
		sendMessage(null);
		String clientMes = receiveMessage();
		while(!clientMes.contains("bye")) {
			System.out.println("Enter while loop with message: " + clientMes);
			sendMessage(clientMes);
			clientMes = receiveMessage();
		}
		
		
	}
	
	public static void sendMessage(String input) {
		//Analyze input and retrieve acceptable response.
		//Analysis would be much better a real implementation.
		Message response = null;
		if(input == null) {
			response = Message.retrieveMessage(DB, "Greeting");
		} else if(input.contains("account")) {
			response = Message.retrieveMessage(DB, "Account");
		} else if(input.contains("?")) {
			response = Message.retrieveMessage(DB, "Question");
		} else {
			response = Message.retrieveMessage(DB, "Other");
		}
		//print response to user.
		System.out.println(response.message);
	}
	
	public static String receiveMessage() {
		String retVal = scan.nextLine();
		return retVal;
	}
	
	public void createUser() {
		
	}
	
	public void updateUserProfile() {
		
	}
}
