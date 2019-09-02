package TestNGFeature;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class annotation {

	@BeforeClass
	public void beforeClass() {

		System.out.println("Initialise selenium WebDriver for class 1");
	}

	@BeforeMethod

	public void beforeMethod() {

		System.out.println("Opening Browser");
	}

	@Test
	public void test1() {

		System.out.println("Inside Test 1");
	}

	@Test
	public void test2() {

		System.out.println("Inside Test 2");
	}

	@AfterMethod

	public void afterMethod() {

		System.out.println("Closing the Browser");
	}

	@AfterClass
	public void afterClass() {

		System.out.println("Destroy selenium WebDriver class 1");
	}

}

/*
 * @BeforeSuite
 * 
 * @BeforeTest
 * 
 * @BeforeClass
 * 
 * @BeforeMethod
 * 
 * @Test
 * 
 * @AfterMethod
 * 
 * @AfterClass
 * 
 * @AfterTest/
 * 
 * @AfterSuite
 */
