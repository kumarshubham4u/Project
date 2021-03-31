package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	private By emailId = By.id("user_email");
	private By password = By.id("user_password");
	private By loginButton= By.name("commit");
	private By forgotPassword=By.linkText("Forgot Password?");
	
	
	public WebElement EmailId() {
		
		return driver.findElement(emailId);
	}
	
	
	public WebElement Password() {
		
		return driver.findElement(password);
	}
	
	public WebElement LoginButton() {
		
		return driver.findElement(loginButton);
	}
	
	public WebElement ForgotPassword() {
		
		return driver.findElement(forgotPassword);
	}
}
