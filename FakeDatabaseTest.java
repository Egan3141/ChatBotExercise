import org.junit.Assert;
//import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class FakeDatabaseTest {
	
	static FakeDatabase DB;
	
	//I still can't get this to work
//	@BeforeClass
//	static void setup() {
//		DB = new FakeDatabase();
//		System.out.println("setup ran");
//	}
	
	void setup() {
		DB = new FakeDatabase();
		DB.addUser(new User("getTest", "testPW", "none"));
		DB.addUser(new User("modifyTest1", "testPW", "none"));
		DB.addUser(new User("modifyTest2", "testPW", "none"));
		DB.addMessage(new Message("Test Title", "Test Content"));
		
	}
	
	@Test
	void getMessageTest() {
		if(DB == null) setup();
		Assert.assertNull("Null Input", DB.getMessage(null));
		Assert.assertNotNull("Not null input", DB.getMessage("Test Title"));
	}
	
	@Test
	void getUserTest() {
		if(DB == null) setup();
		if(DB == null) setup();
		Assert.assertNull("Null Input", DB.getUser(null));
		Assert.assertNotNull("Not null input", DB.getUser("getTest"));
	}

	@Test
	void modifyClientTest() {
		if(DB == null) setup();
		Assert.assertFalse("Null inputs", DB.modifyClient(null, null, null, null, null));
		Assert.assertFalse("Change to existing name", DB.modifyClient("modifyTest", "testPW", "getTest", "irrelevant", "none"));
		Assert.assertFalse("False information", DB.modifyClient("modifyTest", "fakePW", "newName", "irrelevant", "none"));
		Assert.assertTrue("Change password only", DB.modifyClient("modifyTest1", "testPW", "modifyTest1", "New Password", "None"));
		Assert.assertTrue("Full change", DB.modifyClient("modifyTest2", "testPW", "New User", "New Password", "None"));
	}
}
