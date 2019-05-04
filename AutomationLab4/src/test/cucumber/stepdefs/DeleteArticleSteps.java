package test.cucumber.stepdefs;

import org.openqa.selenium.By;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import test.core.common.Helpers;
import test.core.cucumber.World;
import test.poms.Article;
import test.poms.DeleteArticle;

public class DeleteArticleSteps {
	private World world;
	
	public DeleteArticleSteps(World world) {
		this.world = world;
	}
	
	@Given("^the article page is opened$")
	public void openArticle() {
		world.driver.get("http://dev-allied-automation-drupal.pantheonsite.io/node/9/");
	}
	
	@When("^the \"Delete\" button is clicked")
	public void delete() {
		Article article = new Article(world.driver);
		article.deleteLnk.click();
	}
	
	@And("^the deletion is confirmed$")
	public void deleteConfirm() {
		DeleteArticle deleteArticle = new DeleteArticle(world.driver);
		Helpers.waitForVisibleElement(world.driver, deleteArticle.confirmDeleteHeader, 10);
		deleteArticle.deleteArticleLnk.click();
	}
	
	@Then("^a status message is shown$")
	public void checkDeletionStatus() {
		Helpers.waitForVisibleElement(world.driver, 
				world.driver.findElement(By.xpath("//div[@class='messages messages--status']")), 10);
	}
}
