import cucumber.api.CucumberOptions
import cucumber.api.testng.AbstractTestNGCucumberTests
import org.testng.annotations.Test

@CucumberOptions(
        features = arrayOf("src/test/resources"),
        glue = arrayOf("io.github.deadmoto"),
        plugin = arrayOf("pretty")

)
@Test class CucumberTest() : AbstractTestNGCucumberTests()