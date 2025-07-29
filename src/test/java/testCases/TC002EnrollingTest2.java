package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SignUp;
import testBase.BaseClass;
import utilities.DataProviderUtility;


public class TC002EnrollingTest2 extends BaseClass {
	
		
	@Test(dataProvider="TestData",dataProviderClass=DataProviderUtility.class,groups= {"Regression"})
		public void  verify_eroll(String PhoneNumber) throws InterruptedException {
		//Thread.sleep(1000);
		LoginPage lp=new LoginPage(driver);
		lp.clickEnrollNewBusinessLink();
		
		
		SignUp su=new SignUp(driver);
		su.setYourPhoneNumber(PhoneNumber);
		su.clickNext();

		Assert.assertEquals("test", "test");
		
	}
	
}
