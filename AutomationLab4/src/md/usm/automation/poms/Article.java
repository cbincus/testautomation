package md.usm.automation.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.AbstractPOM;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebText;
import md.usm.automation.webelements.WebTypifiedElement;

public class Article extends AbstractPOM {
	public Article(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[contains(@class, 'region-content')]//h1[contains(@class, 'page-title')]")
	public WebText titleTxt;
	
	@FindBy(xpath = "//div[contains(@class, 'field--type-text-with-summary')]")
	public WebText contentTxt;
	
	@FindBy(xpath = "//div[contains(@class, 'node__meta')]")
	public WebText metadataTxt;
	
	@FindBy(xpath = "//nav[@class = 'tabs' and @role = 'navigation']")
	public WebTypifiedElement articleTabs;
	
	public WebLink deleteLnk = (WebLink) articleTabs.findElement(By.xpath(
			"//a[contains(text(), 'Delete')]"));
	
	public WebLink editLnk = (WebLink) articleTabs.findElement(By.xpath(
			"//a[contains(text(), 'Edit')]"));
}
