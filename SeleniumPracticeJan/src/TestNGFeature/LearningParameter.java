package TestNGFeature;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LearningParameter {
	@Parameters("email")
	@Test
	public void login(String email) {
		System.out.println("Facebook login");
	}
}
