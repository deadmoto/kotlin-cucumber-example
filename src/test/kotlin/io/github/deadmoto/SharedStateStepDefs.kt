package io.github.deadmoto

import com.google.inject.Inject
import cucumber.api.java.en.And
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.testng.Assert

class SharedStateStepDefs @Inject constructor(val sharedState: SharedState) {

    @When("^shared state is being initialized$")
    fun sharedStateIsBeingInitialized() {
        sharedState.state = "initialized"
    }

    @And("^shared state is being modified$")
    fun sharedStateIsBeingModified() {
        sharedState.state = "modified"
    }

    @Then("^shared state remains modified$")
    fun sharedStateRemainsModified() {
        Assert.assertEquals(sharedState.state, "modified")
    }
}
