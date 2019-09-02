package TestNGFeature;

import org.testng.annotations.Test;

public class LearningDataparameter {

	@Test(dataProviderClass = TestDatafile.class, dataProvider = "testdata")
	public void registeruser(String firstname, String lastname, String email, String password) {

		System.out.println(firstname);
		System.out.println(lastname);
		System.out.println(email);
		System.out.println(password);
	}

	@Test(dataProviderClass = TestDatafile.class, dataProvider = "testdata")
	public void registeruser1(String firstname, String lastname) {

		System.out.println(firstname);
		System.out.println(lastname);
	}

	@Test(dataProviderClass = TestDatafile.class, dataProvider = "testdata")
	public void loginuser(String email, String password) {

		System.out.println(email);
		System.out.println(password);

	}
}
