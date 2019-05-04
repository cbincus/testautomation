package test.tests.basic;

import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.CreateArticle;

public class CreateArticleTest extends BaseTest {
	@Test
	public void test() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/add/article");
		CreateArticle createArticle = new CreateArticle(driver);
		createArticle.titleInput.clearAndFill("My test title");
		
		if (createArticle.commentSettingsDiv.getAttribute("open") == null)
			createArticle.commentSettingsDiv.click();
		
		createArticle.closeCommentsBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
