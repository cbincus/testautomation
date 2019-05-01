package md.usm.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import md.usm.automation.core.BaseTest;
import md.usm.automation.core.Helpers;
import md.usm.automation.poms.Article;
import md.usm.automation.poms.CreateArticle;

public class CreateArticleURLAlias extends BaseTest {
	@Test
	public void createArticleURLAlias() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/2/edit");
		CreateArticle createArticle = new CreateArticle(driver);
		// createArticle.urlAliasMenu.click();
		Helpers.sleep(1000);
		createArticle.urlAliasInput.sendKeys("/testarticle");
		
		String title = createArticle.titleInput.getAttribute("value");
		String content = createArticle.getContent();
		
		System.out.println(title);
		System.out.println(content);
		
		createArticle.saveBtn.click();
		
		Article article = new Article(driver);
		System.out.println(article.titleTxt.getText());
		System.out.println(article.contentTxt.getText());
		Assert.assertTrue(title.equalsIgnoreCase(article.titleTxt.getText()));
		Assert.assertTrue(content.equalsIgnoreCase(article.contentTxt.getText()));
	}
}
