package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utitlities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= "DataDriven")
	public void verify_LoginDDT(String user, String pass, String exp) {
		
		try {
		// HomePage 
				HomePage hm = new HomePage(driver) ;
				
				hm.clickAccount();
				hm.clickLogin();	
				
				// LoginPage
				LoginPage lp = new LoginPage(driver);
				
				lp.getUserName(user);
				lp.getPassword(pass);
				lp.clickLogin();
				
				// MyaccountPage
				MyAccountPage myacc = new MyAccountPage(driver);
				boolean actual_mesg = myacc.AccountPageExist();
//				Assert.assertEquals(actual_mesg, true);
				
				
				if(exp.equalsIgnoreCase("Valid")) {
					if(actual_mesg==true) {
						myacc.clickLogout();
						Assert.assertTrue(true);
					}
				}else {
					Assert.assertTrue(false);
				}
				
				
				if(exp.equalsIgnoreCase("Invalid")) {
					if(actual_mesg==true) {
						myacc.clickLogout();
						Assert.assertTrue(false);
					}
				}else {
					Assert.assertTrue(true);
				}
					
		}
		catch(Exception e) {
			Assert.fail();
		}

	}
}
