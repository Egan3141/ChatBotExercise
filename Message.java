
/**
 * Message object class that holds all the information pertaining to a message.
 * Also acts as a middleware for the Chatbot and FakeDatabase with information
 * pertaining to messages.
 * 
 * @author Tyler Egan
 */
public class Message {
	
	protected String message;
	protected User sender;
	protected User receiver;
	
	/**
	 * A constructor for a Message object. Because every message must be added to the
	 * database, only storeMessage may access this constructor.
	 * 
	 * @param message The message to be stored.
	 * @param sender The User that sent the message.
	 * @param receiver The intended receiver of the message.
	 */
	private Message(String message, User sender, User receiver) {
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	/**
	 * Stores a message in the database.
	 * 
	 * @param message The message to be stored.
	 * @param sender The sender of the message.
	 * @param receiver The intended receiver of the message.
	 * @return returns a string explaining what happened.
	 * 	Success: User was added.
	 *  No User: Either the sender or receiver does not exist.
	 *  Null: A null argument was passed.
	 */
	public String storeMessage(String message, User sender, User receiver) {
		//Handle invalid input.
		if(message == null || sender == null || receiver == null) {
			return "null";
		}
		if(User.retrieveUser(sender.name) == null || User.retrieveUser(receiver.name) == null) {
			return "No User";
		}
		//Create the message.
		Message newMessage = new Message(message, sender, receiver);
		
		//Placeholder code for storing the message in the database.
		FakeDatabase.addMessage(newMessage);
		
		//Only if the message was added to database.
		return "Success";
	}
	
	/**
	 * Retrieves the latest message for that user.
	 * 
	 * @param receiver Who the message is for.
	 * @return The latest message for the user or null if there are no more messages for the user.
	 */
	public Message retrieveMessage(User receiver) {
		//Placeholder code for retrieving the message from the database.
		return FakeDatabase.getMessage(receiver);
	}

}
