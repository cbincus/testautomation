package test.poms;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.core.common.Helpers;
import test.webelements.WebTypifiedElement;

public class MainPage extends AbstractPOM {
	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='view-content']")
	public WebTypifiedElement pageContent;
	
	private List<WebElement> articleLst = 
			pageContent.findElements(By.xpath(
					"//div[@class = 'view-content']//article[contains(@class, 'node--type-article')]"));

	public List<WebTypifiedElement> getArticleList() {
		return Helpers.getWebTypifiedElementList(articleLst);
	}
}
