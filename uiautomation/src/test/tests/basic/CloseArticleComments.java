package test.tests.basic;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.Article;
import test.poms.ArticleSettings;

public class CloseArticleComments extends BaseTest {
	@Test
	public void test() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/4/edit");
		Article article = new Article(driver);
		article.editLnk.click();
		
		ArticleSettings createArticle = new ArticleSettings(driver);
		if (createArticle.commentSettingsDiv.getAttribute("open") == null)
			createArticle.commentSettingsDiv.click();
		
		createArticle.commentsRb.selectByVisibleText("Closed");
		
		createArticle.saveBtn.click();
		
		Assert.assertTrue(driver.findElements(By.xpath("//h2[@class='title comment-form__title']")).size() < 1);
	}
}
