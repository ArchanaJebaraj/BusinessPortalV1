package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUp extends BasePage {

	WebDriver driver;
	public SignUp(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@data-testid='textfield-login-input']")
	WebElement txtYourPhoneNumber;
	
	@FindBy(xpath="//div[text()='Next']")
	WebElement btnNext;
	
	public void setYourPhoneNumber(String PNumber) {
		txtYourPhoneNumber.sendKeys(PNumber);
	}
	public void clickNext()
	{
		btnNext.click();
	}
	
	

}
