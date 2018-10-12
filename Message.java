
/**
 * Message object class that holds all the information pertaining to a message.
 * Also acts as a middleware for the Chatbot and FakeDatabase with information
 * pertaining to messages.
 * 
 * @author Tyler Egan
 */
public class Message {
	
	protected String title;
	protected String message;
	
	/**
	 * A constructor for a Message object. Because every message must be added to the
	 * database, only storeMessage may access this constructor.
	 * 
	 * @param message The message to be stored.
	 * @param sender The User that sent the message.
	 * @param receiver The intended receiver of the message.
	 */
	private Message(String title, String message) {
		this.title = title;
		this.message = message;
	}

	/**
	 * Stores a message in the database.
	 * @param dB 
	 * 
	 * @param message The message to be stored.
	 * @param title A short description of the message
	 * @param message The actual message
	 */
	public static void storeMessage(FakeDatabase DB, String title, String message) {
		//Create the message.
		Message newMessage = new Message(title, message);
		
		//Placeholder code for storing the message in the database.
		DB.addMessage(newMessage);
	}
	
	/**
	 * Retrieves the latest message for that user.
	 * 
	 * @param string Who the message is for.
	 * @return The latest message for the user or null if there are no more messages for the user.
	 */
	public static Message retrieveMessage(FakeDatabase DB, String title) {
		return DB.getMessage(title);
	}

}
