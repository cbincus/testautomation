package test.poms.elementblocks;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import test.webelements.WebLink;
import test.webelements.WebText;

public class Comment extends HtmlElement {
	@FindBy(xpath = ".//p[@class = 'comment__author']")
	public WebLink authorLink;
	
	@FindBy(xpath = ".//span[contains(text(),'new')]")
	public WebText newFlag;
	
	@FindBy(xpath = ".//p[@class = 'comment__time']")
	public WebText dateTime;
	
	@FindBy(xpath = ".//h3[@property = 'schema:name']")
	public WebLink titleLink;
	
	@FindBy(xpath = ".//div[@property = 'schema:text']")
	public WebText text;
	
	@FindBy(xpath = ".//p[@class = 'comment__permalink']/a")
	public WebLink permalink;
	
	public int getCommentId() {
		String permalinkUrl = permalink.getAttribute("href");
		return Integer.parseInt(
				permalinkUrl.substring(permalinkUrl.lastIndexOf("-") + 1));
	}
}
