package io.github.deadmoto

import gherkin.formatter.Formatter
import gherkin.formatter.model.Background
import gherkin.formatter.model.Examples
import gherkin.formatter.model.Feature
import gherkin.formatter.model.Scenario
import gherkin.formatter.model.ScenarioOutline
import gherkin.formatter.model.Step

open class ThreadSafeFormatterWrapper(val formatter: Formatter) : Formatter {

    val map = mutableMapOf<Thread, MutableList<Runnable>>()
    val list: MutableList<Runnable>
        get() {
            return map.getOrPut(Thread.currentThread()) { mutableListOf() }
        }

    override fun uri(uri: String?) {
        list.add(Runnable { formatter.uri(uri) })
    }

    override fun feature(feature: Feature?) {
        list.add(Runnable { formatter.feature(feature) })
    }

    override fun background(background: Background?) {
        list.add(Runnable { formatter.background(background) })
    }

    override fun scenarioOutline(scenarioOutline: ScenarioOutline?) {
        list.add(Runnable { formatter.scenarioOutline(scenarioOutline) })
    }

    override fun examples(examples: Examples?) {
        list.add(Runnable { formatter.examples(examples) })
    }

    override fun startOfScenarioLifeCycle(scenario: Scenario?) {
        list.add(Runnable { formatter.startOfScenarioLifeCycle(scenario) })
    }

    override fun scenario(scenario: Scenario?) {
        list.add(Runnable { formatter.scenario(scenario) })
    }

    override fun step(step: Step?) {
        list.add(Runnable { formatter.step(step) })
    }

    override fun endOfScenarioLifeCycle(scenario: Scenario?) {
        list.add(Runnable { formatter.endOfScenarioLifeCycle(scenario) })
    }

    override fun syntaxError(state: String?, event: String?, legalEvents: MutableList<String>?, uri: String?, line: Int?) {
        list.add(Runnable { formatter.syntaxError(state, event, legalEvents, uri, line) })
    }

    override fun eof() {
        list.add(Runnable { formatter.eof() })
        list.forEach { it.run() }
    }

    override fun done() {
        formatter.done()
    }

    override fun close() {
        formatter.close()
    }
}
