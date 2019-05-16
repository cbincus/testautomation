package test.poms.elementblocks;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import test.webelements.WebButton;
import test.webelements.WebTextInput;

public class CommentForm extends HtmlElement {
	@FindBy(xpath = ".//input[@id = 'edit-subject-0-value']")
	public WebTextInput commentTitleInput;
	
	@FindBy(xpath = "//body")
	public WebTextInput commentBodyInput;
	
	@FindBy(xpath = ".//input[@id='edit-submit']")
	public WebButton submitBtn;
	
	@FindBy(xpath = ".//input[@id='edit-preview']")
	public WebButton previewBtn;
}
