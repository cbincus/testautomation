package md.usm.automation.poms;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.common.AbstractPOM;
import md.usm.automation.core.common.Helpers;
import md.usm.automation.webelements.WebTypifiedElement;

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
