package md.usm.automation.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "features/deletearticle.feature")
public class DeleteArticleRunner extends AbstractTestNGCucumberTests {
	
}
