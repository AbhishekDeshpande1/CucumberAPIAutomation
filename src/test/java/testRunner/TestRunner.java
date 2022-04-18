package testRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
				@RunWith(Cucumber.class)
				@CucumberOptions(features = { "src/test/resources/features/CFAPI.feature" },
						glue = { "com.ciox.cf.stepDefinitions" }, 
						plugin = {"pretty",
								},
					publish = true		
		)
public class TestRunner {

}