package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	// constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement MsgHeading;
	
	@FindBy(xpath="(//a[text()='Logout'])[2]")
	WebElement Logout;
	
	
	
	// 1 st method 
	/*
	public String getConfirmMessege() {
		String messege = MsgHeading.getText();
		return messege;
	}
	*/
	
	// 2nd method
	public boolean AccountPageExist() {
		try {
		return (MsgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	// click logout
	public void clickLogout() {
		Logout.click();
	}

}
