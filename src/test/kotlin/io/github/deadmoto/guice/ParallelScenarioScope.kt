package io.github.deadmoto.guice

import com.google.inject.Key
import com.google.inject.OutOfScopeException
import com.google.inject.Provider
import cucumber.runtime.java.guice.ScenarioScope
import java.util.HashMap

object ParallelScenarioScope : ScenarioScope {

    val threadLocal: ThreadLocal<HashMap<Any, Any>> = ThreadLocal()

    override fun <T : Any> scope(key: Key<T>, provider: Provider<T>): Provider<T> {
        println("${Thread.currentThread().name}: scope")
        return Provider {
            val map = threadLocal.get() ?: throw OutOfScopeException("Cannot access $key outside of a scoping block")
            return@Provider map.getOrPut(key, { provider.get() }) as T
        }
    }

    override fun enterScope() {
        println("${Thread.currentThread().name}: enterScope")
        threadLocal.set(hashMapOf())
    }

    override fun exitScope() {
        println("${Thread.currentThread().name}: exitScope")
        threadLocal.remove()
    }
}