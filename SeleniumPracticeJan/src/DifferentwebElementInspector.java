import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DifferentwebElementInspector {

	public static void main(String[] args) {
		// TODO Auto-generated method st

		int i;

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.w3schools.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		List<WebElement> list = driver.findElements(By.tagName("a"));

		System.out.println(list.size());
		for (i = 0; i < list.size(); i++)
			;
		//System.out.println(list.);
		
		if(list.get(i).getText().equals("Learn CSS")){
			
			list.get(i).click();
			
			
			
		}

	}

}
