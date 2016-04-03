package io.github.deadmoto

import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class StepDefs {

    @When("^the step is not implemented yet$")
    fun notImplementedYet(): Unit = throw cucumber.api.PendingException()

    @Then("^the next step is skipped$")
    fun skippedStep(): Unit = Unit
}
