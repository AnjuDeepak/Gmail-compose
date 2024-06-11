package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = "steps", // Package where your step definitions are located
    plugin = {"pretty", "html:target/cucumber-reports.html"} // Optional: Generates a report
)
public class TestRunner {
}
