package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest {
	public static WebDriver driver; // Defining a global WebDriver driver
	public  Properties prop;


	@BeforeMethod
	public void initializeBrowser() throws IOException {

		// Getting data from data.properties file
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		//String browserName = System.getProperty("browser");// for parameterisng job in Jenkins and maven
		String browserName = prop.getProperty("browser");

		// for initializing Chrome Browser
		if (browserName.contains("chrome")) {
			
			DesiredCapabilities ch=new DesiredCapabilities();
			ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			
			Map<String, Object> prefs = new HashMap<String, Object>();

			//add key and value to map as follow to switch off browser notification
			//Pass the argument 1 to allow and 2 to block
			prefs.put("profile.default_content_setting_values.notifications", 2);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromeDriver\\chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless")) //for using Chrome Browser in headless mode
			{
				options.addArguments("headless");
			}
			options.merge(ch);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);

		}

		// for initializing Firefox Browser
		if (browserName.contains("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\drivers\\firefoxDriver\\geckodriver.exe");
			
			FirefoxOptions options =new FirefoxOptions();
			
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			

			driver = new FirefoxDriver(options);

		}

		// for initializing Internet Explorer Browser
		if (browserName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\drivers\\internetExplorerDriver\\IEDriverServer3_4.exe");
			driver = new InternetExplorerDriver();
		}

		// Clearing cookies and maximising window screen
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		// defining an implicit timeout
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// returning driver object to use it in test cases
		driver.get(prop.getProperty("url"));// hitting the url
		
	}

	public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}
	
	@AfterMethod
	public void teardown() {
		
		//closing driver
		driver.close();
	}

}
