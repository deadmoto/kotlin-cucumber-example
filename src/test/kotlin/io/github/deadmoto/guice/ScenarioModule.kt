package io.github.deadmoto.guice

import com.google.inject.AbstractModule
import cucumber.runtime.java.guice.ScenarioScope
import cucumber.runtime.java.guice.ScenarioScoped
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

class ScenarioModule : AbstractModule() {
    override fun configure() {
        bindScope(ScenarioScoped::class.java, ParallelScenarioScope)
        bind(ScenarioScope::class.java).toInstance(ParallelScenarioScope)
        bind(WebDriver::class.java).to(FirefoxDriver::class.java).`in`(ParallelScenarioScope)
    }
}