package TestNGFeature;

import org.testng.annotations.Test;

public class GroupingTest {
	
	@Test(groups={"Performance"})
	public void Feature()
	{
		System.out.println("Inside feature1");
		
	}
	@Test(groups={"Regression"})
	public void Feature2()
	{
		System.out.println("Inside feature2");
		
	}
	@Test(groups={"Performance","Regression"})
	public void Feature3()
	{
		System.out.println("Inside feature3");
		
	}
	
	
	
	
}
