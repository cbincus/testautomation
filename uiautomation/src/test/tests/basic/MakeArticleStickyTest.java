package test.tests.basic;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.ArticleSettings;
import test.poms.MainPage;

public class MakeArticleStickyTest extends BaseTest {
	@Test
	public void makeArticleSticky() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/2/edit");
		ArticleSettings createArticle = new ArticleSettings(driver);
		createArticle.expandPromotionMenu();
		createArticle.stickyOptionCheckbox.click();
		
		createArticle.saveBtn.click();
		
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/");
		MainPage mainPage = new MainPage(driver);
		WebElement firstArticle = mainPage.articleList.get(0);
		Assert.assertTrue(firstArticle.getAttribute("class").contains("node--sticky"));
	}
}
