package io.github.deadmoto

import com.google.inject.Inject
import com.google.inject.Provider
import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.openqa.selenium.WebDriver

class StepDefs @Inject constructor(val webDriver: Provider<WebDriver>) {

    @After
    fun tierDown(scenario: Scenario) {
        webDriver.get().quit()
    }

    @When("^the step is implemented$")
    fun implementedStep(): Unit {
        webDriver.get().get("http://google.com")
        webDriver.get().pageSource
    }

    @Then("^the next step is executed$")
    fun passedStep(): Unit = Unit

    @When("^the step is not implemented yet$")
    fun notImplementedStep(): Unit {
        webDriver.get().get("http://google.com")
        webDriver.get().pageSource
        throw cucumber.api.PendingException()
    }

    @Then("^the next step is skipped$")
    fun skippedStep(): Unit = Unit
}
