import cucumber.api.CucumberOptions
import cucumber.api.testng.AbstractTestNGCucumberTests
import cucumber.api.testng.TestNGCucumberRunner
import cucumber.runtime.RuntimeOptions
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@CucumberOptions(
        features = arrayOf("src/test/resources"),
        glue = arrayOf("io.github.deadmoto"),
        plugin = arrayOf("json:build/output/cucumber/cucumber.json")

)
@Test class CucumberTest() : AbstractTestNGCucumberTests() {

    @BeforeClass(alwaysRun = true)
    override fun setUpClass() {
        super.setUpClass()
        testNGCucumberRunner.runtimeOptions.getPlugins()
    }

    @DataProvider(parallel = true)
    override fun features(): Array<Array<Any>> {
        return super.features()
    }
}

val AbstractTestNGCucumberTests.testNGCucumberRunner: TestNGCucumberRunner
    get() {
        val declaredField = AbstractTestNGCucumberTests::class.java.getDeclaredField("testNGCucumberRunner")
        declaredField.isAccessible = true
        return declaredField.get(this) as TestNGCucumberRunner
    }

val TestNGCucumberRunner.runtimeOptions: RuntimeOptions
    get() {
        val declaredField = TestNGCucumberRunner::class.java.getDeclaredField("runtimeOptions")
        declaredField.isAccessible = true
        return declaredField.get(this) as RuntimeOptions
    }

fun RuntimeOptions.getPlugins() {
    val declaredMethod = RuntimeOptions::class.java.getDeclaredMethod("getPlugins")
    declaredMethod.isAccessible = true
    declaredMethod.invoke(this)
}