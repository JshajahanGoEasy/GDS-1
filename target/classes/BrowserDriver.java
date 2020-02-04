package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class BrowserDriver {
	
	public static WebDriver driver;
	public Properties prop;
	
	public WebDriver browser() throws IOException
	{
		prop = new Properties();
		FileInputStream fy =new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\objects.properties");
		prop.load(fy);
		String browserName =prop.getProperty("browser");
		
			
			
			//Create a instance of ChromeOptions class
			ChromeOptions options = new ChromeOptions();

			//Add chrome switch to disable notification - "**--disable-notifications**"
			options.addArguments("--disable-notifications");
			

			//Set path for driver exe 
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\Browser Drivers\\chromedriver.exe");

			//Pass ChromeOptions instance to ChromeDriver Constructor
			 driver =new ChromeDriver(options);
			 driver.manage().window().maximize();
		
	
		return driver;
		
	
	
	
}
	

}

