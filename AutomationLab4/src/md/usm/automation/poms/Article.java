package md.usm.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.AbstractPOM;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebText;

public class Article extends AbstractPOM {
	public Article(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[contains(@class, 'region-content')]//h1[contains(@class, 'page-title')]")
	public WebText title;
	
	@FindBy(xpath = "//div[contains(@class, 'node__content')]")
	public WebText content;
	
	@FindBy(xpath = "//div[contains(@class, 'node__meta')]")
	public WebText metadata;
	
	@FindBy(xpath = "//nav[@class = 'tabs' and @role = 'navigation']//a[contains(text(), 'Delete')]")
	public WebLink delete;
	
	@FindBy(xpath = "//nav[@class = 'tabs' and @role = 'navigation']//a[contains(text(), 'Edit')]")
	public WebLink edit;
}
