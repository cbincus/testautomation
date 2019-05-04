package test.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.webelements.WebTextInput;
import test.webelements.WebTypifiedElement;

public class Login extends AbstractPOM {

	public Login(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='edit-name']")
	public WebTextInput usernameInput;
	
	@FindBy(xpath = "//input[@id='edit-pass']")
	public WebTextInput passwordInput;
	
	@FindBy(xpath = "//input[@id='edit-submit']")
	public WebTypifiedElement submitBtn;
}
