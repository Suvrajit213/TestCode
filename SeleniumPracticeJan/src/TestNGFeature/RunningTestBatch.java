package TestNGFeature;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class RunningTestBatch {

	@Test
	public void loginviaEmail() {
		System.out.println("Login via email");

		try {
			Assert.assertEquals("String", "String2");
		} catch (Throwable t) {
			System.out.println(t);
		}

		System.out.println("After the assert condition");
		/*
		 * // Assert.assertEquals(1, 1);
		 * 
		 * // Assert.assertTrue(4>1);
		 * 
		 * // Assert.assertTrue(1>4);
		 * 
		 * //Assert.assertFalse(1 > 4);
		 * 
		 * Assert.assertFalse(1 < 4);
		 */
	}

	@Test
	public void loginviaFacebook() {
		System.out.println("Login via Facebook");
		throw new SkipException("Facebook functionality is not implemented ");
	}

	@Test
	public void loginviaTwitter() {
		System.out.println("Login via Twitter");
		throw new SkipException("Twitter functionality is not implemented");
	}

}
