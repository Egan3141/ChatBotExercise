import java.util.ArrayList;

/**
 * @author egant
 *
 */
public class FakeDatabase {
	
	private static ArrayList<Message> messages;
	private static ArrayList<User> users;
	
	/**
	 * 
	 */
	public FakeDatabase() {
		messages = new ArrayList<Message>();
		users = new ArrayList<User>();
	}

	/**
	 * @param name
	 * @return
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
	 * @param newUser
	 */
	public void addUser(User newUser) {
		users.add(newUser);
	}

	/**
	 * @param newMes
	 */
	public void addMessage(Message newMes) {
		messages.add(newMes);
	}

	/**
	 * @param title
	 * @return
	 */
	public Message getMessage(String title) {
		Message latestMes = null;
		for(Message message: messages) {
			if(message.title.equals(title)) {
				latestMes = message;
			}
		}
		return latestMes;
		
	}

}
