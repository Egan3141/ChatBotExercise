
/**
 * Message object class that holds all the information pertaining to a message.
 * Also acts as a middleware for the Chatbot and FakeDatabase with information
 * pertaining to messages.
 * 
 * @author Tyler Egan
 */
public class Message {
	
	protected String title;
	protected String content;
	
	/**
	 * A constructor for a Message object. Because every message must be added to the
	 * database, only storeMessage may access this constructor.
	 * 
	 * @param title The title of the message.
	 * @param content The content of the message to be stored.
	 */
	protected Message(String title, String content) {
		this.title = title;
		this.content = content;
	}

	/**
	 * Stores a message in the database.
	 * 
	 * @param DB The database holding the messages.
	 * @param title A short description of the message.
	 * @param message The actual message.
	 * @return true if message stored, false otherwise.
	 */
	public static boolean storeMessage(FakeDatabase DB, String title, String content) {
		//Check input.
		if(DB == null || title == null || content == null) {
			return false;
		}
		//Create and add the message.
		Message newMessage = new Message(title, content);
		DB.addMessage(newMessage);
		return true;
	}
	
	/**
	 * Retrieves the latest message for that user.
	 * 
	 * @param DB the database holding the messages.
	 * @param title The title of the message
	 * @return The message containing that title, or null if non exist.
	 */
	public static Message retrieveMessage(FakeDatabase DB, String title) {
		if(DB == null  || title == null) {
			return null;
		}
		return DB.getMessage(title);
	}

}
