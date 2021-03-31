package testCases;
//Testing 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.baseTest;

//extends is used to inherit another class
public class Test_script_test1 extends baseTest{
	//public WebDriver driver; //declaring a driver locally for parallel testing in future
	
	//adding Log4j logger
	private static Logger log=LogManager.getLogger(baseTest.class.getName());
	


	@Test(dataProvider = "credentials") // passing DataProvider values into the test case
	public void Login(String email, String password) throws InterruptedException {

		HomePage objHome = new HomePage(driver); // creating object of HomePage class
		LoginPage objLogin = new LoginPage(driver);// creating object of LoginPage class
		ForgotPasswordPage objFP=new ForgotPasswordPage(driver);
		
		log.debug("Clicking on Login link");
		objHome.LoginLink().click();
		log.info("Login Link is clicked");
		
		
		log.debug("Sending keys in email field");
		objLogin.EmailId().sendKeys(email);
		log.info("Email id is provided");
		
		log.debug("Sending keys in password field");
		objLogin.Password().sendKeys(password);
		log.info("password is provided");
		
		log.debug("Clicking on Login button");
		objLogin.LoginButton().click();
		log.info("Login button is clicked");
		
		log.debug("Clicking on Forgot Password button");
		objLogin.ForgotPassword().click();
		log.info("Forgot Password button is clicked");
		
		log.debug("Sending keys in email field");
		objFP.EmailId().sendKeys("kumarshubham2994");
		log.info("Email id is provided");
		
		log.debug("Clicking on Send Me Instruction button");
		objFP.SendMeInstructionButton().click();
		log.info("Send Me Instruction button is clicked");
	
		
	}


	// Creating a Data Provider method
	@DataProvider(name = "credentials")
	public Object[][] getData() {
		return new Object[][] { { "kumarshubham2994@gmail.com", "Shubham@4u" },
				{ "kumarshubham143@gmail.com", "Loveisevol@4u" } };

	}
}
