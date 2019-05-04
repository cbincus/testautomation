package test.tests.basic;

import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.Article;
import test.poms.Shortcuts;

public class ArticleTest extends BaseTest {
	@Test
	public void test() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/4");
		Article article = new Article(driver);
		System.out.println(article.metadataTxt.getText());
		
		Shortcuts shortcuts = new Shortcuts(driver);
		shortcuts.openShortcuts();
	}
}
