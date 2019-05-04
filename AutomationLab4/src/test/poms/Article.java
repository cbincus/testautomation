package test.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.webelements.WebLink;
import test.webelements.WebText;
import test.webelements.WebTextInput;
import test.webelements.WebTypifiedElement;

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
	
	public WebLink deleteLnk = new WebLink(articleTabs.findElement(By.xpath(
			"//a[contains(text(), 'Delete')]")));
	
	public WebLink editLnk = new WebLink(articleTabs.findElement(By.xpath(
			"//a[contains(text(), 'Edit')]")));
	
	@FindBy(xpath = "//form[@id='comment-form']")
	public WebTypifiedElement commentForm;
	
	public WebTextInput commentTitleInput = new WebTextInput(commentForm
			.findElement(By.xpath("//input[@id='edit-subject-0-value']")));
}
