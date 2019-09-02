package seleniumfeatures;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBox {
	public static String str;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.tizag.com/htmlT/htmlcheckboxes.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		List<WebElement> CheckBox = driver.findElements(By.xpath("//*[@class='display'][1]/input[@name='sports']"));

		System.out.println(CheckBox.size());

		for (WebElement e : CheckBox) {
			System.out.println(e.isSelected());

			System.out.println(e.getAttribute("value"));
			if (e.getAttribute("value").equals("football") || (e.getAttribute("value").equals("soccer")))

				e.click();
		}

	}

}
