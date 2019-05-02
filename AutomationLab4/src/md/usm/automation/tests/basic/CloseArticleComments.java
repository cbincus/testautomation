package md.usm.automation.tests.basic;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import md.usm.automation.core.common.BaseTest;
import md.usm.automation.poms.Article;
import md.usm.automation.poms.CreateArticle;

public class CloseArticleComments extends BaseTest {
	@Test
	public void test() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/4/edit");
		Article article = new Article(driver);
		article.editLnk.click();
		
		CreateArticle createArticle = new CreateArticle(driver);
		if (createArticle.commentSettingsDiv.getAttribute("open") == null)
			createArticle.commentSettingsDiv.click();
		
		createArticle.commentsRb.selectByVisibleText("Closed");
		
		createArticle.saveBtn.click();
		
		Assert.assertTrue(driver.findElements(By.xpath("//h2[@class='title comment-form__title']")).size() < 1);
	}
}
