package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	
	@FindBy(xpath="//div[text()='Enroll new business']")
	WebElement enrollNewBusinessLink;
	
	
	public void clickEnrollNewBusinessLink() {
		enrollNewBusinessLink.click();
	}
}
