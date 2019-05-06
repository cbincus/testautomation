package test.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.webelements.WebLink;
import test.webelements.WebText;

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
