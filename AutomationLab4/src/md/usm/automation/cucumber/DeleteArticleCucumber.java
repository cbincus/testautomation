package md.usm.automation.cucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import md.usm.automation.core.common.Helpers;
import md.usm.automation.poms.Article;
import md.usm.automation.poms.DeleteArticle;
import md.usm.automation.poms.Login;

@CucumberOptions(features = "features/deletearticle.feature")
public class DeleteArticleCucumber extends AbstractTestNGCucumberTests {
	private static final String CHROME_DRIVER_PATH = "c:\\Portable Software\\ChromeDriver\\chromedriver.exe";
	private static final String LOGIN_PAGE = "http://dev-allied-automation-drupal.pantheonsite.io/user/login";
	private static final int IMPLICITLY_WAIT = 5; // seconds
	private static final int PAGE_LOAD_TIMEOUT = 30;
	
	protected WebDriver driver;

	@Given("^the user is logged in$")
	public void loginWithDefaultUser() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	
		driver.get(LOGIN_PAGE);
		Login loginPage = new Login(driver);
		loginPage.usernameInput.sendKeys("allied2019");
		loginPage.passwordInput.sendKeys("LbQ8al0&HVnH2vLrnn");
		loginPage.submitBtn.click();
	}

	@And("^the article page is opened$")
	public void openArticle() {
		driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/5");
	}
	
	@When("^the \"Delete\" button is clicked")
	public void delete() {
		Article article = new Article(driver);
		article.deleteLnk.click();
	}
	
	@And("^the deletion is confirmed$")
	public void deleteConfirm() {
		DeleteArticle deleteArticle = new DeleteArticle(driver);
		Helpers.waitForVisibleElement(driver, deleteArticle.confirmDeleteHeader, 10);
		deleteArticle.deleteArticleLnk.click();
	}
	
	@Then("^a status message is shown$")
	public void checkDeletionStatus() {
		Helpers.waitForVisibleElement(driver, 
				driver.findElement(By.xpath("//div[@class='messages messages--status']")), 10);
	}
}
