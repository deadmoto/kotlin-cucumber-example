import cucumber.api.CucumberOptions
import cucumber.api.testng.AbstractTestNGCucumberTests
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@CucumberOptions(
        features = arrayOf("src/test/resources"),
        glue = arrayOf("io.github.deadmoto"),
        plugin = arrayOf("json:build/output/cucumber/cucumber.json")

)
@Test class CucumberTest() : AbstractTestNGCucumberTests() {

    @DataProvider(parallel = true)
    override fun features(): Array<Array<Any>> {
        return super.features()
    }
}