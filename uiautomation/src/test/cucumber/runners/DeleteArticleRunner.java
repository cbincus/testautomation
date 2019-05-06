package test.cucumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "features/deletearticle.feature",
		glue = "test.cucumber.stepdefs")
public class DeleteArticleRunner extends AbstractTestNGCucumberTests {
	
}
