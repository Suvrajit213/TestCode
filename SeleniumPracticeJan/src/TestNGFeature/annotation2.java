package TestNGFeature;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class annotation2 {

	@BeforeTest

	public void beforeTest() {

		System.out.println("Start test execution");

	}

	@BeforeSuite

	public void beforeSuite() {
		System.out.println("Inside class2 Before suite");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Inside class2 After suite");

	}

	@BeforeClass
	public void beforeclass() {

		System.out.println("Initialise selenium WebDriver for class 2");
	}

	@Test

	public void test3() {

		System.out.println("Inside Test 3");
	}

	@AfterTest

	public void afterTest() {

		System.out.println("Destroy test execution");

	}

	@AfterClass
	public void afterClass() {

		System.out.println("Destroy selenium WebDriver class 2");
	}

}
