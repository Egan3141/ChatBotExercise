import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MessageTest {
	
	static FakeDatabase DB;
	
	void setup() {
		DB = new FakeDatabase();
		DB.addMessage(new Message("Test Title", "Test Content"));
	}

	@Test
	void storeMessageTest() {
		if(DB == null) setup();
		Assert.assertFalse("Null input", Message.storeMessage(null, null, null));
		Assert.assertTrue("Valid input", Message.storeMessage(DB, "someTitle", "someContent"));
	}
	
	@Test
	void retrieveMessageTest() {
		Assert.assertNull("Null input", Message.retrieveMessage(null, null));
		/*
		 * Assuming additional tests are meaningless since all that remains is a call to
		 *  another method.
		 */
	}

}
