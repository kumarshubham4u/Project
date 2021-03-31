package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	public WebDriver driver;

	// constructor for class HomePage
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// locators definition
	private By loginLink = By.xpath("//span[text()='Login']");
	private By navBar = By.xpath("//ul[@class='nav navbar-nav navbar-right']");
	private By contactMail= By.xpath("//span[text()='info@qaclickacademy.com']");

	// methods for locators
	public WebElement LoginLink() {

		return driver.findElement(loginLink);
	}

	public WebElement Title() {

		return driver.findElement(loginLink);
	}

	public WebElement NavBar() {

		return driver.findElement(navBar);
	}
	
	public WebElement ContactMail() {

		return driver.findElement(contactMail);
	}
}
