package test.poms;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import test.core.common.AbstractPOM;
import test.poms.elementblocks.Comment;
import test.poms.elementblocks.CommentForm;
import test.webelements.WebLink;
import test.webelements.WebText;

public class Article extends AbstractPOM {
	private static final String COMMENT_XPATH = "article[@typeof = 'schema:Comment']";

	public Article(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[contains(@class, 'region-content')]//h1[contains(@class, 'page-title')]")
	public WebText titleTxt;

	@FindBy(xpath = "//div[contains(@class, 'field--type-text-with-summary')]")
	public WebText contentTxt;

	@FindBy(xpath = "//div[contains(@class, 'node__meta')]")
	public WebText metadataTxt;

	@FindBy(xpath = "//a[contains(text(), 'Delete')]")
	public WebLink deleteLnk;

	@FindBy(xpath = "//a[contains(text(), 'Edit')]")
	public WebLink editLnk;

	@FindBy(xpath = "//form[@id='comment-form']")
	public CommentForm commentForm;

	@FindBy(xpath = "//article[@typeof = 'schema:Comment']")
	public List<Comment> comments;

	public Comment getCommentById(int id) {
		return comments
				.stream()
				.filter(c -> (c.getCommentId() == id))
				.collect(Collectors.toList()).get(0);
	}
	
	public Comment getPrecedingComment(Comment comment) {
		String precedingCommentXpath = String.format("(./preceding::%s)[1]", 
				COMMENT_XPATH);
		List<WebElement> precedingComments = comment.getWrappedElement().findElements(By.xpath(precedingCommentXpath));
		
		if (precedingComments.size() == 0)
			return null;
		else
			return HtmlElementLoader.createHtmlElement(
				Comment.class, precedingComments.get(0), "comment");
	}
	
	public Comment getFollowingThreadComment(Comment comment) {
		String followingCommentXpath = "./following::article[@typeof = 'schema:Comment'][1]";
		// "((./following-sibling::*[position() = 1][@class = 'indented']//article[@typeof = 'schema:Comment'])[last()]/following::article[@typeof = 'schema:Comment'])[1]"
		WebElement commentElement = comment.getWrappedElement();
		String replyThreadXpath = "./following-sibling::*[position() = 1][@class = 'indented']";
		String lastReplyInThreadXpath = "(.//article[@typeof = 'schema:Comment'])[last()]";
		List<WebElement> replyThread = commentElement
				.findElements(By.xpath(replyThreadXpath));
		
		if (replyThread.size() == 0) {
			List<WebElement> followingThread = commentElement
					.findElements(By.xpath(followingCommentXpath));
			if (followingThread.size() == 0)
				return null;
			else
				return HtmlElementLoader.createHtmlElement(
						Comment.class, followingThread.get(0), "comment");
		}
		else {
			WebElement followingCommentElement = replyThread.get(0)
					.findElement(By.xpath(lastReplyInThreadXpath))
					.findElement(By.xpath(followingCommentXpath));
			return HtmlElementLoader.createHtmlElement(
					Comment.class, followingCommentElement, "comment");
		}
	}
}
