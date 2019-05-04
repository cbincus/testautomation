package test.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.core.common.AbstractPOM;
import test.webelements.WebLink;
import test.webelements.WebText;

public class SettingsPage extends AbstractPOM {
	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[@class='js-quickedit-page-title page-title']")
	public WebText pageName;
	
	@FindBy(xpath = "//a[contains(@class, 'shortcut-action')]")
	public WebLink manageShortcut;
}
