package md.usm.automation.cucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import md.usm.automation.core.cucumber.World;

public class Hooks {
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";
	private static final String LOGIN_PAGE = "http://dev-allied-automation-drupal.pantheonsite.io/user/login";
	private static final int IMPLICITLY_WAIT = 5; // seconds
	private static final int PAGE_LOAD_TIMEOUT = 30;
	
	private World world;
	
	public Hooks(World world) {
		this.world = world;
	}
	
	@Before
	public void before() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		world.driver = new ChromeDriver();
		world.driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
		world.driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}
	
	@After
	public void after() {
		world.driver.quit();
	}
}
