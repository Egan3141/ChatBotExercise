import java.util.ArrayList;

/**
 * A fake database to make the chatbot implementation more reasonable.
 * 
 * @author Tyler Egan
 */
public class FakeDatabase {
	
	private static ArrayList<Message> messages;
	private static ArrayList<User> users;
	
	/**
	 * A constructor to make a Message and a User list
	 */
	public FakeDatabase() {
		messages = new ArrayList<Message>();
		users = new ArrayList<User>();
	}
	
	/**
	 * Adds a message to the fake database.
	 * 
	 * @param newMes The message to be added.
	 * @return true if message added, false otherwise.
	 */
	public boolean addMessage(Message newMes) {
		//Check input.
		if(newMes ==  null) {
			return false;
		}
		messages.add(newMes);
		return true;
	}
	
	/**
	 * Adds a User to the fake database
	 * 
	 * @param newUser The user to be added.
	 * @return true if message added, false otherwise.
	 */
	public boolean addUser(User newUser) {
		//Check input.
		if(newUser ==  null) {
			return false;
		}
		newUser.password = fakeHash(newUser.password);
		users.add(newUser);
		return true;
	}
	
	/**
	 * Fake hashing algorithm.
	 * 
	 * @param password The password to be hashed.
	 * @return The hashed value.
	 */
	private String fakeHash(String password) {
		// Fake hashing algorithm.
		return (password + "123");
	}

	/**
	 * Retrieve a message from the database if it exists.
	 * 
	 * @param title The title of the message to retrieve.
	 * @return The message or null if not found.
	 */
	public Message getMessage(String title) {
		for(Message message: messages) {
			if(message.title.equals(title)) {
				return message;
			}
		}
		return null;
		
	}
	
	/**
	 * Retrieves a user from the database if they exist.
	 * 
	 * @param name The name of the user to retrieve.
	 * @return The user or null if not found.
	 */
	public User getUser(String name) {
		for(User user: users) {
			if(user.name.equals(name)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Modifies an existing account with the new information.
	 * 
	 * @param name The name of the account to modify.
	 * @param password The password of the account to modify.
	 * @param newName The new name of the account.
	 * @param newPassword The new password of the account.
	 * @param otherInfo The new other info for the account.
	 * @return True if successful, false otherwise.
	 */
	public boolean modifyClient(String name, String password, String newName, String newPassword, String otherInfo ) {
		//Check input
		if(name == null || newName == null || newPassword == null || otherInfo == null) {
			return false;
		}
		// Check if newName already exists and if so, is not the same as the original.
		if(getUser(newName) != null) {
			if(!newName.equals(name)) {
				return false;
			}
		}
		//Verify old information
		if(!verifyClient(name, password)) {
			return false;
		}
		//Find and update the appropriate account.
		for(User user: users) {
			if(user.name.equals(name)) {
				user.name = newName;
				user.password = fakeHash(newPassword);
				user.otherInfo = otherInfo;
				break;
			}
		}
		return true;
	}
	
	/**
	 * Compares the provided password is correct for the
	 *  provided account.
	 * 
	 * @param name The account to check.
	 * @param password The password to use.
	 * @return True if successful, false otherwise.
	 */
	private boolean verifyClient(String name, String password) {
		if(name == null || password == null) {
			return false;
		}
		//Hash the password to compare hashes.
		password = fakeHash(password);
		//Find appropriate user and compare passwords.
		for(User user: users) {
			if(user.name.equals(name)) {
				if(user.password.equals(password)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

}
