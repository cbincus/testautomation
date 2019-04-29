package md.usm.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.AbstractPOM;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebRadio;
import md.usm.automation.webelements.WebTextInput;
import md.usm.automation.webelements.WebTypifiedElement;

public class CreateArticle extends AbstractPOM {
	public CreateArticle(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='edit-title-0-value']")
	public WebTextInput titleInput;
	
	@FindBy(xpath = "//body")
    private WebTypifiedElement contentInput;

    public void fillInContent(String value){
        driver.switchTo().frame(0);
        contentInput.sendKeys(value);
        driver.switchTo().defaultContent();
    }
    
    public String getContent() {
    	driver.switchTo().frame(0);
        String result = contentInput.getText();
        driver.switchTo().defaultContent();
        return result;
    }
	
	@FindBy(xpath = "//details[@id = 'edit-comment-0']")
	public WebLink commentSettingsDiv;
	
	@FindBy(xpath = "//input[@name = 'comment[0][status]']")
	public WebRadio commentsRb;

	@FindBy(xpath = "//input[@id = 'edit-comment-0-status-1']")
	public WebTypifiedElement closeCommentsBtn;
	
	@FindBy(xpath = "//input[@id = 'edit-submit']")
	public WebTypifiedElement saveBtn;
	
	@FindBy(xpath = "//summary[@aria-controls = 'edit-path-0']")
	public WebTypifiedElement urlAliasMenu;
	
	public void expandURLAliasMenu() {
		if (!urlAliasMenu.isDisplayed())
			urlAliasMenu.click();
	}
	
	@FindBy(xpath = "//input[@id = 'edit-path-0-alias']")
	public WebTextInput urlAliasInput;
}
