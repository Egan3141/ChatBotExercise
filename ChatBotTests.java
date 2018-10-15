import org.junit.Assert;
import org.junit.Assume;
///import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class ChatBotTests {
	
	public static FakeDatabase DB;
	
	/*
	 * I know I should have done it as commented below, but no matter how much I looked
	 * I couldn't determine why the method wasn't running.
	 */
//	@BeforeClass
//	public static void setup() {
	public void setup() {
		//Initialize database and add required messages and a user to run tests.
		DB = new FakeDatabase();
		DB.addMessage(new Message("Account", "to create or modify an account, enter your information in the following format (' included)\n" + 
				"\"Create 'your name' 'your password' 'any other information you want stored'\"\n" +
				"To modify your account, do the same as above, but use \"Modify\" instead of \"Create\".\n" + 
				"\"Modify 'your name' 'your password' 'your new name' 'your new password' 'any other information you want stored'"));
		DB.addMessage(new Message("Create-Invalid", "A null argument was passed, type account to see required format."));
		DB.addMessage(new Message("Create-Duplicate", "Sorry, but that name already exists. Please choose another"));
		DB.addMessage(new Message("Create-Success", "Account successfully created."));
		DB.addMessage(new Message("Modify-false", "Failed to login to existing account. No modification performed."));
		DB.addMessage(new Message("Modify-true", "Account successfully modified."));
		DB.addMessage(new Message("Farewell", "Have a good day. Bye."));
		DB.addMessage(new Message("Greeting", "Hello."));
		DB.addMessage(new Message("Help", "If you are looking for what I know, here is a list.\n" +
				"Create Account: You will then be given the format to enter information.\n" +
				"Modify Account: You will then be given the format to enter information.\n" +
				"Close program: just end the conversation with bye.\n"));
		DB.addMessage(new Message("Other", "What would you like to do?"));
		DB.addMessage(new Message("Question", "Sorry, but I am a simple bot and cannot answer most questions."));
		//analyzeMessage User
		DB.addUser(new User("analyzeTest", "test", "none"));
		//updateUserProfile user
		DB.addUser(new User("updateTest", "test", "none"));
	}

	@Test
	public void analyzeMessageTest() {
		//Because I couldn't get @BeforeClass to work.
		if(DB == null) setup();
		//Ensure it is meaningful to run these tests.
		Assume.assumeNotNull(DB);
		//Null input
		Assert.assertNull("Null database", Chatbot.analyzeMessage(null, null));
		Assert.assertTrue("Null input", Chatbot.analyzeMessage(DB, null).equals("Greeting"));
		//Account related input
		Assert.assertTrue("Contains account", Chatbot.analyzeMessage(DB, "How do accounts work?").equals("Account"));
		String create = "create 'a' ";
		Assert.assertTrue("Create Invalid account", Chatbot.analyzeMessage(DB, create).equals("Create-Invalid"));
		create = "create 'analyzeTest' 'password' 'none'";
		Assert.assertTrue("Create Duplicate account", Chatbot.analyzeMessage(DB, create).equals("Create-Duplicate"));
		create = "create 'new' 'password' 'none'";
		Assert.assertTrue("Create Duplicate account", Chatbot.analyzeMessage(DB, create).equals("Create-Success"));
		String modify = "modify 'this won't work'";
		Assert.assertTrue("Modify failure", Chatbot.analyzeMessage(DB, modify).equals("Modify-false"));
		modify = "modify 'analyzeTest' 'test' 'analyzeTest2' 'test' 'none'";
		Assert.assertTrue("Modify failure.", Chatbot.analyzeMessage(DB, modify).equals("Modify-true"));
		//Other input
		Assert.assertTrue("contains help", Chatbot.analyzeMessage(DB, "I need help").equals("Help"));
		Assert.assertTrue("A question unrelated to above content", Chatbot.analyzeMessage(DB, "this is a question?").equals("Question"));
		Assert.assertTrue("Contains bye", Chatbot.analyzeMessage(DB, "this is goodbye").equals("Farewell"));
		Assert.assertTrue("Contains no important words or characters", Chatbot.analyzeMessage(DB, "Hi.").equals("Other"));
	}
	
	@Test
	public void createUserTest() {
		//Null input
		Assert.assertTrue("Null input", Chatbot.createUser(null, null, null, null).equals("Invalid"));
		//Meaningless to test valid input sense it is only a call on another method.
	}
	
	@Test
	public void sendMessageTest() {
		Assert.assertNull("Null input", Chatbot.sendMessage(null, null));
		/*
		 * Cannot test the rest of the code because it relies on a private global variable.
		 * How does amex recommend handling this kind of code? I assume adding another variable 
		 * to each paramter list is not a good idea even though I did it with the fake database.
		 */
	}
	
	@Test
	public void updateUserProfileTest() {
		Assert.assertFalse("Null input", Chatbot.updateUserProfile(null, null));
		/*
		 * Assuming additional tests are meaningless since all that remains is a call to
		 *  another method.
		 */

	}

}
