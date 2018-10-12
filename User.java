
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
	private User(String name, String password, String otherInfo) {
		this.name = name;
		this.password = password;
		this.otherInfo = otherInfo;
	}
	
	/**
	 * Creates a User object and adds that object to the database.
	 * @param DB 
	 * 
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
		if(name == null || password == null || otherInfo == null) {
			return "Invalid";
		}
		if(retrieveUser(DB, name) != null) {
			return "Duplicate";
		}
		
		//Placeholder code for hashing the password.
		String hashedPW = fakeHash(name, password);
		
		//Create the User object.
		User newUser = new User(name, hashedPW, otherInfo);
		
		//Placeholder code for adding to the database.
		DB.addUser(newUser);
		
		//Only if user was added to database.
		return "Success";
	}
	
	/**
	 * Fake hashing algorithm.
	 * 
	 * @param name Used to track password since this is a fake hash.
	 * @param password The password to be hashed.
	 * @return The hashed value.
	 */
	private static String fakeHash(String name, String password) {
		// Fake hashing algorithm.
		return (name + password);
	}

	/**
	 * Retrieves the specified user information from the database.
	 * 
	 * @param name The name of the user to be retrieved.
	 * @return The user object if found, null if not found.
	 */
	public static User retrieveUser(FakeDatabase DB, String name) {
		//Placeholder code for retrieving information from the database.
		return DB.getUser(name);
	}

}
