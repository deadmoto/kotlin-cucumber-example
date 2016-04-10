import cucumber.api.CucumberOptions
import cucumber.api.testng.AbstractTestNGCucumberTests
import cucumber.api.testng.TestNGCucumberRunner
import cucumber.runtime.RuntimeOptions
import cucumber.runtime.formatter.PluginFactory
import io.github.deadmoto.ThreadSafeJSONFormatter
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import java.util.HashMap

@CucumberOptions(
        features = arrayOf("src/test/resources"),
        glue = arrayOf("io.github.deadmoto"),
        plugin = arrayOf("json:build/output/cucumber/cucumber.json")
)
@Test class CucumberTest() : AbstractTestNGCucumberTests() {

    @BeforeClass(alwaysRun = true)
    override fun setUpClass() {
        super.setUpClass()
        plugins().set("json", ThreadSafeJSONFormatter::class.java)
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

fun plugins(): HashMap<String, Any> {
    val declaredField = PluginFactory::class.java.getDeclaredField("PLUGIN_CLASSES")
    declaredField.isAccessible = true
    return declaredField.get(null) as HashMap<String, Any>
}