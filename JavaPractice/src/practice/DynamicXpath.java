package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicXpath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.google.com/search?ei=XhY-XLzdJYGvyAOZqJqYCw&q=goo"
				+ "gle&oq=google&gs_l=psy-ab.3..0i131i67l2j0i67l5j0l3.576.576..2130..."
				+ "0.0..0.172.172.0j1......0....1..gws-wiz.iaRICVXGRDc");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.close();
		
		System.out.println("Browser closed");
		
		
		
		
	}

}
