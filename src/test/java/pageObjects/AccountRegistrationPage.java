package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	// this is constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);	
	}

	// this is pageFactory locator
	
	@FindBy(id="input-firstname")
	WebElement FNAME;
	
	@FindBy(id="input-lastname")
	WebElement LNAME;
	
	@FindBy(id="input-email")
	WebElement EMAIL;
	
	@FindBy(id="input-telephone")
	WebElement TELIPHONE;
	
	@FindBy(id="input-password")
	WebElement PASSWORD;
	
	@FindBy(id="input-confirm")
	WebElement CON_PASSWORD;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement CHKBOX;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement CONTINUE;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement CONFIRM_MESSEGE;
	
	
	// actions method
	
	public void setFirstName(String fname) {
		FNAME.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		LNAME.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		EMAIL.sendKeys(email);
	}
	
	public void setTeliphone(String teliphone) {
		TELIPHONE.sendKeys(teliphone);
	}
	
	public void setPassword(String password) {
		PASSWORD.sendKeys(password);
	}
	
	public void setConPassword(String con_pass) {
		CON_PASSWORD.sendKeys(con_pass);
	}
	
	public void clickCheckBox() {
		CHKBOX.click();
	}
	
	public void clickContinueBtn() {
		CONTINUE.click();
	}
	
	//h1[text()='Your Account Has Been Created!']
	public String getConfirmMessege() {
		try
		{
			return(CONFIRM_MESSEGE.getText());
		}catch(Exception e){
			return(e.getMessage());
			
		}
	}
	
	

}
