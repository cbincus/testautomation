package md.usm.automation.tests.basic;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import md.usm.automation.core.common.BaseTest;
import md.usm.automation.poms.CreateArticle;
import md.usm.automation.poms.MainPage;
import md.usm.automation.webelements.WebTypifiedElement;

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
