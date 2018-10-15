import org.junit.Assert;
import org.junit.jupiter.api.Test;


class UserTests {
	
	static FakeDatabase DB;
	
	void setup() {
		DB = new FakeDatabase();
		DB.addUser(new User("tester", "testPW", "none"));
	}

	@Test
	void createUserTest() {
		if(DB == null) setup();
		Assert.assertTrue("Null input", User.createUser(null, null, null, null).equals("Invalid"));
		Assert.assertTrue("Duplicate input", User.createUser(DB, "tester", "PW", "none").equals("Duplicate"));
		Assert.assertTrue("Valid input", User.createUser(DB, "newUser", "PW", "none").equals("Success"));
	}
	
	@Test
	void retrieveUserTest() {
		if(DB == null) setup();
		Assert.assertNull("Null input", User.retrieveUser(null, null));
		/*
		 * Assuming additional tests are meaningless since all that remains is a call to
		 *  another method.
		 */
	}

}
