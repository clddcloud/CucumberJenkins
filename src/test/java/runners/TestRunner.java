package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // it has the path of feature directory or file
        features = "src/test/resources/features/",
        //the name of the package where step definitions are available
        glue="steps",
        // it stops actual execution when set to true and scans all the steps
        // also it provides missing step definition
        //dryRun = true
        // to start the execution, set the value dryRun to false
        dryRun = false,
        //run test with this tagname (these tagnames)
        // "or" "and" (if you need to choose for multiply tags)
        tags="@invalid" ,
        // plugin for reports. for html report provide folder and file name to save
        plugin={"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
        // to execute failed test cases one more time
                "rerun:target/failed.txt"}
)

public class TestRunner {
}
