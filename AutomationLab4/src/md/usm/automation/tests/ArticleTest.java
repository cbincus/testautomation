package md.usm.automation.tests;

import org.testng.annotations.Test;

import md.usm.automation.core.BaseTest;
import md.usm.automation.poms.Article;
import md.usm.automation.poms.Shortcuts;

public class ArticleTest extends BaseTest {
	@Test
	public void test() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/4");
		Article article = new Article(driver);
		System.out.println(article.metadata.getText());
		
		Shortcuts shortcuts = new Shortcuts(driver);
		shortcuts.openShortcuts();
	}
}
