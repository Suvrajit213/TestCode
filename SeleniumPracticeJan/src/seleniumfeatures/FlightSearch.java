package seleniumfeatures;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlightSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
	     WebDriver driver = new ChromeDriver();
	     driver.get("https://www.tripadvisor.in/");
	     
	    driver.manage().window().maximize();
	    
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    driver.findElement(By.xpath("//*[@class='overlays-modal-Modal__container--1JxH1']/div[2]")).click();
	    
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    
	    
	    driver.findElement(By.xpath("//*[@class='brand-quick-links-QuickLinkTileItem__link--2V0Pn']"
	    		+ "/span[@class='ui_icon flights brand-quick-links-QuickLinkTileItem__icon--26TMI']")).click();
	    
	    
	    driver.findElement(By.xpath("//*[@id='taplc_flights_search_form_0']/div/div[2]/"
	    		+ "div/div/span/div[1]/div[2]/div[1]/div/div[1]/input[2]")).sendKeys("Mumbai");
	    
	    driver.findElement(By.xpath("//*[@id='taplc_flights_search_form_0']/div/div[2]/div/div/span/"
	    		+ "div[1]/div[2]/div[1]/div/div[1]/input[2]")).sendKeys("Delhi");
	    
	    

	}

}
