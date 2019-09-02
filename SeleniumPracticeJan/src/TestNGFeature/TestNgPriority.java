package TestNGFeature;

import org.testng.annotations.Test;

import org.testng.Assert;

public class TestNgPriority {

	/*
	 * @Test(priority=2) public void login() {
	 * 
	 * System.out.println("Log in"); }
	 * 
	 * @Test(priority=1) public void navigating() {
	 * 
	 * System.out.println("navigating"); }
	 * 
	 * @Test(priority=3) public void Logout() {
	 * 
	 * System.out.println("Log out"); }
	 */

	@Test
	public void login() {

		System.out.println("Log in");

		Assert.assertEquals("Sam", "Sam1");
	}

	@Test(dependsOnMethods = { "login" })
	public void navigating() {

		System.out.println("navigating");
	}

	@Test(dependsOnMethods = { "login", "navigating" })
	public void Logout() {

		System.out.println("Log out");
	}
}
