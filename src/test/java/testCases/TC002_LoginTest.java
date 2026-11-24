package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_Login() {
		
		
		// HomePage 
		HomePage hm = new HomePage(driver) ;
		
		hm.clickAccount();
		hm.clickLogin();	
		
		// LoginPage
		LoginPage lp = new LoginPage(driver);
		
		lp.getUserName(p.getProperty("username"));
		lp.getPassword(p.getProperty("password"));
		lp.clickLogin();
		
		// MyaccountPage
		MyAccountPage myacc = new MyAccountPage(driver);
		
		boolean actual_mesg = myacc.AccountPageExist();
		Assert.assertEquals(actual_mesg, true);
		
	}
	
	
	
	
	

}
