import java.util.Scanner;

/**
 * A quick implementation of a chatbot
 * 
 * @author Tyler Egan
 */
public class Chatbot {
	
	private static Scanner scan = new Scanner(System.in);
	private static User client = null;
	//I'd include the database here as well, but then I wouldn't be able to test the methods.
	
	public static void main(String args[]) {
		//Connect to database.
		FakeDatabase DB = new FakeDatabase();
		
		//Store messages in database.
		//This would be very different in a real implementation.
		Message.storeMessage(DB, "Account", "to create or modify an account, enter your information in the following format (' included)\n" + 
				"\"Create 'your name' 'your password' 'any other information you want stored'\"\n" +
				"To modify your account, do the same as above, but use \"Modify\" instead of \"Create\".\n" + 
				"\"Modify 'your name' 'your password' 'your new name' 'your new password' 'any other information you want stored'");
		Message.storeMessage(DB, "Create-Invalid", "A null argument was passed, type account to see required format.");
		Message.storeMessage(DB, "Create-Duplicate", "Sorry, but that name already exists. Please choose another");
		Message.storeMessage(DB, "Create-Success", "Account successfully created.");
		Message.storeMessage(DB, "Modify-false", "Failed to login to existing account. No modification performed.");
		Message.storeMessage(DB, "Modify-true", "Account successfully modified.");
		Message.storeMessage(DB, "Farewell", "Have a good day. Bye.");
		Message.storeMessage(DB, "Greeting", "Hello.");
		Message.storeMessage(DB, "Help", "If you are looking for what I know, here is a list.\n" +
				"Create Account: You will then be given the format to enter information.\n" +
				"Modify Account: You will then be given the format to enter information.\n" +
				"Close program: just end the conversation with bye.\n");
		Message.storeMessage(DB, "Other", "What would you like to do?");
		Message.storeMessage(DB, "Question", "Sorry, but I am a simple bot and cannot answer most questions.");
		
		//Chat with client
		String title = "Greeting";
		System.out.println(sendMessage(DB, title));
		String clientMes;
		while(true) {
			clientMes = receiveMessage();
			title = analyzeMessage(DB, clientMes);
			System.out.println(sendMessage(DB, title));
			if(title.equals("Farewell")) {
				break;
			}
		}
		
		
	}
	
	/**
	 * Analyzes a user's input and returns the title for the Message to 
	 * retrieve for a response.
	 * 
	 * @param DB The database holding the messages and users.
	 * @param input A user's comment to the chatbot.
	 * @return The title of the Message to retrieve.
	 */
	public static String analyzeMessage(FakeDatabase DB, String input) {
		//Check database exists.
		if(DB == null) {
			return null;
		}
		String title = null;
		//Determine which message title to use.
		if(input == null) {
			title = "Greeting";
		} else if(input.toLowerCase().contains("account")) {
				title = "Account";
		} else if(input.length() > 6 && input.substring(0, 6).toLowerCase().equals("create")) {
			title = "Create";
			//Try to create account
			String accountInfo[] = parseAccountInfo(input);
			String result = createUser(DB, accountInfo[0], accountInfo[1], accountInfo[2]);
			title += "-" + result;
			if(result.equals("Success")) {
				client = User.retrieveUser(DB, accountInfo[0]);
			}
		} else if(input.length() > 6 && input.substring(0, 6).toLowerCase().equals("modify")) {
			title = "Modify";
			boolean result = false;
			//Try to update account
			String accountInfo[] = parseAccountInfo(input);
			result = updateUserProfile(DB, accountInfo);
			title += "-" + result;
			if(result) {
				client = User.retrieveUser(DB, accountInfo[2]);
			}
		} else if(input.toLowerCase().contains("help")) {
			title = "Help";
		} else if(input.toLowerCase().contains("?")) {
			title = "Question";
		} else if(input.toLowerCase().contains("bye")) {
			title = "Farewell";
		} else {
			title = "Other";
		}
		return title;
	}

	/**
	 * Connects to the database and adds a user.
	 * 
	 * @param DB The database holding the messages and users.
	 * @param name The name of the user.
	 * @param password The password of the user.
	 * @param otherInfo Any other info for the user.
	 * @return A string explaining what happened.
	 */
	public static String createUser(FakeDatabase DB, String name, String password, String otherInfo) {
		if(DB == null || name == null || password == null || otherInfo == null) {
			return "Invalid";
		}
		return User.createUser(DB, name, password, otherInfo);
	}
	
	/**
	 * Parses a user's Create command to find their desired
	 *  account info.
	 * 
	 * @param input The user's command for creating an account.
	 * @return An array containing the name, password, and other info
	 * 			about the user's account.
	 */
	private static String[] parseAccountInfo(String input) {
		//Initialize variables.
		String accountInfo[] = new String[5];
		String placeHolder = "";
		boolean inWord = false;
		//Loop through the input and parse out each piece of account info.
		for(int i = 0; i < input.length() ; i++) {
			if(inWord) {
				if(input.charAt(i) == '\'') {
					inWord = false;
					for(int j = 0; j < accountInfo.length; j++) {
						if(accountInfo[j] == null) {
							accountInfo[j] = placeHolder;
							placeHolder = "";
							break;
						}
					}
				} else {
					placeHolder += input.charAt(i);
				}
			} else { //End first if loop
				if(input.charAt(i) == '\'') {
					inWord = true;
				}
			}
		}
		return accountInfo;
	}
	
	/**
	 * Retrieves the next message sent by the user.
	 * 
	 * @return The next message from the user.
	 */
	public static String receiveMessage() {
		String retVal = scan.nextLine();
		return retVal;
	}
	
	/**
	 * Retrieves a message from the database and returns its content to be printed.
	 * 
	 * @param DB The database holding the messages and users.
	 * @param input The title of the message to retrieve.
	 * @return The response to the user.
	 */
	public static String sendMessage(FakeDatabase DB, String input) {
		Message response =  Message.retrieveMessage(DB, input);
		if(response == null) {
			return null;
		}
		if(response.content.contains("successfully")) {
			return (response.content + " Hello " + client.name);
		}
		return response.content;
	}
	
	/**
	 * Updates a user's account with provided info
	 * 
	 * @param DB The database holding the messages and users.
	 * @param accountInfo A string array containing login information
	 * 						and the new account info.
	 * @return true if no issue occurred, false otherwise.
	 */
	public static boolean updateUserProfile(FakeDatabase DB, String[] accountInfo) {
		//Check input.
		if(DB == null || accountInfo == null) {
			return false;
		}
		return DB.modifyClient(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4]);
	}
}
