package TestNGFeature;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGFeature.TestngListener.class)

public class TestCasesForListener {
	WebDriver driver = new FirefoxDriver();

	// Test to pass as to verify listeners.
	@Test
	public void Login() {
		driver.get("http://demo.guru99.com/V4/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*
		 * Access details to demo site. User ID : mngr176101 Password : EtEnYvy
		 * This access is valid only for 20 days.
		 */
		driver.findElement(By.name("uid")).sendKeys("mngr176101");
		driver.findElement(By.name("password")).sendKeys("EtEnYvy");
		driver.findElement(By.name("btnLogin")).click();
	}

	// Forcefully failed this test as verify listener.
	@Test
	public void TestToFail() {
		System.out.println("This method to test fail");
		Assert.assertTrue(false);
	}
}
