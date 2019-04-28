package md.usm.automation.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import md.usm.automation.poms.Login;

public class BaseTest {
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";
	private static final String LOGIN_PAGE = "http://dev-allied-automation-drupal.pantheonsite.io/user/login";
	private static final int IMPLICITLY_WAIT = 5; // seconds
	private static final int PAGE_LOAD_TIMEOUT = 30;

	protected WebDriver driver;

	public BaseTest() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}

	@BeforeTest
	public void loginWithDefaultUser() {
		driver.get(LOGIN_PAGE);
		Login loginPage = new Login(driver);
		loginPage.usernameInput.sendKeys("allied2019");
		loginPage.passwordInput.sendKeys("LbQ8al0&HVnH2vLrnn");
		loginPage.submitBtn.click();
	}

	@AfterTest
	public void quit() {
/*		if (result.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		driver.quit();
	}
}
