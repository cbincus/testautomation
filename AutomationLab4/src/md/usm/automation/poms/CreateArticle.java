package md.usm.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.AbstractPOM;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebRadio;
import md.usm.automation.webelements.WebTypifiedElement;

public class CreateArticle extends AbstractPOM {
	public CreateArticle(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='edit-title-wrapper']//input[@id='edit-title-0-value']")
	public WebTypifiedElement titleElement;
	
	@FindBy(xpath = "//details[@id = 'edit-comment-0']")
	public WebLink commentSettingsDiv;
	
	@FindBy(xpath = "//input[@name = 'comment[0][status]']")
	public WebRadio commentsRb;

	@FindBy(xpath = "//input[@id = 'edit-comment-0-status-1']")
	public WebTypifiedElement closeCommentsBtn;
	
	@FindBy(xpath = "//input[@id = 'edit-submit']")
	public WebTypifiedElement saveBtn;
}
