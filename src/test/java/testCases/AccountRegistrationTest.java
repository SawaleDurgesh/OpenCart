package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups= {"Regression","Master"})
	public void Verify_Account_Register() {
		
		// create a object of homepage pageobject class
		HomePage home = new HomePage(driver);
		home.clickAccount();
		home.clickRegister();
		
		// creating a object of registration pageobject class to acess the method present in registraionpage class
		AccountRegistrationPage register = new AccountRegistrationPage(driver);
		register.setFirstName(generatedString().toUpperCase());
		
		register.setLastName(generatedString().toUpperCase());
		
		register.setEmail(generatedString()+"@gamil.com");
		
		register.setTeliphone(genetratedNumber());
		
		
		// for password
		String password = genetratedAlphaNum();
		
		register.setPassword(password);
		register.setConPassword(password);
		
		register.clickCheckBox();
		
		register.clickContinueBtn();
		
		String confmsg = register.getConfirmMessege();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
	}
	
	
	
	
	

}
