package test.tests.basic;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.CreateArticle;
import test.poms.MainPage;
import test.webelements.WebTypifiedElement;

public class MakeArticleStickyTest extends BaseTest {
	@Test
	public void makeArticleSticky() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/2/edit");
		CreateArticle createArticle = new CreateArticle(driver);
		createArticle.expandPromotionMenu();
		createArticle.stickyOptionCheckbox.click();
		
		createArticle.saveBtn.click();
		
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/");
		MainPage mainPage = new MainPage(driver);
		List<WebTypifiedElement> articleList = mainPage.getArticleList();
		WebTypifiedElement firstArticle = articleList.get(0);
		Assert.assertTrue(firstArticle.getAttribute("class").contains("node--sticky"));
	}
}
