package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
	
	public WebDriver driver;
	public ForgotPasswordPage(WebDriver driver) {
		
		this.driver=driver;
	}
	
	private By emailId = By.xpath("//*[@id=\"user_email\"]");
	private By sendMeInstructionButton= By.name("commit");

	
	
	public WebElement EmailId() {
		
		return driver.findElement(emailId);
	}

	public WebElement SendMeInstructionButton() {
		
		return driver.findElement(sendMeInstructionButton);
	}
	
}
