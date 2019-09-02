package seleniumfeatures;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Radiobutton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.echoecho.com/htmlforms10.htm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> RadioButton = driver.findElements(By.xpath("//*[@class='table5']/input[@name='group1']"));
		
		System.out.println(RadioButton.size());

		for (WebElement e : RadioButton) {
			System.out.println(e.getAttribute("value"));
			System.out.println(e.isSelected());
			if (e.getAttribute("value").equals("Butter")) {
				e.click();
			}
		}

	}

}
