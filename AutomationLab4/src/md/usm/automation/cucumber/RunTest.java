package md.usm.automation.cucumber;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "features",
        glue = {"md/usm/automation/cucumber"}/*,
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }*/)
public class RunTest {}
