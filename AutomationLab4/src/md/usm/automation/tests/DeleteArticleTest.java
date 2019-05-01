package md.usm.automation.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import md.usm.automation.core.BaseTest;
import md.usm.automation.core.Helpers;
import md.usm.automation.poms.Article;
import md.usm.automation.poms.DeleteArticle;

public class DeleteArticleTest extends BaseTest {
	@Test
	public void test() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/9");
		Article article = new Article(driver);
		article.deleteLnk.click();
		
		DeleteArticle deleteArticle = new DeleteArticle(driver);
		Helpers.waitForVisibleElement(driver, deleteArticle.confirmDeleteHeader, 10);
		deleteArticle.deleteArticleLnk.click();
		Helpers.waitForVisibleElement(driver, 
				driver.findElement(By.xpath("//div[@class='messages messages--status']")), 10);
	}
}
