package test.tests.basic;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.core.common.Helpers;
import test.poms.Article;
import test.poms.ArticleSettings;
import test.poms.Content;

public class CreateArticleTest extends BaseTest {
	@Test
	public void createArticleTest() {
		final String testTitle = "My test title";
		final String testContent = "My test content";

		loginWithDefaultUser();

		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/add/article");
		ArticleSettings articleSettings = new ArticleSettings(driver);
		articleSettings.titleInput.sendKeys("My test title");
		articleSettings.fillInContent("My test content");

		ZonedDateTime articleZdt = ZonedDateTime.now(super.zoneid);
		String articleDateTime = Helpers.getDrupalDateTime(articleZdt);
		String articleDateTimeShort = Helpers.getDrupalDateTimeShort(articleZdt);
		String articleMetadata = String.format(
				"Submitted by %s on %s", username, articleDateTime);

		articleSettings.saveBtn.click();

		Helpers.waitForPageLoad(driver, 10);

		Article article = new Article(driver);
		Helpers.check2StringIfEquals(article.titleTxt.getText(), testTitle);
		Helpers.check2StringIfEquals(article.contentTxt.getText(), testContent);
		Helpers.check2StringIfEquals(article.metadataTxt.getText(), articleMetadata);

		Map<String, String> articleData = new HashMap<>();
		articleData.put("TITLE", testTitle);
		articleData.put("CONTENT TYPE", "Article");
		articleData.put("AUTHOR", username);
		articleData.put("STATUS", "Published");
		articleData.put("UPDATED\n" + "SORT ASCENDING", articleDateTimeShort);

		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/admin/content");
		Content content = new Content(driver);
		Map<String, String> firstContentEntry = content.contentTable
				.getRowsAsStringMappedToHeadings()
				.get(0);
		Assert.assertTrue(Helpers.containsAll(firstContentEntry, articleData));
	}
}
