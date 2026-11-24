package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement username;
	
	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement Login;
	
	
	
	public void getUserName(String email) {
		username.sendKeys(email);
	}
	
	public void getPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickLogin() {
		Login.click();
	}
	

}
