package io.github.deadmoto.guice

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Stage
import cucumber.runtime.java.guice.InjectorSource

class InjectorSource : InjectorSource {
    override fun getInjector(): Injector {
        return Guice.createInjector(Stage.PRODUCTION, ScenarioModule());
    }
}