package md.usm.automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import md.usm.automation.core.common.AbstractPOM;
import md.usm.automation.webelements.WebLink;
import md.usm.automation.webelements.WebText;

public class SettingsPage extends AbstractPOM {
	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[@class='js-quickedit-page-title page-title']")
	public WebText pageName;
	
	@FindBy(xpath = "//a[contains(@class, 'shortcut-action')]")
	public WebLink manageShortcut;
}
