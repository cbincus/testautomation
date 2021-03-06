package test.poms;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.webelements.WebLink;

public class Shortcuts extends AbstractPOM {
	public Shortcuts(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[@id='toolbar-item-shortcuts']")
	public WebLink shortcutsBtn;

	@FindBy(xpath = "//nav[@aria-label = 'User-defined shortcuts']//ul[@class = 'toolbar-menu']/li/a")
	public List<WebLink> shortcutLinks;
	
	@FindBy(xpath = "//a[@class='edit-shortcuts']")
	public WebLink editShortcuts;
	
	public WebLink selectShortcutByVisibleText(String text) {
		for (WebLink link : shortcutLinks) 
			if (text.equalsIgnoreCase(link.getText()))
				return link;
		return null;
	}
	
	public void openShortcuts() {
		if (!shortcutsBtn.getAttribute("class").contains("is-active"))
			shortcutsBtn.click();
	}
}
