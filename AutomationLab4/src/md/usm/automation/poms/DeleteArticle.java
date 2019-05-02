package md.usm.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.common.AbstractPOM;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebText;

public class DeleteArticle extends AbstractPOM {
	public DeleteArticle(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[contains(@class, 'region-header')]"
			+ "//h1[contains(@class, 'js-quickedit-page-title')]")
	public WebText confirmDeleteHeader;

	@FindBy(xpath = "//input[@id='edit-submit']")
	public WebLink deleteArticleLnk;
}
