package seleniumfeatures;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dropdown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.rediff.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Create a Rediffmail account")).click();

		WebElement dropdown = driver.findElement(By.xpath("//*[@id='tblcrtac']/tbody/tr[22]/td[3]/select[1]"));
		List<WebElement> option = dropdown.findElements(By.tagName("option"));
		System.out.println(option.size());

		String element;

		for (int i = 0; i < option.size(); i++) {
			element = option.get(i).getAttribute("value");
			if (element.equals("13")) {

				option.get(i).click();

			}

		}
		WebElement dropdown1 = driver.findElement(By.xpath("//*[@id='tblcrtac']/tbody/tr[22]/td[3]/select[2]"));

		List<WebElement> option1 = dropdown1.findElements(By.tagName("option"));

		System.out.println(option1.size());

		String element1;
		for (int j = 0; j < option1.size(); j++) {

			element1 = option1.get(j).getAttribute("value1");

			if (element1.equals("MAR")) {
				option1.get(j).click();

			}

		}

	}
}