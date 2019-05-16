package test.tests.basic;

import org.testng.SkipException;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.Article;
import test.poms.elementblocks.Comment;

public class DeleteCommentTest extends BaseTest {
	@Test
	public void deleteCommentTest() {
		loginWithDefaultUser();
		
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/3");
		Article article = new Article(driver);
		
		int commentsCnt = article.comments.size();
		if (commentsCnt == 0)
			throw new SkipException("There are no comments to this article!");
		
		Comment myComment = article.getCommentById(26);
		System.out.println(article.getFollowingThreadComment(myComment).getCommentId());
	}
}
