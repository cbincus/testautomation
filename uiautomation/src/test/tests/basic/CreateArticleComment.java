package test.tests.basic;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.testng.annotations.Test;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import test.core.common.BaseTest;
import test.core.common.Helpers;
import test.poms.Article;
import test.poms.elementblocks.Comment;

public class CreateArticleComment extends BaseTest {
	private static final String TEST_COMMENT_TITLE = "test comment title";
	private static final String TEST_COMMENT = "test comment body";

	@Test
	public void createArticleComment() {
		loginWithDefaultUser();
		
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/3");
		Article article = new Article(driver);
		article.commentForm.commentTitleInput.sendKeys(TEST_COMMENT_TITLE);
		driver.switchTo().frame(0);
		article.commentForm.commentBodyInput.sendKeys(TEST_COMMENT);
		driver.switchTo().defaultContent();

		String commentCreateTimestamp = ZonedDateTime.now(ZoneOffset.UTC)
				.format(DateTimeFormatter.ofPattern("EEE, MM/dd/yyyy - HH:mm").localizedBy(Locale.ENGLISH));

		article.commentForm.submitBtn.click();

		Helpers.waitForPageLoad(driver, 10);

		int commentsCnt = article.getComments().size();
		Comment comment = HtmlElementLoader.createHtmlElement(Comment.class, article.getComments().get(commentsCnt - 1),
				"this comment");

		Helpers.check2StringIfEquals(commentCreateTimestamp, comment.dateTime.getText());
		Helpers.check2StringIfEquals(TEST_COMMENT_TITLE, comment.titleLink.getText());
		Helpers.check2StringIfEquals(TEST_COMMENT, comment.text.getText());
	}
}
