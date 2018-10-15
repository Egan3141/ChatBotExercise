
/**
 * User object class that holds all the information pertaining to a user.
 * Also acts as a middleware for the Chatbot and FakeDatabase with information
 * pertaining to users.
 * 
 * @author Tyler Egan
 */
public class User {
	protected String name;
	protected String password;
	protected String otherInfo;
	
	/**
	 * A constructor for a User object. Because every user must be added to the
	 * database, only createUser may access this constructor.
	 * 
	 * @param name The name of the user.
	 * @param password The password of the user.
	 * @param otherInfo Other info about the user.
	 */
	protected User(String name, String password, String otherInfo) {
		this.name = name;
		this.password = password;
		this.otherInfo = otherInfo;
	}
	
	/**
	 * Creates a User object and adds that object to the database.
	 * 
	 * @param DB The database holding the users.
	 * @param name The name of the user.
	 * @param password The password of the user.
	 * @param otherInfo Other info a user would store.
	 * @return returns a string explaining what happened.
	 * 	Success: User was added.
	 *  Duplicate: Another user with that name already exists.
	 *  Invalid: Invalid input was provided. 
	 */
	public static String createUser(FakeDatabase DB, String name, String password, String otherInfo) {
		//Handle invalid input.
		if(DB == null || name == null || password == null || otherInfo == null) {
			return "Invalid";
		}
		if(retrieveUser(DB, name) != null) {
			return "Duplicate";
		}
		//Create the User object and add it to database.
		User newUser = new User(name, password, otherInfo);
		DB.addUser(newUser);
		return "Success";
	}

	/**
	 * Retrieves the specified user information from the database.
	 * 
	 * @param DB The database holding the users.
	 * @param name The name of the user to be retrieved.
	 * @return The user object if found, null if not found.
	 */
	public static User retrieveUser(FakeDatabase DB, String name) {
		if(DB == null || name == null) {
			return null;
		}
		//Get user from database.
		return DB.getUser(name);
	}

}
