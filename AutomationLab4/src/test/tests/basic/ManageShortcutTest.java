package test.tests.basic;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.core.common.BaseTest;
import test.poms.SettingsPage;
import test.poms.Shortcuts;

public class ManageShortcutTest extends BaseTest {
	@Test
	public void addShortcutTest() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/admin/appearance");
		SettingsPage settingsPage = new SettingsPage(driver);
		String pageName = settingsPage.pageName.getText();
		settingsPage.manageShortcut.click();
		
		Shortcuts shortcuts = new Shortcuts(driver);
		shortcuts.openShortcuts();
		Assert.assertTrue(shortcuts.selectShortcutByVisibleText(pageName) != null);
	}
}
