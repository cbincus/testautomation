package md.usm.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import md.usm.automation.core.BaseTest;
import md.usm.automation.poms.SettingsPage;
import md.usm.automation.poms.Shortcuts;

public class ManageShortcutTest extends BaseTest {
	@Test
	public void addShortcutTest() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/admin/appearance");
		SettingsPage settingsPage = new SettingsPage(driver);
		String pageName = settingsPage.pageName.getText();
		settingsPage.manageShortcut.click();
		
		Shortcuts shortcuts = new Shortcuts(driver);
		Assert.assertTrue(shortcuts.selectShortcutByVisibleText(pageName) != null);
	}
}
