package test.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.webelements.WebCheckbox;
import test.webelements.WebLink;
import test.webelements.WebRadio;
import test.webelements.WebTextInput;
import test.webelements.WebTypifiedElement;

public class ArticleSettings extends SettingsPage {
	public ArticleSettings(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='edit-title-0-value']")
	public WebTextInput titleInput;
	
	@FindBy(xpath = "//body")
    private WebTypifiedElement contentInput;

    public void fillInContent(String value) {
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
	
	@FindBy(xpath = "//summary[contains(text(),'Promotion options')]")
	public WebLink promotionMenu;
	
	public void expandPromotionMenu() {
		if (promotionMenu.getAttribute("aria-expanded").equalsIgnoreCase("false"))
			promotionMenu.click();
	}
	
	@FindBy(xpath = "//input[@id='edit-promote-value']")
	public WebCheckbox frontPageCheckbox;
	
	@FindBy(xpath = "//input[@id='edit-sticky-value']")
	public WebCheckbox stickyOptionCheckbox;
}
