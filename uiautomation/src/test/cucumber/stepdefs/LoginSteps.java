package test.cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import test.core.cucumber.World;
import test.poms.Login;

public class LoginSteps {
	private static final String LOGIN_PAGE = "http://dev-allied-automation-drupal.pantheonsite.io/user/login";

	private World world;
	private Login loginPage;
	
	public LoginSteps(World world) {
		this.world = world;
	}
	
	@Given("^the login page is opened$")
	public void openLoginPage() {
		world.driver.get(LOGIN_PAGE);
		loginPage = new Login(world.driver);
	}
	
	@When("^I submit my username and password$")
	public void submitCredentials() {
		loginPage.usernameInput.sendKeys("allied2019");
		loginPage.passwordInput.sendKeys("LbQ8al0&HVnH2vLrnn");
		loginPage.submitBtn.click();
	}
	
	@Then("^I should be logged in$")
	public void checkLogin() {
		
	}
}
