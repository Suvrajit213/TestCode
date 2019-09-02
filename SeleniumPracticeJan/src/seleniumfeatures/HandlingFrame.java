package seleniumfeatures;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> Frame = driver.findElements(By.tagName("iframe"));
		System.out.println(Frame.size());

		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.xpath("//*[@id='draggable']"));
		System.out.println(element.getText());
		driver.switchTo().defaultContent();
		
		
	}

}
