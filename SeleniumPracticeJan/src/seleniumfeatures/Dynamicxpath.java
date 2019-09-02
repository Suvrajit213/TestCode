package seleniumfeatures;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dynamicxpath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://in.yahoo.com/?p=us");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='uh-search-box']")).sendKeys("Selenium");
		List<WebElement> list =	driver.findElements(By.xpath("//*[starts-with(@id,'yui_3_18_0_3_1552932')]"));
		
		System.out.println(list.size());
         list.get(1).click();
		

	}

}
