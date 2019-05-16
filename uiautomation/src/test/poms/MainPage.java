package test.poms;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.core.common.AbstractPOM;

public class MainPage extends AbstractPOM {
	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class = 'view-content']//article[contains(@class, 'node--type-article')]")
	public List<WebElement> articleList;
	
	public WebElement selectArticleByUrl(String url) {
		
	}
}
