package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SignUp;
import testBase.BaseClass;


public class TC001Enrolling extends BaseClass {
	
		
	@Test(groups="Regression")
		public void  verify_eroll() throws InterruptedException {
		//Thread.sleep(1000);
		LoginPage lp=new LoginPage(driver);
		lp.clickEnrollNewBusinessLink();
		
		
		SignUp su=new SignUp(driver);
		su.setYourPhoneNumber("+965 12365478");
		su.clickNext();

		Assert.assertEquals("test", "test1");
		
	}
}
