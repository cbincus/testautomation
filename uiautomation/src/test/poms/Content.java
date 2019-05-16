package test.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import test.webelements.WebTable;

public class Content extends SettingsPage {
	public Content(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//table[contains(@class, 'views-table')]")
	public WebTable contentTable;
}
